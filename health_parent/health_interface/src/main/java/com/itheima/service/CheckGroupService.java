package com.itheima.service;

import com.itheima.pojo.CheckGroup;

/**
 * interface CheckGroupService
 *
 * @author WangPan
 * @date 2021/6/20 20:35
 */
public interface CheckGroupService {
    public void add(CheckGroup checkGroup, Integer[] checkitemIds);
}
