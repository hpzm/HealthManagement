package com.example.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;
import com.example.entity.Result;
import com.example.pojo.Permission;
import com.example.service.PermissionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Reference
    private PermissionService permissionService;

    //查询所有
    @RequestMapping("/findAll")
    public Result findAll() {
        try {
            List<Permission> list = permissionService.findAll();
            return new Result(true, "所有权限查询成功", list);//查询成功
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "所有权限查询失败");//查询失败
        }
    }

    //新增
    @RequestMapping("/add")
    public Result add(@RequestBody Permission permission) {
        try {
            permissionService.add(permission);
        } catch (Exception e) {
            return new Result(false, "新增权限失败");
        }
        return new Result(true, "新增权限成功");
    }

    //检查项分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = permissionService.pageQuery(queryPageBean);
        return pageResult;
    }

    //删除检查项
    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            permissionService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            //服务调用失败
            return new Result(false, "删除权限失败");
        }
        return new Result(true, "删除权限成功");
    }

    //编辑检查项
    @RequestMapping("/edit")
    public Result edit(@RequestBody Permission permission) {
        try {
            permissionService.edit(permission);
        } catch (Exception e) {
            e.printStackTrace();
            //服务调用失败
            return new Result(false, "更新权限失败");
        }
        return new Result(true,"更新权限成功");
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            Permission permission = permissionService.findById(id);
            return new Result(true, "查询权限成功", permission);
        } catch (Exception e) {
            e.printStackTrace();
            //服务调用失败
            return new Result(false, "查询权限失败");
        }
    }

}
