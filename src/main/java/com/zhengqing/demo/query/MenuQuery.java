package com.zhengqing.demo.query;

import com.zhengqing.demo.query.BaseQuery;

public class MenuQuery extends BaseQuery {

    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MenuQuery{" +
                "name='" + name + '\'' +
                '}';
    }
}
