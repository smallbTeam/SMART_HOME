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
                <div class="line" id="genderDiv">
                    <img src="${path}/page/img/icon/blue.png" class="pull-left"/>
                    <span class="pull-left">性别</span>
                    <i class="arrowRight pull-right"></i>
                    <span id="gender" class="right-content pull-right"></span>

                </div>
                <%--<div class="line">--%>
                <%--<img src="${path}/page/img/icon/blue.png" class="pull-left"/>--%>
                <%--<span class="pull-left">年龄</span>--%>
                <%--<i class="arrowRight pull-right" ></i>--%>
                <%--<span id="age" class="right-content pull-right">女</span>--%>
                <%--</div>--%>
                <div class="line" id="birthDiv">
                    <img src="${path}/page/img/icon/blue.png" class="pull-left"/>
                    <span class="pull-left">出生日期</span>
                    <i class="arrowRight pull-right" style="opacity: 0"></i>
                    <span id="birth" class="right-content pull-right"></span>
                </div>
                <div class="line" id="nickNameDiv">
                    <img src="${path}/page/img/icon/blue.png" class="pull-left"/>
                    <span class="pull-left">用户名</span>
                    <i class="arrowRight pull-right"></i>
                    <span id="nickName" class="right-content pull-right"></span>
                </div>
                <div class="line" id="phoneNumDiv">
                    <img src="${path}/page/img/icon/blue.png" class="pull-left"/>
                    <span class="pull-left">手机号码</span>
                    <i class="arrowRight pull-right"></i>
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

<script src='${path}/page/js/jquery.js'></script>

