package com.example.dao;

import com.example.pojo.CheckItem;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * 持久层Dao接口
 */
public interface CheckItemDao {

    public void add(CheckItem checkItem);

    public Page<CheckItem> selectByCondition(String queryString);

    public long findCountByCheckItemId(Integer id);

    public void deleteById(Integer id);

    public void edit(CheckItem checkItem);

    public CheckItem findById(Integer id);

    public List<CheckItem> findAll();

}
