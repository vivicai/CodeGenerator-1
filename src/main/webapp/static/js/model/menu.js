$(function () {
    layui.config({
        version: '1545041465480' //为了更新 js 缓存，可忽略
    });
    //注意：这里是数据表格的加载数据，必须写
    layui.use(['table', 'layer', 'form', 'laypage', 'laydate'], function () {
        var table = layui.table //表格
            ,layer = layui.layer //弹层
            ,form = layui.form //form表单
            ,laypage = layui.laypage //分页
            ,laydate = layui.laydate;//日期

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });

        //执行一个 table 实例
        table.render({
            elem: '#zq_table'
            ,id: 'tableReload'//重载数据表格
            ,url: '/menu/page' //数据接口
            ,toolbar: '#zq_toolbar' //开启头工具栏，此处default：显示默认图标，可以自定义模板，详见文档
            ,title: 'xx表'
            ,page: true //开启分页
            // ,totalRow: true //开启合计行
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left', totalRowText: '合计：'} //totalRow：true则代表这列数据要合计
                ,{field: 'name', title: '菜单名称',edit: 'text'}
                ,{field: 'url', title: '菜单路径',  sort: true, totalRow: true,edit: 'text'}
                ,{field: 'icon', title: '菜单图标', sort: true,edit: 'text'}
                ,{field: 'parent', title: '菜单', sort: true, totalRow: true,edit: 'text',templet: '#zq_formatter'}
                ,{field: 'children', title: '子菜单',edit: 'text'}
                ,{fixed: 'right',title:'操作', width: 150, align:'center', toolbar: '#zq_bar'}
            ]]
        });


        //监听头工具栏事件
        table.on('toolbar(zq_table)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id)
                ,data = checkStatus.data; //获取选中的数据
            //json字符串转换成Json数据 eval("("+jsonStr+")")  /JSON.parse(jsonStr)
            data = eval("("+JSON.stringify(data)+")");
            switch(obj.event){
                case 'delAll':
                    if(data.length === 0){
                        layer.msg('请至少选择1行', { icon: 2, time: 1500 });
                    }else {
                        layer.alert('您确认要删除'+data.length+'条数据吗？', {
                            skin: 'layui-layer-molv' //样式类名layui-layer-lan或layui-layer-molv  自定义样式
                            ,closeBtn: 1    // 是否显示关闭按钮
                            ,anim: 1 //动画类型
                            ,btn: ['确定','取消'] //按钮
                            ,icon: 2    // icon
                            ,yes:function(){
                                // layer.msg('确定', { icon: 1, time: 1500 });
                                for (var i=0;i<data.length;i++){
                                    console.debug("id:======"+data[i].id)
                                    //发送请求到后台
                                    $.post("menu/delete", { id: data[i].id }, function (result) {
                                        if (result.code == "1") {//删除成功，刷新当前页表格
                                            // obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                            layer.msg(result.msg, { icon: 1, time: 1500 });
                                            // layer.close(index);
                                            $(".layui-laypage-btn").click();//点击分页刷新当前页
                                        }else  if(result.code == "-1"){  //删除失败
                                            layer.alert(result.msg, { icon: 2},function () {
                                                $(".layui-laypage-btn").click();
                                                window.location.reload();
                                            });
                                        }
                                    });
                                }
                                /*   //捉到所有被选中的，发异步进行删除
                                   layer.msg('删除成功', {icon: 1});
                                   $(".layui-form-checked").not('.header').parents('tr').remove();*/
                            }
                            ,btn2:function(){
                                layer.msg('好的,暂时不给您删除。',{ icon: 1, time: 1500 });
                            }
                        });
                    }
                    break;
                case 'add':
                    zq_form('添加菜单','url这个值不管','','');
                    //数据回显
                    // $("#zq_form").setForm({id:data.id,name: data.name, url: data.url,icon:data.icon,parent:data.parent,children:data.children});
                    $("#zq_form").setForm({id:''});
                    break;
            }
        });

        //监听行工具事件
        table.on('tool(zq_table)', function(obj){ //注：tool 是工具条事件名，zq_table 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            switch(layEvent){
                case 'detail':
                    //json字符串转换成Json数据 eval("("+jsonStr+")")  /JSON.parse(jsonStr)
                    var jsonstr = JSON.stringify(data);//json数据转字符串  JSON.stringify(obj)
                    layer.alert(jsonstr);
                    break;
                case 'del':
                    layer.confirm('您确定删除id：'+data.id+'的数据吗？', function(index){
                        //向服务端发送删除指令，在这里可以使用Ajax异步
                        $.post("menu/delete", { id: data.id }, function (ret) {
                            if (ret.code == "1") {//删除成功，刷新当前页表格
                                layer.msg(ret.msg, { icon: 1, time: 1500 }, function () {
                                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                    layer.close(index);
                                    // $(".layui-laypage-btn").click();//点击分页刷新当前页
                                });
                            }else  if(ret.code == "-1"){  //删除失败
                                layer.alert(ret.msg, { icon: 2},function () {
                                    layer.close(index);
                                    // $(".layui-laypage-btn").click();
                                    window.location.reload();
                                });
                            }
                        });
                    });
                    break;
                case 'edit':
                    console.debug(data);
                    zq_form('编辑菜单','url这个值不管',500,400);
                    //数据回显
                    $("#zq_form").setForm({id:data.id,name: data.name, url: data.url,icon:data.icon,parent:data.parent,children:data.children});
                    break;
            }
        });

        //监听单元格编辑   zq_table 对应 <table> 中的 lay-filter="zq_table"       做可编辑表格使用
        table.on('edit(zq_table)', function(obj){
            var value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field; //得到字段
            layer.msg('[ID: '+ data.id +'] ' + field + ' 字段更改为：'+ value);
        });

        /*
        //监听显示操作
        form.on('switch(isShow)', function(obj) {
            var t = this;
            layer.tips(t.value + ' ' + t.name + '：' + obj.elem.checked, obj.othis);
        });*/

        //监听提交 lay-filter="zq_submit"
        form.on('submit(zq_submit)', function(data){
            // console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
            // console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
            console.log(data.field) //当前from表单所提交的所有字段， 名值对形式：{name: value}
            layer.msg(JSON.stringify(data.field));//表格数据序列化
            var formData = data.field;
            var id = formData.id,
                name = formData.name,
                url=formData.url,
                icon=formData.icon,
                parent_id=formData.parent_id;
            $.ajax({
                type: "post",  //数据提交方式（post/get）
                url: "/menu/save",  //提交到的url
                data: {"id":id,"name":name,"url":url,"icon":icon,"parent_id":parent_id},//提交的数据
                dataType: "json",//返回的数据类型格式
                success: function(msg){
                    if (msg.success){  //成功
                        layer.msg(msg.msg, { icon: 1, time: 1500 });
                        table.reload('tableReload');//数据表格重载
                        layer.close(index);//关闭弹出层
                    }else {  //失败
                        layer.alert(msg.msg, { icon: 2},function () {
                            // $(".layui-laypage-btn").click();//执行分页刷新当前页
                            layer.close(index);
                            // window.location.reload();
                        });
                    }
                }
            });
            return false;//false：阻止表单跳转  true：表单跳转
        });

        //监听提交 lay-filter="search"
        form.on('submit(search)', function(data){
            layer.msg(JSON.stringify(data.field));//表格数据序列化
            var formData = data.field;
            console.debug(formData);
            var name = formData.name,
                url=formData.url,
                icon=formData.icon,
                parent_id=formData.parent_id;
            //数据表格重载
            table.reload('tableReload', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {//这里传参  向后台
                    name: name,
                    url:url
                }
                , url: '/menu/page'//后台做模糊搜索接口路径
                , method: 'post'
            });
            return false;//false：阻止表单跳转  true：表单跳转
        });

    });

});

