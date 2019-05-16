package com.zhengqing.demo.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhengqing.demo.mapper.BaseMapper;
import com.zhengqing.demo.query.BaseQuery;
import com.zhengqing.demo.service.IBaseService;
import com.zhengqing.demo.util.PageList;

import java.io.Serializable;
import java.util.List;

public abstract class BaseServiceImpl<T> implements IBaseService<T> {

    //由于core层没有集成spring，所以无法实现依赖注入
    protected abstract BaseMapper<T> getMapper();

    public void add(T t) {
        getMapper().insert(t);
    }
    public void update(T t) {
        getMapper().updateByPrimaryKey(t);
    }
    public void delete(Serializable id) {
        getMapper().deleteByPrimaryKey(id);
    }

    public T getOne(Serializable id) {
        return getMapper().selectByPrimaryKey(id);
    }

    public List<T> getAll() {
        return getMapper().selectAll();
    }

    /*public PageList<T> getByQuery(BaseQuery query) {
        //创建封装数据的PageList对象
        PageList<T> pageList = new PageList<T>();
        //设置分页条件
        Page page = PageHelper.startPage(query.getPage(),query.getRows());
        //查询当前页的数据
        List<T> rows = getMapper().selectByQuery(query);
        //获取总条目数
        long total = page.getTotal();
        //将数据封装到PageList对象中返回
        pageList.setRows(rows);
        pageList.setTotal(total);
        return pageList;
    }*/

    public PageList<T> getByQuery(BaseQuery query) {
        //创建封装数据的PageList对象
        PageList<T> pageList = new PageList<T>();
        //设置分页条件
        Page page = PageHelper.startPage(query.getPage(),query.getLimit());
        //查询当前页的数据
        List<T> rows = getMapper().selectByQuery(query);
        //获取总条目数
        long total = page.getTotal();
        //将数据封装到PageList对象中返回
        pageList.setData(rows);
        pageList.setCount(total);
        return pageList;
    }

}