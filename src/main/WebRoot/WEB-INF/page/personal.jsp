
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
    <link rel="stylesheet" type="text/css" href="${path}/page/css/personal.css">
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

<!--<div style="clear:both;">-->
<!--<script src="/gg_bd_ad_720x90.js" type="text/javascript"></script>-->
<!--<script src="/follow.js" type="text/javascript"></script>-->
<!--</div>-->

<nav>
    <div class="menuleft">
        <div class="dropdownIcon">
            <i class="arrowUp"></i>
        </div>
    </div>
    <div id="titleV" class="titleView">
        <span>个人信息</span>
    </div>
    <!--<div class="menu">-->
        <!--<i class="return"></i>-->
    <!--</div>-->
    <!--<ul id="rightM" class="dropDown">-->
        <!--<li><a href="#"><i class="personal"></i> Menu</a></li>-->
        <!--<li><a href="#">Menu Item 2</a></li>-->
        <!--<li><a href="#">Menu Item 3</a></li>-->
    <!--</ul>-->

</nav>

<!--内容区 -->
<section id="gateWay_content" class="gateWay_content">
    <div class="top">
        <div id="topContent" class="topContent">
            <div class="row">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 ">
                    <!--<h3 class="title"><span>用户资料</span>Qixu Lorem</h3>-->
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 ">

            <div id class="common-detail">
                <div class="line">
                    <img src="${path}/page/img/icon/blue.png" class="pull-left"/>
                    <span class="pull-left">性别</span>
                    <i class="arrowRight pull-right" ></i>
                    <span id="gender" class="right-content pull-right"></span>

                </div>
                <%--<div class="line">--%>
                    <%--<img src="${path}/page/img/icon/blue.png" class="pull-left"/>--%>
                    <%--<span class="pull-left">年龄</span>--%>
                    <%--<i class="arrowRight pull-right" ></i>--%>
                    <%--<span id="age" class="right-content pull-right">女</span>--%>
                <%--</div>--%>
                <div class="line">
                    <img src="${path}/page/img/icon/blue.png" class="pull-left"/>
                    <span class="pull-left">出生日期</span>
                    <i class="arrowRight pull-right" ></i>
                    <span id="birth" class="right-content pull-right"></span>
                </div>
                <div class="line">
                    <img src="${path}/page/img/icon/blue.png" class="pull-left"/>
                    <span class="pull-left">用户名</span>
                    <i class="arrowRight pull-right" ></i>
                    <span id="nickName" class="right-content pull-right"></span>
                </div>
                <div class="line">
                    <img src="${path}/page/img/icon/blue.png" class="pull-left"/>
                    <span class="pull-left">手机号码</span>
                    <i class="arrowRight pull-right" ></i>
                    <span id="phoneNum" class="right-content pull-right"></span>
                </div>
            </div>
        </div>
    </div>
    <div class="gateWay_detail">
        <div class="row">
            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 ">

            </div><!--col-xs-12-->
        </div> <!--row-->
    </div><!--gateWay_detail-->

</section>




<script src='js/jquery.js'></script>

<script>
    $(document).ready(function () {
        $(".menuleft").click(function () {
            window.history.back();
        });

        var account = {"id":'${account.id}',
            "mobelPhone":'${account.MobelPhone}',
            "wxId":'${account.WxId}',
            "nickName":'${account.NickName}',
            "birthday":'${account.Birthday}',
            "sex":'${account.Sex}',
            "reserve":'${account.Reserve}',
            "token":'${account.Token}'
        };

        $("#gender").val(account.sex);
        $("#birth").val(account.birthday);
        $("#phoneNum").val(account.mobelPhone);
        $("#nickName").val(account.nickName);


    });

</script>

</body>
</html>