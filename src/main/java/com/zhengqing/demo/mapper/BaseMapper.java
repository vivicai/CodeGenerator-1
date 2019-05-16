package com.zhengqing.demo.mapper;


import com.zhengqing.demo.query.BaseQuery;

import java.io.Serializable;
import java.util.List;

public interface BaseMapper<T> {
    /**
     * 添加
     * @param t
     */
    public void insert(T t);

    /**
     * 修改
     * @param t
     */
    public void updateByPrimaryKey(T t);

    /**
     * 删除
     * @param id
     */
    public void deleteByPrimaryKey(Serializable id);

    /**
     * 根据主键查询
     * @param id
     */
    public T selectByPrimaryKey(Serializable id);

    /**
     * 查询所有
     * @return
     */
    public List<T> selectAll();

    /**
     * 条件分页查询
     * @param query
     * @return
     */
    public List<T> selectByQuery(BaseQuery query);

}
