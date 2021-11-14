package com.example.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dao.MemberDao;
import com.example.pojo.Member;
import com.example.service.MemberService;
import com.example.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
        return memberDao.findByTelephone(telephone);
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
}