var index;//layer.open 打开窗口后的索引，通过layer.close(index)的方法可关闭
//表单弹出层
function zq_form(title,url,w,h){
    if (title == null || title == '') { title=false; };
    if (url == null || url == '') {  };// url="404.html";
    if (w == null || w == '') {  w=($(window).width()*0.9);  };
    if (h == null || h == '') {  h=($(window).height() - 50);  };
    index = layer.open({  //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
        type:1,
        title:title,
        area: ['25%','55%'],//类型：String/Array，默认：'auto'  只有在宽高都定义的时候才不会自适应
        // area: [w+'px', h +'px'],
        fix: false, //不固定
        maxmin: true,//开启最大化最小化按钮
        shadeClose: true,//点击阴影处可关闭
        shade:0.4,//背景灰度
        skin: 'layui-layer-rim', //加上边框
        content:$("#zq_formpopbox").html()
    });
}




//表单提交 onclick  普通的jquery实现
/*function zq_submit () {
    var formData = $("#zq_form").serializeFormToJson();
    console.debug("============="+formData);
    var name = formData.name,
        url=formData.url,
        icon=formData.icon,
        parent_id=formData.parent_id;
    $.ajax({
        type: "post",  //数据提交方式（post/get）
        url: "/menu/save",  //提交到的url
        data: {"name":name,"url":url,"icon":icon,"parent_id":parent_id},//提交的数据
        dataType: "json",//返回的数据类型格式
        success: function(msg){
            if (msg.success){  //成功
                //添加成功处理代码...
                layer.msg(msg.msg, { icon: 1, time: 1500 });
            }else {  //失败
                //添加失败处理代码...
                layer.alert(msg.msg, { icon: 2},function () {
                    $(".layui-laypage-btn").click();//执行分页刷新当前页
                });
                return false;
            }
        }
    });
}*/

