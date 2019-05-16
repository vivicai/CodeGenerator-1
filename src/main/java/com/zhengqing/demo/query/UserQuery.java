package com.zhengqing.demo.query;

import com.zhengqing.demo.query.BaseQuery;

public class UserQuery extends BaseQuery {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserQuery{" +
                "username='" + username + '\'' +
                '}';
    }
}
