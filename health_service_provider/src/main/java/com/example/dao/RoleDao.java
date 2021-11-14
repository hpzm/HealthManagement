package com.example.dao;

import com.example.pojo.Role;

import java.util.Set;

public interface RoleDao {
    public Set<Role> findByUserId(Integer userId);
}
