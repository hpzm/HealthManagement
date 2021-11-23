package com.example.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dao.PermissionDao;
import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;
import com.example.pojo.Permission;
import com.example.service.PermissionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = PermissionService.class)
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    public void add(Permission permission) {
        permissionDao.add(permission);
    }

    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();//查询条件
        PageHelper.startPage(currentPage, pageSize);
        Page<Permission> page = permissionDao.selectByCondition(queryString);
        long total = page.getTotal();
        List<Permission> rows = page.getResult();
        return new PageResult(total, rows);
    }

    public void deleteById(Integer id) {
        long count = permissionDao.findCountByRole(id);
        if (count > 0) {
            //已经被关联到，不允许删除
            new RuntimeException();
        }
        permissionDao.deleteById(id);
    }

    public void edit(Permission permission) {
        permissionDao.edit(permission);
    }

    public Permission findById(Integer id) {
        return permissionDao.findById(id);
    }
}
