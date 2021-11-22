package com.example.dao;

import com.github.pagehelper.Page;

public interface ExamResultDao {

    public Page findByQueryString(String queryString);
}
