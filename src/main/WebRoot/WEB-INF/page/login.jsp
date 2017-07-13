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
    <script type="text/javascript">
        var wxId = ${wxId};

        $(function () {

        });

        //初始化页面
        $(document).ready(function () {

        });
    </script>
    <link rel="stylesheet" type="text/css" href="${path}/page/css/register.css">
    <!-- 登录页面js -->
    <script type="text/javascript" src="${path}/page/js/index.js" charset="utf8"></script>
    <style>
        .row {
            margin: 0;
        }
        .col-lg-1, .col-lg-10, .col-lg-11, .col-lg-12, .col-lg-2, .col-lg-3, .col-lg-4, .col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9, .col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9, .col-sm-1, .col-sm-10, .col-sm-11, .col-sm-12, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9, .col-xs-1, .col-xs-10, .col-xs-11, .col-xs-12, .col-xs-2, .col-xs-3, .col-xs-4, .col-xs-5, .col-xs-6, .col-xs-7, .col-xs-8, .col-xs-9 {
            padding: 0;
        }
    </style>
</head>
<body>

<div class="top-icon">
    <div class="logo">
        <img src="${path}/page/img/icon/logo.png"/>
    </div>
</div>

<canvas id="contentConvas">
</canvas>

<div class="container reg-content">
    <div class="row">
        <div class="col-sm-8 col-sm-offset-2 col-xs-12 col-md-6 col-md-offset-3 col-lg-4 col-lg-offset-4">
            <div class="login-section">
                <div id="phone" class="row-item">
                    <img src="${path}/page/img/icon/phone.png" class=" icon pull-left"/>
                    <!--<img src="img/icon/success.png" class=" remark pull-right"/>-->
                    <div class="row-center">
                        <input id="phoneNumber" type="text" class="" placeholder="手机号码">
                    </div>
                </div>

                <div class="row-item">
                    <img src="${path}/page/img/icon/pwd.png" class=" icon pull-left"/>
                    <!--<img src="img/icon/success.png" class=" remark pull-right"/>-->
                    <div class="row-center">
                        <input type="text" id="password" class="" placeholder="密码">
                    </div>
                </div>

                <div class=" btn-content">
                    <button id="accountLogin">登录</button>
                </div>
                <div class="forgot">
                    <button class="btn-right pull-right">忘记密码?</button>
                    <button class="btn-left pull-left">注册</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        var canvas = document.getElementById("contentConvas");
        var context = canvas.getContext("2d");
        context.strokeStyle = "#ffffff";
        context.fillStyle = "#ffffff";
        context.lineWidth = "1";
        context.beginPath();
        context.moveTo(0, 0);
        context.quadraticCurveTo(canvas.width / 4, 45, canvas.width * 0.6, 35);
        context.quadraticCurveTo(canvas.width * 0.8, 30, canvas.width, 50);
        context.lineTo(canvas.width, canvas.height);
        context.lineTo(0, canvas.height);
        context.lineTo(0, 0);
        context.scale(4, 4);
        context.closePath();
//        context.stroke();
        context.fill();
        $("#phoneNumber").change(function () {
//            var validate = ' <div id="validate" class="validate">'+
//                '<img src="img/icon/validate.png" class=" icon pull-left"/>'+
//                '<button class=" pull-right">发送验证码</button>'+
//                '<div class="row-center">'+
//                '<input type="text" class="" placeholder="验证码">'+
//                '</div>'+
//                '</div>';
            $('#validate').slideDown("slow");
        });
        $("#accountLogin").on("click",function(){
            $('#accountLogin').text('登录中...');
            $.ajax({
                url:"${path}/client/account?action=accountLogin",
                type:"post",
                data:{
                    "mobelPhone":$("#phoneNumber").val(),
                    "password":$("#password").val(),
                    "wxid":wxId,
                },
                dataType:"json",
                success:function(data){
                    if(data.result == "success" && null != data.operationResult
                        && "" != data.operationResult){
                        if((0 == data.operationResult)){
                            alert("手机号或密码错误");
                            $('#accountLogin').text('登录');
                        } else {
                            window.location.href = "${path}/client/home?action=index&mobelPhone=" + $("#phoneNumber").val();
                        }
                    } else {
                        alert("系统错误");
                        $('#accountLogin').text('登录');
                    }
                },
                error:function(e) {
                    alert("系统错误");
                    $('#accountLogin').text('登录');
                    console.log(e);
                }
            });
        });

    });
</script>
</body>
</html>
