package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckItemDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * class CheckItemServiceImpl
 *
 * @author WangPan
 * @date 2021/6/19 23:57
 *
 * 检查项服务
 */
@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    //注入Dao对象
    @Autowired
    private CheckItemDao checkItemDao;

    //新增检查项
    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    //检查项分页查询
    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        //获取当前页码
        Integer currentPage = queryPageBean.getCurrentPage();
        //要查询多少条数据
        Integer pageSize = queryPageBean.getPageSize();
        //过滤条件
        String queryString = queryPageBean.getQueryString();
        //完成分页查询，基于mybatis框架提供的分页助手PageHelper插件完成
        PageHelper.startPage(currentPage, pageSize);
        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);
        //总的记录数
        long total = page.getTotal();
        //rows
        List<CheckItem> rows = page.getResult();
        return new PageResult(total,rows);
    }

    //根据id删除检查项
    @Override
    public void deleteById(Integer id) {
        //判断当前检查项是否关联检查组
        long count = checkItemDao.findCountByCheckItemId(id);
        if (count > 0) {
            //当前检查项已经被关联到检查组了，不允许删除
            new RuntimeException();
        }
        checkItemDao.deleteById(id);
    }

    //编辑检查项
    @Override
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }

    @Override
    public CheckItem findById(Integer id) {
        return checkItemDao.findById(id);
    }

    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }
}