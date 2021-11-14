package com.example.service;

import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;
import com.example.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {

    public void add(CheckGroup checkGroup, Integer[] checkitemIds);

    public PageResult pageQuery(QueryPageBean queryPageBean);

    public CheckGroup findById(Integer id);

    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    public void edit(CheckGroup checkGroup, Integer[] checkitemIds);

    public List<CheckGroup> findAll();

}
