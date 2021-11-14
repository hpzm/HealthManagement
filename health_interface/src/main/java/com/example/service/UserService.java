package com.example.service;

import com.example.pojo.User;

public interface UserService {
    public User findByUsername(String username);
}
