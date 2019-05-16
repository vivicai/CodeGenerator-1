package com.zhengqing.demo.domain;

import com.zhengqing.demo.util.LayuiColumn;

public class User {
    @LayuiColumn(title = "编号")
    private Long id;
    @LayuiColumn(title = "用户名")
    private String username;
    @LayuiColumn(title = "密码")
    private String password;
    @LayuiColumn(title = "性别")
    private Integer sex;
    @LayuiColumn(title = "头像")
    private String userHeadUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getUserHeadUrl() {
        return userHeadUrl;
    }

    public void setUserHeadUrl(String userHeadUrl) {
        this.userHeadUrl = userHeadUrl == null ? null : userHeadUrl.trim();
    }
}