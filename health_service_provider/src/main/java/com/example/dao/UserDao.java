package com.example.dao;

import com.example.pojo.User;

public interface UserDao {
    public User findByUsername(String username);
}
