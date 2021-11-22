package com.example.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dao.OrderSettingListDao;
import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;
import com.example.service.OrderSettingListService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = OrderSettingListService.class)
@Transactional
public class OrderSettingListServiceImpl implements OrderSettingListService {

    @Autowired
    private OrderSettingListDao orderSettingListDao;

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();//查询条件
        //完成分页查询，基于mybatis框架提供的分页助手插件完成
        PageHelper.startPage(currentPage, pageSize);
        Page page = orderSettingListDao.selectByCondition(queryString);
        long total = page.getTotal();
        List rows = page.getResult();
        return new PageResult(total, rows);
    }

    //根据ID取消预约
    @Override
    public void deleteById(Integer id) {
        //判断当前检查项是否已经关联到检查组
        orderSettingListDao.deleteById(id);
    }

    @Override
    public void confirmAppointment(Integer id) {
        orderSettingListDao.confirmAppointment(id);
    }

}
