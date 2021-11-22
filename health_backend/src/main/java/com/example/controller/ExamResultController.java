package com.example.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;
import com.example.service.ExamResultService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/examResult")
public class ExamResultController {

    @Reference
    private ExamResultService examResultService;

    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
//        System.out.println(queryPageBean.getCurrentPage());
        PageResult pageResult = examResultService.findPage(queryPageBean);
        return pageResult;
    }

}