<script>
    $(document).ready(function () {
        $(".menuleft").click(function () {
            window.history.back();
        });

        <%--var account = {"id":'${account.id}',--%>
        <%--"mobelPhone":'${account.MobelPhone}',--%>
        <%--"wxId":'${account.WxId}',--%>
        <%--"nickName":'${account.NickName}',--%>
        <%--"birthday":'${account.Birthday}',--%>
        <%--"sex":'${account.Sex}',--%>
        <%--"reserve":'${account.Reserve}',--%>
        <%--"token":'${account.Token}'--%>
        <%--};--%>
        var account = {
            "id": '${account.customerId}',
            "mobelPhone": '${account.mobelPhone}',
            "wxId": '${account.wxId}',
            "nickName": '${account.nickName}',
            "birthday": '${account.birthday}',
            "sex": '${account.sex}',
            "token": '${account.token}'
        };
        var timer;
        if (account.sex == 2) {
            $("#gender").html("女");
        } else if (account.sex == 1) {
            $("#gender").html("男");
        } else {
            $("#gender").html("未知");
        }
//        $("#gender").html(account.sex);
        $("#birth").html(account.birthday);
        $("#phoneNum").html(account.mobelPhone);
        $("#nickName").html(account.nickName);


        var isSendCode = false;

        function sendCode() {
            if ($("#up_phoneNum").val() == "" || $("#up_phoneNum").val() == null) {
                alert("请输入手机号！");
                return;
            }
            //发送验证码请求
            //console.log("[" + url + "]");
            $.ajax({
                url: "${path}/client/account?action=accountIsExit",
                type: "post",
                data: {
                    mobelPhone: $("#up_phoneNum").val()
                },
                dataType: "json",
                success: function (result) {
                    if (result.result == "success") {
                        if (result.operationResult) {
                            alert("手机号已注册！！");
                        } else {
                            $.ajax({
                                url: "${path}/verificationMsg?action=sendMsg",
                                type: "GET",
                                data: {
                                    mobelPhone: $("#up_phoneNum").val()
                                },
                                dataType: "json",
                                success: function (result) {
                                    //console.log(result);
                                    //alert("result:" + result.result);
                                    if (result.result == "success") {
                                        isSendCode = true;
                                        var countdown = 60;
                                        var _this = $(this);
                                        $("#sendCode").attr("disabled", "true");
                                        $("#sendCode").text(countdown + "秒后重发");
                                        $("#up_phoneNum").attr("disabled", "true");
                                        timer = setInterval(function () {
                                            if (countdown - 0 > 1) {
                                                --countdown;
                                                $("#sendCode").text(countdown + "秒后重新获取");
                                            } else {
                                                clearInterval(timer);
                                                $("#sendCode").removeAttr("disabled");
                                                $("#up_phoneNum").removeAttr("disabled");
                                                $("#sendCode").text("重新发送验证码");
                                            }
                                        }, 1000);
                                    } else {
                                        layer.msg("发送验证码失败!");
                                    }
                                },
                                error: function () {
                                    layer.msg("程序繁忙，请稍后重试。！");
                                }
                            });
                        }
                    } else {
                        layer.msg("系统繁忙，请稍后再试");
                    }
                }
            });
        }

        $("#phoneNumDiv").click(function () {
            var dialog = '<div class="box">' +
                '<form >' +
                '<div class="form-group">' +
                '<input type="text" class="form-control" id="up_phoneNum" placeholder="请输入手机号"  required>' +

                '</div>' +
                '</form>' +
//                '<form class="form-inline" role="form">'+
                '<div class="form-group">' +
                '<label class="sr-only" for="name">名称</label>' +
                ' <input type="text" id="veridateMsg" class="form-control" id="name"' +
                'placeholder="请输入验证码">' +
                '</div>' +
                '<div class="form-group">' +
                '<button id="sendCode" class="btn btn-default">发送验证码</button>' +
                '</div>' +
//                '</form>'+
                '</div>';

            layer.confirm(dialog, {
                title: "更改手机号",
                btn: ["提交"], //按钮
//                            width: "100%"
            }, function () {
                clearInterval(timer);
                if ($("#up_phoneNum").val() == "" || $("#up_phoneNum").val() == null) {
                    //                //console.log("false");
                    alert("请输入手机号!");
                    return;
                }

                if ($("#veridateMsg").val() == "" || $("#veridateMsg").val() == null) {
                    alert("请输入验证码!");
                    return;
                }

                alert("13652091037:" + $("#veridateMsg").val());

                $.ajax({
                    url: "${path}/client/account?action=accountUpdateMobile",
                    type: "GET",
                    data: {
                        newMobelPhone: $("#up_phoneNum").val(),
                        customerId: account.id,
                        veridateMsg: $("#veridateMsg").val()
                    },
                    dataType: "json",
                    success: function (result) {
                        //console.log(result);
                        if (result.result == "success" && result.operationResult == 1) {
                            layer.msg("更新成功");
                            $("#phoneNum").html($("#up_phoneNum").val());
                        } else if (result.operationResult == 1) {

                        } else if (result.result == "success") {
                            layer.msg("更新失败:" + result.operationResul);
                        } else {
                            layer.msg("更新失败:" + result.error);
                        }
                    },
                    error: function () {
                        layer.msg("程序繁忙，请稍后重试。！");

                    }
                });
            });

            $("#sendCode").click(function () {
                sendCode();
            });
        });
        $("#genderDiv").click(function () {
            var dialog = '<div class="box">' +
                '<form >' +
                '<div class="form-group">' +
                '<label for="name">性别</label>' +
                '</div>' +
                '<div class="form-group">' +
                '<select id="up_gender" class="form-control">' +
                ' <option value="1">男性</option>' +
                ' <option value="2">女性</option>' +
                ' <option value="0">未知</option>' +

                ' </select>' +

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
                        if (result.result == "success") {
                            layer.msg("更新成功");
                            if ($("#up_gender").val() ==1) {
                                $("#gender").html("男");
                            } else if ($("#up_gender").val() == 2) {
                                $("#gender").html("女");
                            } else {
                                $("#gender").html("未知");
                            }

                        } else {
                            layer.msg("修改失败");
                        }
                    },
                    error: function () {
                        layer.msg("程序繁忙，请稍后重试。！");

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
                        if (result.result == "success") {
                            layer.msg("更新成功");
                            $("#nickName").html($("#up_nickName").val());

                        } else {
                            layer.msg("更新失败");
                        }
                    },
                    error: function () {
                        layer.msg("程序繁忙，请稍后重试。！");

                    }
                });
            });
        });

    });

</script>

</body>
</html>
