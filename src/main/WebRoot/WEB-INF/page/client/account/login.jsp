<%--
  Created by IntelliJ IDEA.
  User: ligw
  Date: 2017/6/6
  Time: 1:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入页面变量配置--%>
<%@include file="/page/common/jsp/base.jsp" %>
<html>
<head>
    <%--引入基础设置--%>
    <%@include file="/page/common/jsp/baseInclude.jsp" %>
    <title>登录</title>
        <style>
            body{
                background: url("${path}/page/common/img/bg_login.png") no-repeat;
                background-size: 100% 100% ;
            }
        </style>
    <script type="text/javascript">
        var wxId = ${wxId};

        $(function () {

        });

        //初始化页面
        $(document).ready(function () {

        });
    </script>
    <!-- 用户中心部分通用css -->
    <link rel="stylesheet" type="text/css" href="${path}/page/client/account/css/account.css">
    <!-- 登录页面js -->
    <script type="text/javascript" src="${path}/page/client/account/js/login.js" charset="utf8"></script>
</head>
<body>
<div class="top ">
    <center><h1>登录</h1>
        <p>
            精彩瞬间、即刻分享
        </p>
    </center>
</div>

<!--content 内容区-->
<div class="container content">
    <form >
        <div class="row" >

            <div class="col-xs-12 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4 ">

                <div class="bottommsg">
                    <div class=" inputmsg">
                        <span class="glyphicon glyphicon-phone szwhite pull-left"></span>
                        <input type="text" class="pull-left " id="mobelPhone"  name="username" placeholder="手机号码" required autofocus>

                    </div>
                    <div class="inputmsg ">
                        <span class="glyphicon glyphicon-lock pull-left szwhite"></span>
                        <input type="text" class=" pull-left" id="password" name="password" placeholder="密码" >

                    </div>
                </div>
            </div>
        </div>
        <div class="linespace"></div>
        <div class="linespace"></div>

        <div class="col-xs-12 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4 ">

            <div class="row submitBtn">
                <input type="submit" id="loginBtn" name="password" placeholder="Password" value="登录">
                <span class="pull-right">忘记密码</span>
            </div>
        </div>
    </form>
</div>


<div class="bottom footer">
    <div class="row">
        <div class="col-sm-12">
            <center> 	&copy; Bootstrap Login Form Templates by <a href="http://azmind.com" target="_blank">Azmind</a>.
            </center>
        </div>
    </div>
</div>

</body>
</html>
