<%--
  Created by IntelliJ IDEA.
  User: ligw
  Date: 2017/6/6
  Time: 1:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入页面变量配置--%>
<%@include file="/page/common/jsp/base.jsp" %>
<html>
<head>
    <%--引入基础设置--%>
    <%@include file="/page/common/jsp/baseInclude.jsp" %>
    <title>注册</title>
    <script type="text/javascript" src="${path}/page/common/js/layer/layer.js" charset="utf8"></script>
    <script type="text/javascript" src="${path}/page/common/js/common.js"></script>
    <!-- 用户中心部分通用css -->
    <link href="${path}/page/assets/css/bootstrap-datetimepicker.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" type="text/css" href="${path}/page/client/account/css/account.css">

    <!-- 注册页面js -->
    <script type="text/javascript" src="${path}/page/client/account/js/register.js" charset="utf8"></script>
    <%--<script type="text/javascript" src="${path}/page/assets/js/moment.min.js" charset="UTF-8"></script>--%>

    <%--<script type="text/javascript" src="${path}/page/assets/js/moment-locale.js" charset="UTF-8"></script>--%>
    <script type="text/javascript" src="${path}/page/assets/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>

    <%--<script type="text/javascript" src="${path}/page/assets/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>--%>
    <script type="text/javascript" src="${path}/page/assets/js/bootstrap-datetimepicker.zh-CN.js"
            charset="UTF-8"></script>

    <script type="text/javascript">
        var wxId = ${wxId}
            $(function () {

            });

    </script>

    <style>
        .row {
            margin: 0;
        }

        .col-lg-1, .col-lg-10, .col-lg-11, .col-lg-12, .col-lg-2, .col-lg-3, .col-lg-4, .col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9, .col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9, .col-sm-1, .col-sm-10, .col-sm-11, .col-sm-12, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9, .col-xs-1, .col-xs-10, .col-xs-11, .col-xs-12, .col-xs-2, .col-xs-3, .col-xs-4, .col-xs-5, .col-xs-6, .col-xs-7, .col-xs-8, .col-xs-9 {
            padding: 0;
        }

        .dropdown-menu {
            background-color: #2f8ad8;
            color: white;
        }

        .form-control {
            width: auto;
            min-width: 200px;
            right: 10px;
        }

        body {
            background: url("${path}/page/common/img/bg_login.png") no-repeat;
            background-size: 100% 100%;
        }

    </style>
</head>
<body>

<div class="top szwhite">
    <center><h1>注册</h1>
        <p>
            精彩瞬间、即刻分享
        </p>
    </center>
</div>


<!--content 内容区-->
<div class="container content">
    <form id="regform">
        <%--<input type="hidden" name="action" value="registerAccount">--%>
        <div class="row">

            <div class="col-xs-12 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4 ">

                <div class="registerpanel ">
                    <div class="inputmsg">
                        <span class="glyphicon glyphicon-phone szwhite pull-left"></span>
                        <input type="text" id="phoneNum" class="pull-left " name="mobelPhone" placeholder="手机号码"
                               required
                               autofocus>
                        <!--<span class="pull-left glyphicon glyphicon-remove" style="position:absolute;top:10px;left:10px;z-index:10"></span>-->
                    </div>
                    <div class="inputmsg">
                        <span class="glyphicon glyphicon-object-align-vertical pull-left szwhite"></span>
                        <div class="clearfix validate  ">
                            <input type="text " class="pull-left " id="validateCodeID" name="validateCode"
                                   placeholder="验证码">
                            <button type="button" id="sendValidateCode" class="pull-right" value="发送验证码">发送验证码</button>
                        </div>


                    </div>
                    <div class="inputmsg ">
                        <span class="glyphicon glyphicon-lamp pull-left szwhite"></span>
                        <input type="text" id="username" class="pull-left " name="nickName" placeholder="用户名">

                    </div>
                    <div class="inputmsg ">
                        <span class="glyphicon glyphicon-lock pull-left szwhite"></span>
                        <input type="text" class="pull-left " id="cc" name="password" placeholder="密码">

                    </div>
                    <div class="inputmsg ">
                        <span class="glyphicon glyphicon-lock pull-left szwhite"></span>
                        <input type="text" class="pull-left " id="email" name="passwordAgain" placeholder="确认密码">

                    </div>
                </div>

                <div class="linespace"></div>
                <div class="linespace"></div>

                <div class="registerpanel section-2">
                    <div class="inputmsg ">
                        <span class="glyphicon glyphicon-paperclip szwhite pull-left"></span>
                        <select id="gender" name="sex" class="pull-left">
                            <option value="1">男性</option>
                            <option value="0">女性</option>
                            <option value="-1">未知</option>

                        </select>
                        <input type="hidden" name="sex">
                        <br>
                    </div>
                    <div style="padding: 0 0;" class="form-group sec2">
                        <div class="input-group date form_datetime szblack" data-date="1979-09-16T05:25:07Z"
                             data-date-format="dd MM yyyy - HH:ii p" data-link-field="dtp_input1">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-th szwhite"></span></span>
                            <input style="padding: 10px 0 0;background: rgba(255,255,255,0.0);" id="birth"
                                   name="birthday" class="form-control" style="	background: rgba(255, 255, 255, 0.0);box-shadow: none;
" size="16" type="text" value="出生日期" readonly>
                        </div>
                        <input type="hidden" id="dtp_input1" value=""/>
                    </div>
                </div>
            </div>
        </div>
        <div class="linespace"></div>
        <div class="linespace"></div>

        <div class="col-xs-12 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4 ">

            <div class="row submitBtn">
                <input type="button" id="regBtn" placeholder="Password" value="注册">
            </div>
        </div>
    </form>
</div>

<div class="bottom footer szwhite">
    <div class="row">
        <div class="col-sm-12">
            <center> &copy; Bootstrap Login Form Templates by <a href="https://www.google.com" target="_blank">chrom</a>.
            </center>
        </div>
    </div>
</div>

</body>
</html>
