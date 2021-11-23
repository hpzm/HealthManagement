package com.example.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.example.constant.MessageConstant;
import com.example.constant.RedisMessageConstant;
import com.example.entity.Result;
import com.example.pojo.Member;
import com.example.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * 处理会员相关操作
 */

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private JedisPool jedisPool;

    @Reference
    private MemberService memberService;

    //手机号快速登录
    @RequestMapping("/login")
    public Result login(HttpServletResponse response, @RequestBody Map map) {
        String telephone = (String) map.get("telephone");
        String validateCode = (String) map.get("validateCode");
        //从Redis中获取保存的验证码
        String validateCodeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_LOGIN);
        if (validateCodeInRedis != null && validateCode != null && validateCode.equals(validateCodeInRedis)) {
            //验证码输入正确
            //判断当前用户是否为会员（查询会员表来确定）
            Member member = memberService.findByTelephone(telephone);
            if (member == null) {
                member = new Member();
                //不是会员，自动完成注册（自动将当前用户信息保存到会员表）
                member.setRegTime(new Date());
                member.setPhoneNumber(telephone);
                memberService.add(member);
            }
            //向客户端浏览器写入Cookie，内容为手机号
            Cookie cookie = new Cookie("login_member_telephone", telephone);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(cookie);
            //将会员信息保存到Redis
            String json = JSON.toJSON(member).toString();
            jedisPool.getResource().setex(telephone, 60 * 30, json);
            return new Result(true, MessageConstant.LOGIN_SUCCESS);
        } else {
            //验证码输入错误
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
    }

    //根据ID查询检查组
    @RequestMapping("/findByTelephone")
    public Result findByTelephone(HttpServletRequest request) {
        String telephone = null;
        try {
            Cookie[] cookies = request.getCookies();
            for(Cookie c :cookies ){
               if(c.getName().equals("login_member_telephone")){
                   telephone=c.getValue();
               }
            }
            Member member = memberService.findByTelephone(telephone);
            if(member==null){
                return new Result(false, "请登陆后查看个人信息哦");//查询失败
            }
            return new Result(true, "会员信息查询成功", member);//查询成功
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "会员信息查询失败");//查询失败
        }
    }

    //编辑检查组
    @RequestMapping("/edit")
    public Result edit(@RequestBody Member member) {
        try {
            memberService.edit(member);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_MEMBER_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_MEMBER_SUCCESS);
    }

    //编辑检查组
    @RequestMapping("/logout")
    public Result logout(HttpServletResponse response) {
        try {
            Cookie cookie = new Cookie("login_member_telephone", null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(true, "退出成功");
    }

}
