package com.example.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dao.ExamResultDao;
import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;
import com.example.pojo.ExamResult;
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

    //分页查询
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        examResultDao.deleteAll();
        examResultDao.add();
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page<ExamResult> page = examResultDao.findByCondition(queryString);
        long total = page.getTotal();
        List<ExamResult> rows = page.getResult();
        return new PageResult(total, rows);
    }

}
