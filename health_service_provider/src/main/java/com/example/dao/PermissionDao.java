package com.example.dao;

import com.example.pojo.Permission;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Set;

public interface PermissionDao {
    public Set<Permission> findByRoleId(Integer roleId);

    public List<Permission> findAll();

    public void add(Permission permission);

    public Page<Permission> selectByCondition(String queryString);

    public long findCountByRole(Integer id);

    public void deleteById(Integer id);

    public void edit(Permission permission);

    public Permission findById(Integer id);
}
