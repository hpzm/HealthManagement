package com.example.service;

import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;
import com.example.pojo.User;

import java.util.List;

public interface UserService {
    public User findByUsername(String username);

    public void add(User user, Integer[] roleIds);

    public PageResult pageQuery(QueryPageBean queryPageBean);

    public User findById(Integer id);

    public List<Integer> findroleIdsByCheckUserId(Integer id);

    public void edit(User user, Integer[] roleIds);

    public void deleteById(Integer id);
}
