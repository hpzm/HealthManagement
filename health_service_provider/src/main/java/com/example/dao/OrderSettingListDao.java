package com.example.dao;

import com.example.pojo.Member;
import com.example.pojo.OrderSettingList;
import com.github.pagehelper.Page;

public interface OrderSettingListDao {

    public void deleteAll();
    public void add();
    public Page<OrderSettingList> selectByCondition(String queryString);
    public void deleteById(Integer id);
    public void confirmAppointment(Integer id);

}
