package com.example.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.constant.MessageConstant;
import com.example.constant.RedisMessageConstant;
import com.example.entity.Result;
import com.example.pojo.Order;
import com.example.service.OrderService;
import com.example.utils.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 体检预约处理
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private JedisPool jedisPool;

    @Reference
    private OrderService orderService;

    //在线体检预约
    @RequestMapping("/submit")
    public Result submit(@RequestBody Map map) {
        String telephone = (String) map.get("telephone");
        //从Redis中获取保存的验证码
        String validateCodeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);
        String validateCode = (String) map.get("validateCode");

        //将用户输入的验证码和Redis中保存的验证码进行比对
        if (validateCodeInRedis != null && validateCode != null && validateCode.equals(validateCodeInRedis)) {
            //如果比对成功，调用服务完成预约业务处理
            map.put("orderType", Order.ORDERTYPE_WEIXIN);//设置预约类型，分为微信预约、电话预约
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

    //根据预约ID查询预约相关信息
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            Map map = orderService.findById(id);
            return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ORDER_FAIL);
        }
    }

    @RequestMapping("/findCheckRecord")
    public Result findCheckRecord(HttpServletRequest request) {
        try {
            String telephone = null;
            Cookie[] cookies = request.getCookies();
            for (Cookie c : cookies) {
                if (c.getName().equals("login_member_telephone")) {
                    telephone = c.getValue();
                }
            }
            if(telephone==null){
                return new Result(false, "请登陆后查看");
            }
            List<Map> map = orderService.findCheckRecord(telephone);
            if(map==null){
                return new Result(false, "请预约后查看");
            }
            return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ORDER_FAIL);
        }
    }

}
