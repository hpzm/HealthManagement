package com.example.service;

import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;
import com.example.pojo.Menu;

import java.util.List;

public interface MenuService {
    public List<Menu> findAll();

    public Menu findById(Integer id);

    public void edit(Menu menu);

    public void deleteById(Integer id);

    public PageResult pageQuery(QueryPageBean queryPageBean);

    public void add(Menu menu);
}
