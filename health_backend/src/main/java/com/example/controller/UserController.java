package com.example.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.constant.MessageConstant;
import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;
import com.example.entity.Result;
import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户操作
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    //获得当前登录用户的用户名
    @RequestMapping("/getUsername")
    public Result getUsername() {
        //当Spring security完成认证后，会将当前用户信息保存到框架提供的上下文对象
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user);
        if (user != null) {
            String username = user.getUsername();
            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS, username);
        }

        return new Result(false, MessageConstant.GET_USERNAME_FAIL);
    }

    //新增
    @RequestMapping("/add")
    public Result add(@RequestBody User user, Integer[] roleIds) {
        try {
            userService.add(user, roleIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "新增用户失败");//新增失败
        }
        return new Result(true, "新增用户成功");//新增成功
    }

    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return userService.pageQuery(queryPageBean);
    }

    //根据ID查询
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            User user = userService.findById(id);
            return new Result(true, "用户查询成功", user);//查询成功
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "用户查询失败");//查询失败
        }
    }

    @RequestMapping("/findRoleIdsByCheckUserId")
    public Result findRoleIdsByCheckUserId(Integer id) {
        try {
            List<Integer> roleIds = userService.findroleIdsByCheckUserId(id);
            return new Result(true,"查询角色成功", roleIds);//查询成功
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "查询角色失败");//查询失败
        }
    }

    //编辑
    @RequestMapping("/edit")
    public Result edit(@RequestBody User user, Integer[] roleIds) {
        try {
            userService.edit(user, roleIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "用户更新失败");
        }
        return new Result(true, "用户更新成功");
    }

    //删除
    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            userService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            //服务调用失败
            return new Result(false, "用户删除失败");
        }
        return new Result(true, "用户删除成功");
    }
}
