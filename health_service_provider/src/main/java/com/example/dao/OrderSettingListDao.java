package com.example.dao;

import com.github.pagehelper.Page;

public interface OrderSettingListDao {

    public Page selectByCondition(String queryString);
    public void deleteById(Integer id);
    public void confirmAppointment(Integer id);

}
