package com.zhengqing.demo.service;

import com.zhengqing.demo.query.BaseQuery;
import com.zhengqing.demo.util.PageList;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<T> {

    /**
     * 添加
     * @param t
     */
    public void add(T t);

    /**
     * 修改
     * @param t
     */
    public void update(T t);

    /**
     * 删除
     * @param id
     */
    public void delete(Serializable id);

    /**
     * 根据主键查询
     * @param id
     */
    public T getOne(Serializable id);

    /**
     * 查询所有
     * @return
     */
    public List<T> getAll();

    /**
     * 条件分页查询
     * @param query
     * @return
     */
    public PageList<T> getByQuery(BaseQuery query);

}
