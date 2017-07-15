
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
                <div class="line"  id="genderDiv" >
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
                <div class="line"  id="birthDiv" >
                    <img src="${path}/page/img/icon/blue.png" class="pull-left"/>
                    <span class="pull-left">出生日期</span>
                    <i class="arrowRight pull-right" ></i>
                    <span id="birth" class="right-content pull-right"></span>
                </div>
                <div class="line"  id="nickNameDiv">
                    <img src="${path}/page/img/icon/blue.png" class="pull-left"/>
                    <span class="pull-left">用户名</span>
                    <i class="arrowRight pull-right" ></i>
                    <span id="nickName" class="right-content pull-right"></span>
                </div>
                <div class="line" id="phoneNumDiv">
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

        $("#phoneNumDiv").click(function () {
            var dialog = '<div class="box">' +
                '<form >' +
                '<div class="form-group">' +
                '<label for="name">手机号码</label>' +
                '<input type="text" class="form-control" id="invate_phoneNum" placeholder="请输入手机号"  required>' +

//                            '<label for="name">网关IP</label>'+
//                            '<input type="text" class="form-control" id="add_gatewayIP" placeholder="请输入网关IP">'+

                '</div>' +
                '<div class="form-group">' +
                '</div>' +
//                            '<div id="addGatewaySubmit" class="btn-default" >提交</div>'+
                '</form>' +
                '</div>';


            layer.confirm(dialog, {
                title: "更改手机号",
                btn: ["提交"], //按钮
//                            width: "100%"
            }, function () {
                $.ajax({
                    url: "${path}/client/account?action=accountUpdateMobile",
                    type: "GET",
                    data: {
                        mobelPhone: $("#invate_phoneNum").val(),
                        customerId: account.id
                    },
                    dataType: "json",
                    success: function (result) {
                        //console.log(result);
                        if (result.operationResult == 1) {
                            layer.msg("更新成功");
                            $("#phoneNum").val( $("#invate_phoneNum").val());

                        } else {
                            layer.msg("发送邀请失败");
                        }
                    },
                    error: function () {
                        layer.msg("请求失败！");

                    }
                });
            });
        });
        $("#genderDiv").click(function () {
            var dialog = '<div class="box">' +
                '<form >' +
                '<div class="form-group">' +
                '<label for="name">性别</label>' +
                ' <select id="up_gender" class="">'+
            ' <option value="1">男性</option>'+
            ' <option value="0">女性</option>'+
            ' <option value="-1">未知</option>'+

            ' </select>'+

//                            '<label for="name">网关IP</label>'+
//                            '<input type="text" class="form-control" id="add_gatewayIP" placeholder="请输入网关IP">'+

                '</div>' +
                '<div class="form-group">' +
                '</div>' +
//                            '<div id="addGatewaySubmit" class="btn-default" >提交</div>'+
                '</form>' +
                '</div>';


            layer.confirm(dialog, {
                title: "修改信息",
                btn: ["提交"], //按钮
//                            width: "100%"
            }, function () {
                $.ajax({
                    url: "${path}/client/account?action=updateAccount",
                    type: "GET",
                    data: {
                        sex: $("#up_gender").val(),
                        customerId: account.id
                    },
                    dataType: "json",
                    success: function (result) {
                        //console.log(result);
                        if (result.operationResult == 1) {
                            layer.msg("更新成功");
                            $("#gender").val( $("#up_gender").val());


                        } else {
                            layer.msg("修改失败");
                        }
                    },
                    error: function () {
                        layer.msg("请求失败！");

                    }
                });
            });
        });
        $("#nickNameDiv").click(function () {
            var dialog = '<div class="box">' +
                '<form >' +
                '<div class="form-group">' +
                '<label for="name">用户名</label>' +
                '<input type="text" class="form-control" id="up_nickName" placeholder="请输入用户名"  required>' +



//                            '<label for="name">网关IP</label>'+
//                            '<input type="text" class="form-control" id="add_gatewayIP" placeholder="请输入网关IP">'+

                '</div>' +
                '<div class="form-group">' +
                '</div>' +
//                            '<div id="addGatewaySubmit" class="btn-default" >提交</div>'+
                '</form>' +
                '</div>';


            layer.confirm(dialog, {
                title: "更改用户名",
                btn: ["提交"], //按钮
//                            width: "100%"
            }, function () {
                $.ajax({
                    url: "${path}/client/account?action=updateAccount",
                    type: "GET",
                    data: {
                        nickName: $("#up_nickName").val(),
                        customerId: account.id
                    },
                    dataType: "json",
                    success: function (result) {
                        //console.log(result);
                        if (result.operationResult == 1) {
                            layer.msg("更新成功");
                            $("#nickName").val( $("#up_nickName").val());

                        } else {
                            layer.msg("发送邀请失败");
                        }
                    },
                    error: function () {
                        layer.msg("请求失败！");

                    }
                });
            });
        });

    });

</script>

</body>
</html>