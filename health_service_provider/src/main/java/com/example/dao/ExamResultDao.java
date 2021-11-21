package com.example.dao;

import com.example.pojo.ExamResult;
import com.github.pagehelper.Page;

public interface ExamResultDao {
    public void deleteAll();
    public void add();
    public Page<ExamResult> findByCondition(String queryString);
}
