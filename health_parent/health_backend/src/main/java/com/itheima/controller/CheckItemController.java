package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.itheima.constant.MessageConstant.*;

/**
 * class CheckItemController
 *
 * @author WangPan
 * @date 2021/6/19 23:44
 *
 * 检查项管理
 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
    @Reference//查找服务
    private CheckItemService checkItemService;

    //新增检查项
    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        try {
            checkItemService.add(checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            //说明服务调用失败
            return new  Result(false,ADD_CHECKITEM_FAIL);
        }
        return new  Result(true,ADD_CHECKITEM_SUCCESS);
    }

    //检查项分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = checkItemService.pageQuery(queryPageBean);
        return pageResult;
    }

    //删除检查项
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            checkItemService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            //说明服务调用失败
            return new  Result(false,DELETE_CHECKITEM_FAIL);
        }
        return new  Result(true,DELETE_CHECKITEM_SUCCESS);
    }

    //编辑检查项
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckItem checkItem){
        try {
            checkItemService.edit(checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            //说明服务调用失败
            return new  Result(false,EDIT_CHECKITEM_FAIL);
        }
        return new  Result(true,EDIT_CHECKITEM_SUCCESS);
    }

    //编辑检查项
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            CheckItem checkItem = checkItemService.findById(id);
            return new  Result(true,QUERY_CHECKITEM_SUCCESS,checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            //说明服务调用失败
            return new  Result(false,QUERY_CHECKITEM_FAIL);
        }
    }

    //编辑检查项
    @RequestMapping("/findAll")
    public Result findAll(){
        try {
            List<CheckItem> list = checkItemService.findAll();
            return new  Result(true,QUERY_CHECKITEM_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            //说明服务调用失败
            return new  Result(false,QUERY_CHECKITEM_FAIL);
        }
    }

}
