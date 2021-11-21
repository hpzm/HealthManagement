package com.example.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.entity.Result;
import com.example.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 体检预约处理
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Reference
    private OrderService orderService;

    //结果未出
    @RequestMapping("/updateRemark1")
    public Result updateRemark1(Integer id) {
        try {
            orderService.updateRemark1(id);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    //结果已出
    @RequestMapping("/updateRemark2")
    public Result updateRemark2(Integer id) {
        try {
            orderService.updateRemark2(id);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

}
