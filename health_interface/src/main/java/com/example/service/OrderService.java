package com.example.service;

import com.example.entity.Result;

import java.util.List;
import java.util.Map;

public interface OrderService {
    public Result order(Map map) throws Exception;
    public Map findById(Integer id) throws Exception;

    public List<Map> findCheckRecord(String telephone);

    public void updateRemark1(Integer id);

    public void updateRemark2(Integer id);
}
