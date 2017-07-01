<%--
  Created by IntelliJ IDEA.
  User: ligw
  Date: 2017/6/6
  Time: 2:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入页面变量配置--%>
<%@include file="/page/common/jsp/base.jsp" %>
<html>
<head>
    <%--引入基础设置--%>
    <%@include file="/page/common/jsp/baseInclude.jsp" %>
    <title>设备列表</title>
        <script type="text/javascript" src="${path}/page/js/index.js" charset="utf8"></script>
    <script type="text/javascript">
        var account = {"id":'${account.id}',
            "mobelPhone":'${account.MobelPhone}',
            "wxId":'${account.WxId}',
            "nickName":'${account.NickName}',
            "birthday":'${account.Birthday}',
            "sex":'${account.Sex}',
            "reserve":'${account.Reserve}',
            "token":'${account.Token}'
        }
        $(function () {
            alert("登录手机号：["+account.mobelPhone+"]");
        });

        $(document).ready(function () {
            $("#toAddgatewaypage_btn").on("click",function () {
                alert("用户："+account.mobelPhone+"添加网关");
                window.location.href = "${path}/client/home?action=addGetway&mobelPhone=" + account.mobelPhone;
                layer.closeAll();
            });
        });
    </script>
    <!-- home部分通用css -->
    <link rel="stylesheet" type="text/css" href="${path}/page/css/main.css">
    <style>
        .row {
            margin: 0;
        }

        .col-lg-1, .col-lg-10, .col-lg-11, .col-lg-12, .col-lg-2, .col-lg-3, .col-lg-4,
        .col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9, .col-md-1, .col-md-10,
        .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6,
        .col-md-7, .col-md-8, .col-md-9, .col-sm-1, .col-sm-10, .col-sm-11, .col-sm-12,
        .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8,
        .col-sm-9, .col-xs-1, .col-xs-10, .col-xs-11, .col-xs-12, .col-xs-2, .col-xs-3,
        .col-xs-4, .col-xs-5, .col-xs-6, .col-xs-7, .col-xs-8, .col-xs-9 {
            padding: 0;
        }
    </style>
</head>
<body>

<input type="button" value="扫描添加网关" id="toAddgatewaypage_btn">

</body>
</html>
