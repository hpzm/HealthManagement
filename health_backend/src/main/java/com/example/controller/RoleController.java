package com.example.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;
import com.example.entity.Result;
import com.example.pojo.Role;
import com.example.service.RoleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色操作
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Reference
    private RoleService roleService;

    //查询所有
    @RequestMapping("/findAll")
    public Result findAll() {
        try {
            List<Role> list = roleService.findAll();
            return new Result(true, "所有角色查询成功", list);//查询成功
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "所有角色查询失败");//查询失败
        }
    }

    //新增
    @RequestMapping("/add")
    public Result add(@RequestBody Role Role, Integer[] permissionIds) {
        try {
            System.out.println(Role.getId());
            roleService.add(Role, permissionIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "新增角色失败");//新增失败
        }
        return new Result(true, "新增角色成功");//新增成功
    }

    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return roleService.pageQuery(queryPageBean);
    }

    //根据ID查询
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            Role Role = roleService.findById(id);
            return new Result(true, "角色查询成功", Role);//查询成功
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "角色查询失败");//查询失败
        }
    }

    @RequestMapping("/findPermissionIdsByRoleId")
    public Result findPermissionIdsByRoleId(Integer id) {
        try {
            List<Integer> permissionIds = roleService.findPermissionIdsByRoleId(id);
            return new Result(true,"查询角色成功", permissionIds);//查询成功
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "查询角色失败");//查询失败
        }
    }

    @RequestMapping("/findMenuIdsByRoleId")
    public Result findMenuIdsByRoleId(Integer id) {
        try {
            List<Integer> MenuIds = roleService.findMenuIdsByRoleId(id);
            return new Result(true,"查询菜单成功", MenuIds);//查询成功
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "查询菜单失败");//查询失败
        }
    }

    //编辑
    @RequestMapping("/edit")
    public Result edit(@RequestBody Role Role, Integer[] permissionIds) {
        try {
            roleService.edit(Role, permissionIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "角色更新失败");
        }
        return new Result(true, "角色更新成功");
    }

    //删除检查组
    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            roleService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            //服务调用失败
            return new Result(false, "角色删除失败");
        }
        return new Result(true, "角色删除成功");
    }


}
