package com.example.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dao.MenuDao;
import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;
import com.example.pojo.Menu;
import com.example.service.MenuService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = MenuService.class)
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    public List<Menu> findAll() {
        return menuDao.findAll();
    }

    @Override
    public Menu findById(Integer id) {
        return menuDao.findById(id);
    }

    @Override
    public void edit(Menu menu) {
        menuDao.edit(menu);
    }

    @Override
    public void deleteById(Integer id) {
        long count = menuDao.findCountByRole(id);
        if (count > 0) {
            //已经被关联到，不允许删除
            new RuntimeException();
        }
        menuDao.deleteById(id);
    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();//查询条件
        PageHelper.startPage(currentPage, pageSize);
        Page<Menu> page = menuDao.selectByCondition(queryString);
        long total = page.getTotal();
        List<Menu> rows = page.getResult();
        return new PageResult(total, rows);
    }

    @Override
    public void add(Menu menu) {
        menuDao.add(menu);
    }
}
