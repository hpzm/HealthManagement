package com.example.service;

import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;
import com.example.pojo.Role;

import java.util.List;

public interface RoleService {
    public List<Role> findAll();

    public void add(Role role, Integer[] permissionIds);

    public PageResult pageQuery(QueryPageBean queryPageBean);

    public Role findById(Integer id);

    public List<Integer> findPermissionIdsByRoleId(Integer id);

    public void edit(Role role, Integer[] permissionIds);

    public void deleteById(Integer id);

    public List<Integer> findMenuIdsByRoleId(Integer id);
}
