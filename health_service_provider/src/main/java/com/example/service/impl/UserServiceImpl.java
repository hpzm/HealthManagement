package com.example.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dao.PermissionDao;
import com.example.dao.RoleDao;
import com.example.dao.UserDao;
import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;
import com.example.pojo.Permission;
import com.example.pojo.Role;
import com.example.pojo.User;
import com.example.service.UserService;
import com.example.utils.MD5Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户服务
 */
@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    //根据用户名查询数据库获取用户信息和关联的角色信息，同时需要查询角色关联的权限信息
    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);//查询用户基本信息，不包含用户的角色
        if (user == null) {
            return null;
        }
        Integer userId = user.getId();
        //根据用户ID查询对应的角色
        Set<Role> roles = roleDao.findByUserId(userId);
        for (Role role : roles) {
            Integer roleId = role.getId();
            //根据角色ID查询关联的权限
            Set<Permission> permissions = permissionDao.findByRoleId(roleId);
            role.setPermissions(permissions);//让角色关联权限
        }
        user.setRoles(roles);//让用户关联角色
        return user;
    }

    //新增User，同时需要让关联role
    public void add(User user, Integer[] roleIds) {
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder() ;
        String userPwdEncode = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(userPwdEncode);
        userDao.add(user);
        //设置检查组和检查项的多对多的关联关系，操作t_checkgroup_checkitem表
        Integer userId = user.getId();
        this.setUserAndRole(userId, roleIds);
    }

    private void setUserAndRole(Integer userId, Integer[] roleIds) {
        if (roleIds != null && roleIds.length > 0) {
            for (Integer roleId : roleIds) {
                Map<String, Integer> map = new HashMap();
                map.put("userId", userId);
                map.put("roleId", roleId);
                userDao.setUserAndRole(map);
            }
        }
    }


    //分页查询
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page<User> page = userDao.findByCondition(queryString);
        long total = page.getTotal();
        List<User> rows = page.getResult();
        return new PageResult(total, rows);
    }

    //根据ID查询
    public User findById(Integer id) {
        User user = userDao.findById(id);
        user.setPassword("");
        return user;
    }


    //根据ID查询关联的roleIds
    public List<Integer> findroleIdsByCheckUserId(Integer id) {
        return userDao.findroleIdsByCheckUserId(id);
    }

    //编辑信息，同时需要关联
    public void edit(User user, Integer[] roleIds) {
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder() ;
        String userPwdEncode = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(userPwdEncode);
        userDao.edit(user);
        userDao.deleteAssocication(user.getId());
        Integer userId = user.getId();
        this.setUserAndRole(userId, roleIds);
    }

    public void deleteById(Integer id) {
        userDao.deleteAssocication(id);
        userDao.deleteById(id);
    }
}
