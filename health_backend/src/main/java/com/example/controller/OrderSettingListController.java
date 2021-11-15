package com.example.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.constant.MessageConstant;
import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;
import com.example.entity.Result;
import com.example.service.OrderSettingListService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 预约列表
 */
@RestController
@RequestMapping("/OrderSettingList")
public class OrderSettingListController {

    @Reference
    private OrderSettingListService orderSettingListService;

    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = orderSettingListService.pageQuery(queryPageBean);
        return pageResult;
    }

    //取消预约
    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            orderSettingListService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            //服务调用失败
            return new Result(false, "取消预约失败！");
        }
        return new Result(true, "取消预约成功！");
    }

    //确认预约
    @RequestMapping("/confirmAppointment")
    public Result confirmAppointment(Integer id) {
        try {
            orderSettingListService.confirmAppointment(id);
        } catch (Exception e) {
            e.printStackTrace();
            //服务调用失败
            return new Result(false, "确认预约失败！");
        }
        return new Result(true, "确认预约成功！");
    }

}
