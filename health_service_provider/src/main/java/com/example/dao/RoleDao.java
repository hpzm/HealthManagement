package com.example.dao;

import com.example.pojo.Role;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RoleDao {
    public Set<Role> findByUserId(Integer userId);

    public List<Role> findAll();

    public void deleteAssocication(Integer id);

    public void edit(Role role);

    public void deleteById(Integer id);

    public List<Integer> findPermissionIdsByRoleId(Integer id);

    public Role findById(Integer id);

    public Page<Role> findByCondition(String queryString);

    public void setRoleAndPermission(Map<String, Integer> map);

    public void add(Role role);

    public List<Integer> findMenuIdsByRoleId(Integer id);
}
