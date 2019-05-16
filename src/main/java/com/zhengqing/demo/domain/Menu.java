package com.zhengqing.demo.domain;

import com.zhengqing.demo.util.LayuiColumn;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    @LayuiColumn(title = "编号")
    private Long id;
    @LayuiColumn(title = "菜单名称")
    private String name;//菜单名称  ex:菜单管理
    @LayuiColumn(title = "菜单路径")
    private String url;//菜单路径  ex:/menu/index
    @LayuiColumn(title = "菜单图标")
    private String icon;//菜单图标
    @LayuiColumn(title = "菜单")
    private Menu parent;
    @LayuiColumn(title = "子菜单")
    private List<Menu> children = new ArrayList<>();//子菜单

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", parent=" + parent +
                '}';
    }
}