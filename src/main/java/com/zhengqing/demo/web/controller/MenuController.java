package com.zhengqing.demo.web.controller;

import com.zhengqing.demo.domain.Menu;
import com.zhengqing.demo.query.MenuQuery;
import com.zhengqing.demo.service.IMenuService;
import com.zhengqing.demo.util.AjaxResult;
import com.zhengqing.demo.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @RequestMapping("/index")
    public String index(){
        return "menu/index";
    }

    @RequestMapping("/list")//得到当前用户所有菜单
    @ResponseBody
    public List<Menu> list(){
        return menuService.treeMenu(1L);
    }

    /**
     * 分页查询
     * @param query
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public PageList<Menu> page(MenuQuery query){
        return menuService.getByQuery(query);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(Long id){
        try {
            menuService.delete(id);
            return new AjaxResult("删除成功!",1);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("删除失败："+e.getMessage(),-1);
        }
    }

    /**
     * 保存
     * @param menu
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(Menu menu){
        try {
            if(menu.getId()!=null){
                menuService.update(menu);
            }else{
                menuService.add(menu);
            }
            return new AjaxResult("保存成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("保存失败:"+e.getMessage(),-10002);
        }
    }

}
