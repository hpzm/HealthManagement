package com.example.service;

import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;

public interface ExamResultService {
    public PageResult findPage(QueryPageBean queryPageBean);
}