//表单提交 onclick  普通的jquery实现  高级查询
/*function search () {
    var formData = $("#zq_search").serializeFormToJson();
    console.debug(formData);
    var name = formData.name,
        url=formData.url,
        icon=formData.icon,
        parent_id=formData.parent_id;
    $.ajax({
        type: "post",  //数据提交方式（post/get）
        url: "/menu/page",  //提交到的url
        data: {"name":name,"url":url,"icon":icon,"parent_id":parent_id},//提交的数据
        dataType: "json",//返回的数据类型格式
        success: function(msg){
            if (msg.success){  //成功
                //添加成功处理代码...
                layer.msg(msg.msg, { icon: 1, time: 1500 });
            }else {  //失败
                //添加失败处理代码...
                layer.alert(msg.msg, { icon: 2},function () {
                    // $(".layui-laypage-btn").click();//执行分页刷新当前页
                });
                return false;
            }
        }
    });
}*/


























//下面忽略...===================================================================================================

















































/* var table = layui.table
     ,form = layui.form;
 layui.use('table', function () {  // 引入 table模块
     table.render({
         id:"zq_table",//
         elem: '#layui_table_id',//指定表格元素
         url: '/menu/page',  //请求路径
         cellMinWidth: 20 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
         ,skin: 'line ' //表格风格 line （行边框风格）row （列边框风格）nob （无边框风格）
         //,even: true    //隔行换色
         ,page: true  //开启分页
         ,limits: [10,20,50]  //每页条数的选择项，默认：[10,20,30,40,50,60,70,80,90]。
         ,limit: 10 //每页默认显示的数量
         ,method:'post'  //提交方式
         ,cols: [[
             {type:'checkbox'}, //开启多选框
             {
                 field: 'id', //json对应的key
                 title: 'ID',   //列名
                 sort: true   // 默认为 false,true为开启排序
             },
             {
                 field: 'name', //json对应的key
                 title: '菜单名称',   //列名
                 sort: true   // 默认为 false,true为开启排序
             },
             {
                 field: 'url', //json对应的key
                 title: '菜单路径',   //列名
                 sort: true   // 默认为 false,true为开启排序
             },
             {
                 field: 'icon', //json对应的key
                 title: '菜单图标',   //列名
                 sort: true   // 默认为 false,true为开启排序
             },
             {
                 field: 'parent', //json对应的key
                 title: '菜单',   //列名
                 sort: true   // 默认为 false,true为开启排序
             },
             {
                 field: 'children', //json对应的key
                 title: '子菜单',   //列名
                 sort: true   // 默认为 false,true为开启排序
             }
         ]]
     });
 });
*/



/*

$(function () {
    var table = layui.table
        ,form = layui.form;
    layui.use('table', function () {  // 引入 table模块
        table.render({
            id:"zq_table",//
            elem: '#layui_table_id',//指定表格元素
            url: '/menu/page',  //请求路径
            cellMinWidth: 20 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,skin: 'line ' //表格风格 line （行边框风格）row （列边框风格）nob （无边框风格）
            //,even: true    //隔行换色
            ,page: true  //开启分页
            ,limits: [10,20,50]  //每页条数的选择项，默认：[10,20,30,40,50,60,70,80,90]。
            ,limit: 10 //每页默认显示的数量
            ,method:'post'  //提交方式
            ,cols: [[
                {type:'checkbox'}, //开启多选框
                {
                    field: 'id', //json对应的key
                    title: 'ID',   //列名
                    sort: true   // 默认为 false,true为开启排序
                }
            ]]
        });
    });

});

*/


