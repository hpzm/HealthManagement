package com.example.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;
import com.example.entity.Result;
import com.example.pojo.Menu;
import com.example.service.MenuService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Reference
    private MenuService menuService;

    //查询所有
    @RequestMapping("/findAll")
    public Result findAll() {
        try {
            List<Menu> list = menuService.findAll();
            return new Result(true, "所有菜单查询成功", list);//查询成功
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "所有菜单查询失败");//查询失败
        }
    }

    //新增
    @RequestMapping("/add")
    public Result add(@RequestBody Menu menu) {
        try {
            menuService.add(menu);
        } catch (Exception e) {
            return new Result(false, "新增菜单失败");
        }
        return new Result(true, "新增菜单成功");
    }

    //检查项分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = menuService.pageQuery(queryPageBean);
        return pageResult;
    }

    //删除
    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            menuService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            //服务调用失败
            return new Result(false, "删除菜单失败");
        }
        return new Result(true, "删除菜单成功");
    }

    //编辑检查项
    @RequestMapping("/edit")
    public Result edit(@RequestBody Menu menu) {
        try {
            menuService.edit(menu);
        } catch (Exception e) {
            e.printStackTrace();
            //服务调用失败
            return new Result(false, "更新菜单失败");
        }
        return new Result(true, "更新菜单成功");
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            Menu menu = menuService.findById(id);
            return new Result(true, "查询菜单成功", menu);
        } catch (Exception e) {
            e.printStackTrace();
            //服务调用失败
            return new Result(false, "查询菜单失败");
        }
    }
}
