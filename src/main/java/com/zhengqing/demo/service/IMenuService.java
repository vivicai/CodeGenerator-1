package com.zhengqing.demo.service;

import com.zhengqing.demo.service.IBaseService;
import com.zhengqing.demo.domain.Menu;

import java.util.List;

public interface IMenuService extends IBaseService<Menu> {

    List<Menu> treeMenu(Long id);//根据当前登录用户拿到他对应的菜单

}
