package com.example.dao;

import com.github.pagehelper.Page;
import com.example.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealDao {

    public void add(Setmeal setmeal);

    public void setSetmealAndCheckGroup(Map map);

    public Page<Setmeal> findByCondition(String queryString);

    public List<Setmeal> findAll();

    public Setmeal findById(int id);

    public Setmeal findSetmealById(int id);

    Object findById4Detail(Integer id);

    public List<Map<String, Object>> findSetmealCount();

    public void deleteSetmealAndCheckGroupById(Integer id);

    public void deleteById(Integer id);

    public void edit(Setmeal setmeal);
}
