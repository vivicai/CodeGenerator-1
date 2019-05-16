package com.zhengqing.test;

import com.zhengqing.demo.util.LayuiColumn;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MyEasyCodeTest {

    /*
    * 代码的生成顺序:
    * rpms-web/src/main/webapp/static/js/model/domain.js
    * rpms-web/src/main/webapp/WEB-INF/views/domain/index.jsp
    * rpms-web/src/main/java/cn/itsource/rpms/web/controller/DoaminController.java
    * rpms-service/src/main/java/cn/itsource/rpms/service/IDomainService.java
    * rpms-service/src/main/java/cn/itsource/rpms/service/impl/DomainServiceImpl.java
    * */


    private String projectPath = "E:\\IT_zhengqing\\IT_private_job\\demo\\";


    //准备路径的拼接
    private String[] paths ={
            "src/main/webapp/static/js/model/",
            "src/main/webapp/WEB-INF/views/domain/",
            "src/main/java/com/zhengqing/demo/web/controller/",
            "src/main/java/com/zhengqing/demo/service/",
            "src/main/java/com/zhengqing/demo/service/impl/",
            "src/main/java/com/zhengqing/demo/query/"
    };

    //准备所有模板名称(和路径顺序一致)
    private String[] tempNames = {
            "domain.js",
            "index.jsp",
            "DomainController.java",
            "IDomainService.java",
            "DomainServiceImpl.java",
            "DomainQuery.java"
    };

    //准备要生成的Domain(有可能同时生成多个)
    private String[] domains = {"Share"};


    //生成相应的文件
    @Test
    public void testCreate() throws Exception{

        //创建模板应用上下文
        VelocityContext context = new VelocityContext();//Domain  domain   vos
        //一.遍历所有的Domain
        for (int i = 0; i < domains.length; i++) {
            //1.1拿到大写的domain
            String domainBig = domains[i];
            //1.2拿到小写的domain
            String domainSmall = domainBig.substring(0,1).toLowerCase() + domainBig.substring(1);
            System.out.println(domainBig);
            System.out.println(domainSmall);
            //1.3设置上下文的替换名称
            context.put("Domain",domainBig);
            context.put("domain",domainSmall);
            //vos  数组或者集合    {title:   filed:}
            List<FieldVo> vos = scanDomain(domainBig);
            context.put("vos", vos);

            //二.遍历所有的路径
            for (int j = 0; j < paths.length; j++) {
                //2.1拿到相应的路径
                String path =paths[j];
                //2.2拿到相应的模板名称
                String tempName = tempNames[j];
                //2.3拼接回我们需要的位置文件
                String realPath = (projectPath+path + tempName).replaceAll("Domain",domainBig).replaceAll("domain",domainSmall);

                //三.准备相应文件与模板进行组合
                //3.1准备相应的文件(要生成的文件)
                File file = new File(realPath);
                //  如果父文件不存在，我们创建一个
                File parentFile = file.getParentFile();
                if(!parentFile.exists()){
                    parentFile.mkdirs();
                }
                //3.2拿到相应的模板(需要设置好编码)
                Template template = Velocity.getTemplate("template/"+tempName,"UTF-8");
                FileWriter writer = new FileWriter(file);
                template.merge(context, writer);
                writer.close();
            }
        }
    }

    private List<FieldVo> scanDomain(String domainBig) {
        List<FieldVo> list = new ArrayList<>();
        try {
            //Class对象
            String className = "com.zhengqing.demo.domain."+domainBig;
            Class c = Class.forName(className);
            //获取所有的字段
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                //扫描上面的注解LayuiColumn
                if(field.isAnnotationPresent(LayuiColumn.class)){
                    //有LayuiColumn注解
                    LayuiColumn layuiColumn = field.getAnnotation(LayuiColumn.class);
                    String title = layuiColumn.title();
                    //拿到字段的名称
                    String name = field.getName();
                    FieldVo fv = new FieldVo(title,name);
                    list.add(fv);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }


}
