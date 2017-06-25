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

        <!-- 注册页面js -->
        <script type="text/javascript" src="${path}/page/client/account/js/register.js" charset="utf8"></script>
        <%--<script type="text/javascript" src="${path}/page/assets/js/moment.min.js" charset="UTF-8"></script>--%>


        <%--日期控件--%>
        <script src="${path}/page/assets/js/third/flatpickr.js"></script>
        <!--<script async src="${path}/page/assets/js/third/prettify.js?skin=none" onload="prettyPrint()"></script>-->
        <!--<script async src="assets/table-of-contents.js"></script>-->
        <!--<script async src="assets/themer.js"></script>-->
        <%--<script async id="locale_script" src="${path}/page/assets/js/third/flatpickr.l10n.zh.js" onload="redraw()"></script>--%>
        <!--<script async src="assets/localizr.js"></script>-->

        <link rel="stylesheet" type="text/css" href="${path}/page/assets/css/third/site.css">
        <link rel="stylesheet" id="cal_style" type="text/css" href="${path}/page/assets/css/third/flatpickr.min.css">

        <%----%>
        <!-- 用户中心部分通用css -->
        <link rel="stylesheet" type="text/css" href="${path}/page/client/account/css/account.css">

        <script type="text/javascript">
        $(function () {



        });



        //初始化页面
        $(document).ready(function () {
            Flatpickr.l10n.weekdays.shorthand = ['周日', '周一', '周二', '周三', '周四', '周五', '周六'];
            Flatpickr.l10n.months.longhand= ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'];

            document.getElementById('birth').flatpickr();

            function isNotNull(id) {
                if ($(id).val() != "" && $(id).val() != null && $(id).val() != undefined) {
                    return true;
                }
                return false;
            }
            var wxId = "<%=request.getParameter("code")%>";


             function listenPhoneNum() {
                var reg = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0-9]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;

                if (!reg.test($("#phoneNum").val())) {
                    $("#phoneNum").val("");
                    $("#phoneNum").attr('placeholder',"请输入正确的手机号码!");
                    return false;
                } else {

                    return true;
                }
            }

            function listenField(id) {
                var reg = /^[a-zA-z]\w{3,15}$/;
                if (!reg.test($(id).val())) {
//                    $("#password").val("请输入6-22位数字字母组合!");
//                    $(id).css("color", "red");
                    $(id).val("");
                    $(id).attr('placeholder',"请输入6-22位数字字母组合!");

                    return false;
                } else {
                    return true;
                }
            }


            $("#phoneNum").change(function () {
                listenPhoneNum();
            });

            $("#username").change(function () {
                listenField("#username")
            });

            $("#password").change(function () {
                listenField("#password");
            });

            $("#passwordAgain").change(function () {

               if (listenField("#passwordAgain")){

                    if ($("#passwordAgain").val().equals($("#password").val()) && isNotNull("password")) {

                        return true;
                    }else{
                        layer.msg("val:"+$(this).val()+"；f："+$("#password").val());
                        $(this).val("");
                        $(this).attr('placeholder',"密码输入不一致");
                        return false;
                    }

                }
            });

            $("#sendValidateCode").click(function () {
                if (!listenPhoneNum()) {
                    return ;
                }

                var mobelPhone = $("#phoneNum").val();
                //发送验证码请求
                var timestamp = Date.parse(new Date());
                var url = "${path}/verificationMsg?action=sendMsg&mobelPhone="+mobelPhone+"&timeStamp="+timestamp;

                $.get(url,function (msg) {
                    if ( msg.result == 'success'){
                        //请求成功
                        var countdown = 60;
                        var _this = $(this);
                        $("#sendValidateCode").attr("disabled","true");
                        $("#sendValidateCode").text(countdown+"秒后重发");
                        $("#phoneNum").attr("disabled","true");

                        var timer = setInterval(function () {

                            if (countdown-0 > 1) {
                                --countdown;
                                $("#sendValidateCode").text(countdown + "秒后重新获取");
                            }else{

                                clearInterval(timer);
                                $("#sendValidateCode").removeAttr("disabled");
                                $("#phoneNum").removeAttr("disabled");

                                $("#sendValidateCode").text("重新发送验证码");
                            }
                        },1000);

                    }else{
                        layer.msg("发送验证码失败!");
                    }
                });
            });

            $('#regBtn').click(function () {

                if (!listenPhoneNum()) {
                    return false;
                }


                if (!isNotNull("#validateCodeID")){
                    layer.msg("验证码还未填写哦！"+$("#validateCodeID").val());
                    return ;
                }

                if (!isNotNull("#username")){
                    layer.msg("验证码还未填写哦！"+$("#validateCodeID").val());
                    return ;
                }

                if (!isNotNull("#password")){
                    layer.msg("密码还未填写哦！");
                    return ;
                }
                if (!isNotNull("#passwordAgain")){
                    layer.msg("请再次输入密码哦！");
                    return ;
                }

                if ($("#passwordAgain").val() == $("#password").val() && isNotNull("password")) {
                    layer.msg("俩次密码输入不一致，请重新输入！");
                    return ;
                }

                if (!isNotNull("#gender")){
                    layer.msg("性别还未填写哦！");
                    return ;
                }
                if (!isNotNull("#birth")){
                    layer.msg("出生日期还未填写哦！");
                    return ;
                }


                var validateUrl = "http://localhost:8080/smarthome/verificationMsg?action=veridateMsg&mobelPhone="+$("#phoneNum").val()+"&veridateMsg="+$("#validateCodeID").val();
                $.get(validateUrl,function (msg1) {
//                    alert("13652091037:"+msg1.operationResult);
                    if (msg1.operationResult) {

                        var sex=$("#gender").val();
                        var str = $("#birth").val(); // 日期字符串
                        str = str.replace(/-/g,'/'); // 将-替换成/，因为下面这个构造函数只支持/分隔的日期字符串
                        var birthday = new Date(str).getTime();

//                            new Date().getTime();

                        var url="${path}/client/account?action=registAccount&mobelPhone="+$("#phoneNum").val()+"&wxId=wertyuioikjhgfdftgyhutu&password="+$("#password").val()+"&nickName="+$("#username").val()+"&birthday="+birthday+"&sex="+sex;

                        if ( wxId !== null || wxId !== undefined || wxId !== '' ) {
                            url="${path}/client/account?action=registAccount&mobelPhone="+$("#phoneNum").val()+"&wxId=wertyuioikjhgfdftgyhutu&password="+$("#password").val()+"&nickName="+$("#username").val()+"&birthday="+birthday+"&sex="+sex+"&wxId="+wxId;
                        }

                $.get(url,function (msg) {
                            if (msg.result == 'success'){
                                //请求成功
                                window.location.href = "${path}/client/account?action=login";
                                layer.closeAll();
                            }else{
                                //请求失败
//                        layer.open({
//                            type: 1,
//                            title: false,
//                            closeBtn: 1,
//                            shadeClose: true,
//                            skin: 'dialog',
//                            content: '请求失败'
//                        });
                                layer.msg("注册失败！");
                            }
                            return false;
                        });
                    }else{
                        layer.msg("验证码错误");
                    }

                });


            });
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


        body{
            background: url("${path}/page/common/img/bg_login.png") no-repeat;
            background-size: 100% 100% ;
        }

        .inputmsg input{
            -webkit-appearance: none;
            border-radius: 0;
            -webkit-border-radius: 0;
        }

    </style>
</head>
<body>



<!--content 内容区-->
<div class="container content">
    <form id="regform">
        <%--<input type="hidden" name="action" value="registerAccount">--%>
        <div class="row">

            <div class="col-xs-12 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4 ">

                <div class="registerpanel ">
                    <div class="inputmsg">
                        <span class="glyphicon glyphicon-phone szwhite pull-left"></span>
                        <input type="text" id="phoneNum" class="pull-left " name="mobelPhone" placeholder="手机号码" required
                               autofocus>
                        <!--<span class="pull-left glyphicon glyphicon-remove" style="position:absolute;top:10px;left:10px;z-index:10"></span>-->
                    </div>
                    <div class="inputmsg">
                        <span class="glyphicon glyphicon-object-align-vertical pull-left szwhite"></span>
                        <div class="clearfix validate  ">
                            <input type="text " class="pull-left " id="validateCodeID" name="validateCode" placeholder="验证码">
                            <button type="button" id="sendValidateCode" class="pull-right" value="发送验证码">发送验证码</button>
                        </div>


                    </div>
                    <div class="inputmsg ">
                        <span class="glyphicon glyphicon-lamp pull-left szwhite"></span>
                        <input type="text" id="username" class="pull-left " name="nickName" placeholder="用户名">

                    </div>
                    <div class="inputmsg ">
                        <span class="glyphicon glyphicon-lock pull-left szwhite"></span>
                        <input type="password" class="pull-left " id="password" name="password" placeholder="密码"  aria-describedby="basic-addon1">

                    </div>
                    <div class="inputmsg ">
                        <span class="glyphicon glyphicon-lock pull-left szwhite"></span>
                        <input type="password" class="pull-left " id="passwordAgain" name="passwordAgain" placeholder="确认密码"  aria-describedby="basic-addon1">

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
                       <div class="inputmsg">
                           <div class=" example  sec2 szwhite " data-desc="A basic datepicker" id="basic">
                               <span class="glyphicon glyphicon-th szwhite pull-left" ></span>
                               <input style="padding: 0px 0 0;background: rgba(255,255,255,0.0);" id="birth" placeholder="出生日期.." class="pull-left"/>

                           </div>
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
        </div>
    </form>
</div>
<div class="bottom footer">
    <div class="row">
        <div class="col-sm-12">
            <center> &copy; SS <a href="http://azmind.com" target="_blank">Azmind</a>.
            </center>
        </div>
    </div>
</div>

</body>
</html>
