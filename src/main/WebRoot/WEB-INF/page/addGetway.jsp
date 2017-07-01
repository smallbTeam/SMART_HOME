<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/24
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入页面变量配置--%>
<%@include file="/page/common/jsp/base.jsp" %>
<html>
<%--引入基础设置--%>
<%@include file="/page/common/jsp/baseInclude.jsp" %>
<title>登录</title>
<script type="text/javascript" src="${path}/page/js/third/jweixin-1.2.0.js" charset="utf8"></script>
<style>
    body {
        background: url("${path}/page/common/img/bg_login.png") no-repeat;
        background-size: 100% 100%;
    }
</style>
<script type="text/javascript">
    var appId = '${appid}';
    var timestamp = '${timestamp}';
    var nonceStr = '${noncestr}';
    var signature = '${signaturet}';
    $(function(){
        console.log("[appId:" + appId + "][timestamp:" + timestamp + "][nonceStr:" + nonceStr + "][signature:" + signature + "]");
    });
    //初始化页面
    $(document).ready(function () {

    });
</script>
<!-- 登录页面js -->
<script type="text/javascript" src="${path}/page/js/addGetWay.js" charset="utf8"></script>
</head>
<body>
<div class="container content">
    <form>
        <div class="row">
            <div class="col-xs-12 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4 ">
                <div class="bottommsg">
                    <div class=" inputmsg">
                        <span class="glyphicon glyphicon-phone szwhite pull-left"></span>
                        <input type="text" class="pull-left " id="deviceNo" name="deviceNo" placeholder="设备号">
                    </div>
                    <div class="inputmsg ">
                        <span class="glyphicon glyphicon-lock pull-left szwhite"></span>
                        <input type="text" class=" pull-left" id="address" name="address" placeholder="地址">
                    </div>
                </div>
            </div>
        </div>

        <div class="col-xs-12 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4 ">
            <div class="row submitBtn">
                <input  type="button" id="addGetway" value="添加网关">
                <span class="pull-right">忘记密码</span>
            </div>
        </div>
    </form>
</div>

<script>

    $(document).ready(function () {
        $('#addGetway').click(function () {
            alert("触发按钮")
            <%--window.location.href = "${path}/client/home?action=index";--%>
            wx.checkJsApi({
                jsApiList: ['configWXDeviceWiFi'],
                success: function(res) {
                    alert("checksuccess");
                    WeixinJSBridge.invoke('configWXDeviceWiFi', {'key':'wnqE4KH53r7UVwEs'}, function(res){
                        alert("errmsg：["+JSON.stringify(res)+"]");
                        var err_msg = res.err_msg;
                        if(err_msg == 'configWXDeviceWiFi:ok') {
                            $('#message').html("配置 WIFI成功，<span id='second'>5</span>秒后跳转到首页。");
                            setInterval(count,1000);
                            return;
                        } else {
                            $('#message').html("配置 WIFI失败，是否<a href=\"/wechat/scan/airkiss" + window.location.search +  "\">再次扫描</a>。<br>不配置WIFI,<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf1867e87a4eeeb16&redirect_uri=http://letux.xyz/wechat/page/main&response_type=code&scope=snsapi_base&state=1#wechat_redirect\">直接进入首页</a>。");
                        }
                    });
                }
            });
        });
    });

</script>

</body>
</html>
