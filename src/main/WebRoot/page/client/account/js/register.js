var wxuId = wxId;
//初始化页面
$(document).ready(function () {

    $('.form_datetime').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });


    function listenPhoneNum() {
        var reg = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0-9]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;

        if (!reg.test($("#phoneNum").val())) {

            $("#phoneNum").val("请输入正确的手机号码!");
            $("#phoneNum").css("color", "red");
            return false;
        } else {

            $("#phoneNum").css("color", "#fff");
            return true;
        }
    }

    $("#phoneNum").change(function () {
        listenPhoneNum();
    });

    $("#username").change(function () {
        var reg = /^[a-zA-z]\w{3,15}$/;
        if (!reg.test($("#username").val())) {
            $("#username").val("请输入6-22位数字字母组合用户名!");
            $("#username").css("color", "red");
            return false;
        } else {
            $("#username").css("color", "#fff");
            return true;
        }
    });

    $("#password").change(function () {
        var reg = /^[a-zA-z]\w{3,15}$/;
        if (!reg.test($("#password").val())) {
            $("#password").val("请输入6-22位数字字母组合!");
            $("#password").css("color", "red");
            return false;
        } else {
            $("#password").css("color", "#fff");
            return true;
        }
    });

    $("#sendValidateCode").click(function () {
//                alert("sendValidateCode:---");

        if (!listenPhoneNum()) {
            return ;
        }


        var mobelPhone = $("#phoneNum").val();
        //发送验证码请求
        var timestamp = Date.parse(new Date());
        var url = ${path} + "/verificationMsg?action=sendMsg&mobelPhone="+mobelPhone+"&timeStamp="+timestamp;
//                alert("url:---"+url);
        ajaxRequest(url,"GET",function (flag,msg) {
            alert("msg---"+msg);
            if (flag == true && msg == 200){
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
//                        layer.msg("发送验证码失败!");

            }
        });
    });

    $('#regBtn').click(function () {

        if (!listenPhoneNum()) {
            return ;
        }

//                layer.load(2);

        var validateUrl =${path} + "/verificationMsg?action=sendMsg&mobelPhone="+$("#phoneNum").val()+"&timeStamp="+$("#validateCodeID").val();

//                ajaxRequest(validateUrl,"GET",function (flag,msg) {

//                    if (flag && msg["result"].equals("success")) {
//                        alert("请求成功");

        var sex=$("#gender").val();
        var birthday = new Date().getTime();
//                            $("#birth").val();

        var url=${path} + "/client/account?action=registAccount&mobelPhone="+$("#phoneNum").val()+"&wxId=wertyuioikjhgfdftgyhutu&password="+$("#password").val()+"&nickName="+$("#username").val()+"&birthday="+birthday+"&sex="+sex;
        ajaxRequest(url,"GET",function (flag,msg) {
//                            alert("msg:"+msg+"::"+JSON.parse(msg));

            var obj = JSON.parse(msg);

            if (flag && obj.result == ("success")){
                //请求成功
                $.ajax({
                    url: ${path} + "/client/account?action=login",
                    type: "post",
                    dataType: "json",
                    data: {
                        "wxId": wxuId,
                    },
                    success: function (data) {

                    }
                });
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
                layer.msg("请求失败！");
            }
        });
//                    }else{
//                        layer.msg("验证码错误");
//                    }

//                });

    });
});
