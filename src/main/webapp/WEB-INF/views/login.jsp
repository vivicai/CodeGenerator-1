<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" +request.getServerPort() + request.getContextPath();
    System.out.println(basePath);
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>4s维修管理后台登录</title>
    <%@ include file="/WEB-INF/views/head.jsp"%>
    <%@ include file="/WEB-INF/views/common.jsp"%>
    <script type="text/javascript" src="/static/js/prefixfree.min.js"></script>
    <script type="text/javascript" src="/static/js/stopExecutionOnTimeout.js"></script>
    <style class="cp-pen-styles">body {
        background: radial-gradient(200% 100% at bottom center, #0070aa, #0b2570, #000035, #000);
        background: radial-gradient(220% 105% at top center, #000 10%, #000035 40%, #0b2570 65%, #0070aa);
        background-attachment: fixed;
        overflow: hidden;
    }

    @keyframes rotate {
        0% {
            transform: perspective(400px) rotateZ(20deg) rotateX(-40deg) rotateY(0);
        }
        100% {
            transform: perspective(400px) rotateZ(20deg) rotateX(-40deg) rotateY(-360deg);
        }
    }
    .stars {
        transform: perspective(500px);
        transform-style: preserve-3d;
        position: absolute;
        bottom: 0;
        perspective-origin: 50% 100%;
        left: 50%;
        animation: rotate 90s infinite linear;
    }

    .star {
        width: 2px;
        height: 2px;
        background: #F7F7B6;
        position: absolute;
        top: 0;
        left: 0;
        transform-origin: 0 0 -300px;
        transform: translate3d(0, 0, -300px);
        backface-visibility: hidden;
    }
    </style>

</head>
<%--<div class="stars">--%>

<%--</div>--%>

<script>
    $(document).ready(function () {
        var stars = 800;
        var $stars = $('.stars');
        var r = 800;
        for (var i = 0; i < stars; i++) {
            if (window.CP.shouldStopExecution(1)) {
                break;
            }
            var $star = $('<div/>').addClass('star');
            $stars.append($star);
        }
        window.CP.exitedLoop(1);
        $('.star').each(function () {
            var cur = $(this);
            var s = 0.2 + Math.random() * 1;
            var curR = r + Math.random() * 300;
            cur.css({
                transformOrigin: '0 0 ' + curR + 'px',
                transform: ' translate3d(0,0,-' + curR + 'px) rotateY(' + Math.random() * 360 + 'deg) rotateX(' + Math.random() * -50 + 'deg) scale(' + s + ',' + s + ')'
            });
        });
    });

</script>
<body class="login-bg">
<div class="stars">

</div>

<div class="login layui-anim layui-anim-up">
    <div class="message">4s维修管理登录</div>
    <div id="darkbannerwrap"></div>

    <form method="post" class="layui-form" >
        <input name="username" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" value="老大">
        <hr class="hr15">
        <input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input" value="123456">
        <hr class="hr15">
        <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
        <hr class="hr20" >
    </form>
    其他方式登录：
    <a href="${authUrl}"><img style="width: 25px;height: 25px;" src="/static/images/Github.png" alt="github"></a>
</div>

<script>
    $(document).ready(function () {
        var stars = 800;
        var $stars = $('.login layui-anim layui-anim-up');
        var r = 800;
        for (var i = 0; i < stars; i++) {
            if (window.CP.shouldStopExecution(1)) {
                break;
            }
            var $star = $('<div/>').addClass('star');
            $stars.append($star);
        }
        window.CP.exitedLoop(1);
        $('.star').each(function () {
            var cur = $(this);
            var s = 0.2 + Math.random() * 1;
            var curR = r + Math.random() * 300;
            cur.css({
                transformOrigin: '0 0 ' + curR + 'px',
                transform: ' translate3d(0,0,-' + curR + 'px) rotateY(' + Math.random() * 360 + 'deg) rotateX(' + Math.random() * -50 + 'deg) scale(' + s + ',' + s + ')'
            });
        });
    });
    $(function  () {
        layui.use('form', function () {
            var form = layui.form;
            // layer.msg('玩命卖萌中', function(){
            //   //关闭后的操作
            //   });
            //监听提交
            form.on('submit(login)', function (data) {
                var formData = data.field;
                var username = formData.username;//用户名
                var password = formData.password;//密码
                $.ajax({
                    type: "post",  //数据提交方式（post/get）
                    url: "/login",  //提交到的url
                    data: {
                        "username": username,
                        "password": password
                    },//提交的数据

                    dataType: "json",//返回的数据类型格式
                    success: function (data) {
                        // console.debug(eval("("+JSON.stringify(data)+")") );
                        var result = eval("("+JSON.stringify(data)+")");
                        if (result.success) {  //成功
                            // layer.msg(result.msg, {icon: 1, time: 1500});
                            window.location.href ="/main";
                        } else {  //失败
                            layer.alert(result.msg, {icon: 2}, function () {
                            });
                        }
                    }
                });
                return false;//false：阻止表单跳转  true：表单跳转
            });
        })
    })
</script>
</body>
</html>