package com.example.dao;

import com.example.pojo.Permission;

import java.util.Set;

public interface PermissionDao {
    public Set<Permission> findByRoleId(Integer roleId);
}
