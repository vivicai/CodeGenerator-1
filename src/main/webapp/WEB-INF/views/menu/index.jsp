    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%-- 此部分代码做如果项目配置运行时有path的时候使用 --%>
            <%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" +     request.getServerPort() + request.getContextPath();
    System.out.println(basePath);
%>
        <html>
        <head>
        <base href="<%=basePath%>"/>
        <title>菜单管理</title>
        <%@ include file="/WEB-INF/views/head.jsp"%>
        <%@ include file="/WEB-INF/views/common.jsp"%>
        <script type="text/javascript" src="/static/js/common.js"></script>
        <script type="text/javascript" src="/static/js/model/menu.js"></script>
        <style>
        body{margin: 10px;}
        .demo-carousel{height: 200px; line-height: 200px; text-align: center;}
        </style>
        </head>
        <body class="layui-anim layui-anim-up">
        <div class="x-nav">
        <span class="layui-breadcrumb">
        <a href="/main">首页</a>
        <a href="/main">系统管理</a>
        <a><cite>菜单管理</cite></a>
        </span>
        <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">&#xe669;</i></a>
        </div>

        <div class="x-body">
        <%--<div class="layui-row">
            <form class="layui-form layui-col-md12 x-so">
            <input class="layui-input" placeholder="开始日" name="start" id="start">
            <input class="layui-input" placeholder="截止日" name="end" id="end">
            <input type="text" name="username"  placeholder="请输入用户名" autocomplete="off" class="layui-input">
            <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
            </form>
        </div>--%>
        <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so" id="zq_search">
        菜单：<input id="name" type="text" name="name"  placeholder="请输入菜单" autocomplete="off" class="layui-input">
        <button id="search" class="layui-btn"  lay-submit="" lay-filter="search"><i class="layui-icon">&#xe615;</i></button>
        </form>
        </div>

        <table class="layui-hide" id="zq_table" lay-filter="zq_table"></table>

        </div>

        <div class="layui-row" id="zq_formpopbox" style="display:none;position: absolute;
        top: 0; left: 0; bottom: 0; right: 0;">
        <div class="layui-col-md11">
        <form id="zq_form" class="layui-form" action="" style="margin-top: 20px;align:center;">
        <%--隐藏字段id,区分添加和修改--%>
        <input type="hidden" name="id"/>
        <%--lay-verify验证的值：
         required（必填项）,phone（手机号）,email（邮箱）
         url（网址）,number（数字）,date（日期）,identity（身份证）
         自定义值--%>
        <div class="layui-form-item">
        <label class="layui-form-label">菜单名称</label>
        <div class="layui-input-block">
        <input type="text" name="name" id="name" lay-verify="required" placeholder="请输入菜单名称" autocomplete="off" class="layui-input">
        </div>
        </div>
        <div class="layui-form-item">
        <label class="layui-form-label">菜单路径</label>
        <div class="layui-input-block">
        <input type="text" name="url" id="url" lay-verify="" placeholder="请输入菜单路径" autocomplete="off" class="layui-input">
        </div>
        </div>
        <div class="layui-form-item">
        <label class="layui-form-label">菜单图标</label>
        <div class="layui-input-block">
        <input type="text" name="icon" lay-verify="" placeholder="请输入菜单图标" autocomplete="off" class="layui-input">
        </div>
        </div>
        <div class="layui-form-item">
        <label class="layui-form-label">菜单</label>
        <div class="layui-input-block">
        <input type="text" name="parent" lay-verify="" placeholder="请输入菜单" autocomplete="off" class="layui-input">
        </div>
        </div>
        <div class="layui-form-item">
        <label class="layui-form-label">子菜单</label>
        <div class="layui-input-block">
        <input type="text" name="children" lay-verify="" placeholder="请输入子菜单" autocomplete="off" class="layui-input">
        </div>
        </div>

        <div class="layui-form-item">
        <div class="layui-input-block">
        <button class="layui-btn layui-btn-radius layui-btn-normal" lay-submit="" lay-filter="zq_submit" <%--onclick="zq_submit()"--%>>确认</button>
        <%--<input type="button" class="layui-btn layui-btn-radius layui-btn-normal" value="确认" onclick="zq_submit()" />--%>
        <button type="reset" class="layui-btn layui-btn-radius layui-btn-primary">重置</button>
        </div>
        </div>
        </form>
        </div>
        </div>

        <%-- 解决对象格式化问题
          注意：{{d.id}} 是动态内容，它对应数据接口返回的字段名。除此之外，你还可以读取到以下额外字段：
              序号：{{ d.LAY_INDEX }} （该额外字段为 layui 2.2.0 新增）--%>
        <script type="text/html" id="zq_formatter">
        {{#  if( d.parent != null){ }}
        {{d.parent.name}}
        {{#  }  }}
        </script>


        <%-- 这里放头工具栏按钮 id和table头的toolbar属性绑定--%>
        <script type="text/html" id="zq_toolbar">
        <div class="layui-btn-container">
        <button class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delAll"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn layui-btn-sm" lay-event="add"  <%--onclick=""--%>><i class="layui-icon"></i>添加</button>
        </div>
        </script>

        <%-- 这里放CRUD行工具栏按钮 id和table行的toolbar属性绑定--%>
        <script type="text/html" id="zq_bar">
        <a <%--class="layui-btn layui-btn-primary layui-btn-xs"--%> lay-event="detail" title="查看"><i class="layui-icon">&#xe63c;</i></a>
        <a <%--class="layui-btn layui-btn-xs"--%> lay-event="edit" title="编辑"><i class="layui-icon">&#xe642;</i></a>
        <a <%--class="layui-btn layui-btn-danger layui-btn-xs"--%> lay-event="del" title="删除"><i class="layui-icon">&#xe640;</i></a>
        </script>

        <!--_footer 作为公共模版分离出去-->
        <%@ include file="/WEB-INF/views/footer.jsp"%>
        </body>
        </html>
