package com.example.service;

import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;
import com.example.pojo.Permission;

import java.util.List;

public interface PermissionService {
    public List<Permission> findAll();

    public void add(Permission permission);

    public PageResult pageQuery(QueryPageBean queryPageBean);

    public void deleteById(Integer id);

    public void edit(Permission permission);

    public Permission findById(Integer id);
}
