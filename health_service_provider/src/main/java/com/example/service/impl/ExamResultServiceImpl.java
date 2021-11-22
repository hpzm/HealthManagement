package com.example.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dao.ExamResultDao;
import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;
import com.example.service.ExamResultService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = ExamResultService.class)
@Transactional
public class ExamResultServiceImpl implements ExamResultService {

    @Autowired
    private ExamResultDao examResultDao;

    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page page = examResultDao.findByQueryString(queryString);
        long total = page.getTotal();
        List rows = page.getResult();
        return new PageResult(total, rows);
    }

}
