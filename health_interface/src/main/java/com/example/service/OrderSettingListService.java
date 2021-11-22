package com.example.service;

import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;

public interface OrderSettingListService {

    public PageResult pageQuery(QueryPageBean queryPageBean);

    public void deleteById(Integer id);

    public void confirmAppointment(Integer id);

}
