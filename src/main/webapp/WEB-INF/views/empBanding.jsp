<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>第三方账号绑定</title>
    <link rel="stylesheet" type="text/css" href="/static/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/reset.css"/>
</head>
<body>
<div class="wrap login_wrap" style="width: 100%;height: 100%;background: url('/static/images/logo_bg.jpg')">
    <div class="content">

        <div class="logo"></div>

        <div class="login_box">

            <div class="login_form" style="height: 280px">
                <div class="login_title">
                    第三方账号绑定
                </div>
                <form id="registerForm" action="/binding" method="post">

                    <div class="form_text_ipt">
                        <input name="username" type="text" placeholder="用户名">
                    </div>
                    <div class="ececk_warning"><span>用户名不能为空</span></div>
                    <div class="form_text_ipt">
                        <input name="password" type="password" placeholder="密码">
                    </div>
                    <div class="ececk_warning"><span>密码不能为空</span></div>
                    <div class="form_btn">
                        <button type="submit">绑定</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/static/js/common.js"></script>
</body>
</html>
