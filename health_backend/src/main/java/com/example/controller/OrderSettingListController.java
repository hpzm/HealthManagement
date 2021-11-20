package com.example.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.constant.MessageConstant;
import com.example.constant.RedisMessageConstant;
import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;
import com.example.entity.Result;
import com.example.pojo.Order;
import com.example.service.OrderService;
import com.example.service.OrderSettingListService;
import com.example.utils.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * 预约列表
 */
@RestController
@RequestMapping("/OrderSettingList")
public class OrderSettingListController {

    @Autowired
    private JedisPool jedisPool;

    @Reference
    private OrderService orderService;

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

    //电话体检预约
    @RequestMapping("/submitTelephone")
    public Result submitTelephone(@RequestBody Map map) {
        String telephone = (String) map.get("telephone");
        //从Redis中获取保存的验证码
        String validateCodeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);
        String validateCode = (String) map.get("validateCode");

        //将用户输入的验证码和Redis中保存的验证码进行比对
        if (validateCodeInRedis != null && validateCode != null && validateCode.equals(validateCodeInRedis)) {
            //如果比对成功，调用服务完成预约业务处理
            map.put("orderType", Order.ORDERTYPE_TELEPHONE);//设置预约类型，分为微信预约、电话预约
            Result result = null;
            try {
                result = orderService.order(map);//通过Dubbo远程调用服务实现在线预约业务处理
            } catch (Exception e) {
                e.printStackTrace();
                return result;
            }
            if (result.isFlag()) {
                //预约成功，可以为用户发送短信
                try {
                    SMSUtils.sendShortMessage(SMSUtils.ORDER_NOTICE, telephone, (String) map.get("orderDate"));
                    return new Result(true, MessageConstant.ORDER_SUCCESS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return result;
        } else {
            //如果比对不成功，返回结果给页面
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
    }


}
