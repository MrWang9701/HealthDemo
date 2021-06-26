package com.itheima.dao;

import com.itheima.pojo.CheckGroup;

import java.util.Map;

/**
 * class CheckGroupDao
 *
 * @author WangPan
 * @date 2021/6/20 20:44
 *
 * 检查组
 */
public interface CheckGroupDao {
    public void add(CheckGroup checkGroup);
    public void setCheckGroupAndCheckItem(Map map);
}
