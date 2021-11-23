package com.example.dao;

import com.example.pojo.Menu;
import com.github.pagehelper.Page;

import java.util.List;

public interface MenuDao {

    public List<Menu> findAll();

    public void add(Menu menu);

    public Page<Menu> selectByCondition(String queryString);

    public long findCountByRole(Integer id);

    public void deleteById(Integer id);

    public void edit(Menu menu);

    public Menu findById(Integer id);
}
