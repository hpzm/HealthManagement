package com.example.service;

import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;
import com.example.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealService {

    public void add(Setmeal setmeal, Integer[] checkgroupIds);

    public PageResult pageQuery(QueryPageBean queryPageBean);

    public List<Setmeal> findAll();

    public Setmeal findById(int id);

    public Setmeal findSetmealById(int id);

    public List<Map<String,Object>> findSetmealCount();

    public void deleteById(Integer id);

    public void edit(Setmeal setmeal, Integer[] checkgroupIds);

    public List<Integer> findCheckGroupsBySetMealId(Integer id);
}
