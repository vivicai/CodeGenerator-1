package com.zhengqing.demo.web.controller;

import com.zhengqing.demo.domain.User;
import com.zhengqing.demo.query.UserQuery;
import com.zhengqing.demo.service.IUserService;
import com.zhengqing.demo.util.AjaxResult;
import com.zhengqing.demo.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/index")
    public String index(){
        return "user/index";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<User> list(){
        return userService.getAll();
    }

    /**
     * 分页查询
     * @param query
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public PageList<User> page(UserQuery query){
        return userService.getByQuery(query);
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
            userService.delete(id);
            return new AjaxResult("删除成功!",1);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("删除失败:"+e.getMessage(),-1);
        }
    }

    /**
     * 保存
     * @param user
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(User user){
        try {
            if(user.getId()!=null){
                userService.update(user);
            }else{
                userService.add(user);
            }
            return new AjaxResult("保存成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("保存失败:"+e.getMessage(),-10002);
        }
    }

}
