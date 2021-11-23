package com.example.dao;

import com.example.pojo.User;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface UserDao {
    public User findByUsername(String username);

    public void add(User user);

    public void setUserAndRole(Map<String, Integer> map);

    public Page<User> findByCondition(String queryString);

    public User findById(Integer id);

    public List<Integer> findroleIdsByCheckUserId(Integer id);

    public void edit(User user);

    public void deleteAssocication(Integer id);

    public void deleteById(Integer id);
}
