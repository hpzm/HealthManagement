package com.example.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dao.RoleDao;
import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;
import com.example.pojo.Role;
import com.example.service.RoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = RoleService.class)
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void add(Role role, Integer[] permissionIds) {
        roleDao.add(role);
        Integer roleId = role.getId();
        this.setRoleAndPermission(roleId, permissionIds);
    }

    private void setRoleAndPermission(Integer roleId, Integer[] permissionIds) {
        if (permissionIds != null && permissionIds.length > 0) {
            for (Integer permissionId : permissionIds) {
                Map<String, Integer> map = new HashMap();
                map.put("roleId", roleId);
                map.put("permissionId", permissionId);
                roleDao.setRoleAndPermission(map);
            }
        }
    }

    //分页查询
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page<Role> page = roleDao.findByCondition(queryString);
        long total = page.getTotal();
        List<Role> rows = page.getResult();
        return new PageResult(total, rows);
    }

    //根据ID查询
    public Role findById(Integer id) {
        return roleDao.findById(id);
    }


    //根据ID查询关联的roleIds
    public List<Integer> findPermissionIdsByRoleId(Integer id) {
        return roleDao.findPermissionIdsByRoleId(id);
    }

    //编辑信息，同时需要关联
    public void edit(Role Role, Integer[] permissionIds) {
        roleDao.edit(Role);
        roleDao.deleteAssocication(Role.getId());
        Integer RoleId = Role.getId();
        this.setRoleAndPermission(RoleId, permissionIds);
    }

    public void deleteById(Integer id) {
        roleDao.deleteAssocication(id);
        roleDao.deleteById(id);
    }

    @Override
    public List<Integer> findMenuIdsByRoleId(Integer id) {
        return roleDao.findMenuIdsByRoleId(id);
    }
}
