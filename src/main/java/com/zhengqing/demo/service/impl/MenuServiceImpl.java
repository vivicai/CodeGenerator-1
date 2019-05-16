package com.zhengqing.demo.service.impl;

import com.zhengqing.demo.domain.Menu;
import com.zhengqing.demo.mapper.BaseMapper;
import com.zhengqing.demo.mapper.MenuMapper;
import com.zhengqing.demo.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;


    @Override
    protected BaseMapper<Menu> getMapper() {
        return menuMapper;
    }

    @Override
    public List<Menu> treeMenu(Long id) {
//        Employee loginUser = UserContext.getUser();//拿到当前登录用户，然后取id给下面的

//        注意:这里只是暂时模拟的，没有shiro权限
        List<Menu> allMenus = menuMapper.treeMenu(id);//拿到当前登录用户所有的菜单(子菜单)
        allMenus.forEach(e -> System.out.println("------>"+e));
        List<Menu> parentMenus = new ArrayList<>();//准备一个空的父菜单集合
        //遍历出所有的父菜单
        for (Menu menu : allMenus) {
            if(menu.getParent()==null){
                parentMenus.add(menu);
            }
        }
        //遍历出父菜单对应的子菜单
        for (Menu parentMenu : parentMenus) {
            for (Menu menu : allMenus) {
                if (menu.getParent()==null){
                    continue;
                }
                if(menu.getParent().getId()==parentMenu.getId()){
                    parentMenu.getChildren().add(menu);
                }
            }
        }
        return parentMenus;
    }


}
