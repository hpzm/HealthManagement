package com.example.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dao.MemberDao;
import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;
import com.example.pojo.Member;
import com.example.service.MemberService;
import com.example.utils.MD5Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 会员服务
 */
@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    public Member findByTelephone(String telephone) {
        Member member = memberDao.findByTelephone(telephone);
        if ("1".equals(member.getSex())) {
            member.setSex("男");
        } else {
            member.setSex("女");
        }
        // 出生日期
        String IdCard = member.getIdCard();
        String birthday = null;
        // 身份证号不为空
        if (IdCard.length() == 15) {
            birthday = "19" + IdCard.substring(6, 8) + "-" + IdCard.substring(8, 10) + "-" + IdCard.substring(10, 12);
        } else if (IdCard.length() == 18) {
            birthday = IdCard.substring(6, 10) + "-" + IdCard.substring(10, 12) + "-" + IdCard.substring(12, 14);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        member.setBirthday(date);
        return member;
    }

    //保存会员信息
    public void add(Member member) {
        String password = member.getPassword();
        if (password != null) {
            //使用md5将明文密码进行加密
            password = MD5Utils.md5(password);
            member.setPassword(password);
        }
        memberDao.add(member);
    }

    //根据月份查询会员数量
    public List<Integer> findMemberCountByMonths(List<String> months) {//2018-05
        List<Integer> memberCount = new ArrayList<>();
        for (String month : months) {
            String[] dates = month.split("-");
            int year1 = Integer.parseInt(dates[0]);
            int month1 = Integer.parseInt(dates[1]);
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year1);
            cal.set(Calendar.MONTH, month1 - 1);//Java月份才0开始算
            int dateOfMonth = cal.getActualMaximum(Calendar.DATE);
//            String date = month + ".31";//2021-05-31
            String date = month + "-" + dateOfMonth;//2021-05-31
            Integer count = memberDao.findMemberCountBeforeDate(date);
            memberCount.add(count);
        }
        return memberCount;
    }

    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page<Member> page = memberDao.selectByCondition(queryString);
        long total = page.getTotal();
        List<Member> rows = page.getResult();
        return new PageResult(total, rows);
    }

    //根据ID查询
    public Member findById(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    //编辑信息
    public void edit(Member member) {
        memberDao.edit(member);
    }

    //注销
    public void deleteById(Integer id) {
        memberDao.deleteOrderMemberIdById(id);
        memberDao.deleteById(id);
    }

}
