package com.zhengqing.demo.web.controller;

import com.zhengqing.demo.domain.${Domain};
import com.zhengqing.demo.query.${Domain}Query;
import com.zhengqing.demo.service.I${Domain}Service;
import com.zhengqing.demo.util.AjaxResult;
import com.zhengqing.demo.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/${domain}")
public class ${Domain}Controller {

    @Autowired
    private I${Domain}Service ${domain}Service;

    @RequestMapping("/index")
    public String index(){
        return "${domain}/index";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<${Domain}> list(){
        return ${domain}Service.getAll();
    }

    /**
     * 分页查询
     * @param query
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public PageList<${Domain}> page(${Domain}Query query){
        return ${domain}Service.getByQuery(query);
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
            ${domain}Service.delete(id);
            return new AjaxResult("删除成功!",1);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("删除失败:"+e.getMessage(),-1);
        }
    }

    /**
     * 保存
     * @param ${domain}
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(${Domain} ${domain}){
        try {
            if(${domain}.getId()!=null){
                ${domain}Service.update(${domain});
            }else{
                ${domain}Service.add(${domain});
            }
            return new AjaxResult("保存成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("保存失败:"+e.getMessage(),-10002);
        }
    }

}