/*$(function(){
    //声明变量来缓存组件
    var menuDatagrid,menuDialog,menuDialogForm;
    //将组件缓存到变量中
    menuDatagrid = $("#menuDatagrid");
    menuDialog = $("#menuDialog");
    menuDialogForm = $("#menuDialogForm");
    //封装一个命令对象,存放按钮的响应函数
    var cmdObj = {
        add : function(){
            //打开模态框
            menuDialog.dialog("open").dialog("setTitle","添加").dialog("center");
            //清空form表单
            menuDialogForm.form("clear");
        },
        edit:function(){
            //获取选中行
            var row = menuDatagrid.datagrid("getSelected");
            //判断是否选中
            if(!row){
                $.messager.alert("温馨提示","亲,请选择一行进行修改","info");
                return;
            }
            //打开模态框
            menuDialog.dialog("open").dialog("setTitle","添加").dialog("center");
            //回显form表单
            menuDialogForm.form("load",row);
        },
        delete:function(){
            //获取选中行
            var row = menuDatagrid.datagrid("getSelected");
            //判断是否选中
            if(!row){
                $.messager.alert("温馨提示","亲,请选择一行进行删除","info");
                return;
            }
            //询问是否删除
            $.messager.confirm("温馨提示","亲,你确定要删除吗?",function(r){
                if(r){
                    //发送ajax请求删除操作
                    $.get("menu/delete",{id:row.id},function (result) {
                        if(result.success){
                            $.messager.alert("温馨提示",result.message,"info",function(){
                                //刷新
                                menuDatagrid.datagrid("reload");
                            });
                        }else{
                            $.messager.alert("温馨提示",result.errorCode+"-"+result.message,"info");
                        }
                    },"json");
                }
            });
        },
        refresh:function(){
            // load  reload  loadData
            menuDatagrid.datagrid("reload");
        },
        //取消保存
        cancelSave:function(){
            menuDialog.dialog("close");
        },
        //保存
        menuSave:function(){
            //调用easyui form组件的submit方法 ajax方式提交
            //注意不要调用jquery的submit
            menuDialogForm.form('submit', {
                url:'menu/save',
                onSubmit: function(){
                    return menuDialogForm.form("validate");
                },
                success:function(data){
                    //这里的data是一个json字符串
                    var result = $.parseJSON(data);
                    if(result.success){
                        $.messager.alert("温馨提示",result.message,"info",function(){
                            //关掉dialog
                            menuDialog.dialog("close");
                            //刷新
                            menuDatagrid.datagrid("reload");
                        });
                    }else{
                        $.messager.alert("温馨提示",result.errorCode+"-"+result.message,"info");
                    }
                }
        });
        }
    }
    //初始化组件
    menuDatagrid.datagrid({
        url:'menu/list',
        title:"",
        fit:true,
        fitColumns:true,
        singleSelect:true,
        pagination:true,//分页
        rownumbers:true,
        toolbar:"#menuDatagridToolbar",
        columns:[[
                        {field:'id',title:'编号',width:100},
                        {field:'name',title:'菜单名称',width:100},
                        {field:'url',title:'菜单路径',width:100},
                        {field:'icon',title:'菜单图标',width:100},
                        {field:'parent',title:'菜单',width:100},
                    ]]
    });
    //初始化添加修改模态框
    menuDialog.dialog({
        width: 400,
        height: 300,
        closed: true,//默认关闭
        modal: true,
        buttons:"#menuDialogButtons"
    });
    //为页面的所有的按钮注册点击事件
    //1 只想为我们自己定义的按钮注册事件
    //2 事件的处理函数如何调用
    $("a").on("click",function(){
       //区分不同的按钮,调用指定的function
        //获取到data-cmd的值
        var cmd = $(this).data("cmd");
        //获取disabled属性值
        var options = $(this).linkbutton("options");
        if(cmd&&!options.disabled){
            //调用对应的function
            cmdObj[cmd]();
        }
    });


});*/
