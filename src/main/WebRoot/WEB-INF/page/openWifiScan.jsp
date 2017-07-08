<%--
  Created by IntelliJ IDEA.
  User: ligw
  Date: 2017/7/8
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入页面变量配置--%>
<%@include file="/page/common/jsp/base.jsp" %>
<html>
<head>
    <%--引入基础设置--%>
    <%@include file="/page/common/jsp/baseInclude.jsp" %>
        <script type="text/javascript" src="${path}/page/js/third/jweixin-1.2.0.js" charset="utf8"></script>
    <title>Wifi扫描添加设备</title>
    <script>
        var appId = '${appid}';
        var timestamp = '${timestamp}';
        var nonceStr = '${noncestr}';
        var signature = '${signaturet}';
        var account = {
            "id": '${account.id}',
            "mobelPhone": '${account.MobelPhone}',
            "wxId": '${account.WxId}',
            "nickName": '${account.NickName}',
            "birthday": '${account.Birthday}',
            "sex": '${account.Sex}',
            "reserve": '${account.Reserve}',
            "token": '${account.Token}'
        };
        $(function () {
            alert("登录手机号：["+account.mobelPhone+"]");
            alert("[appId:" + appId + "][timestamp:" + timestamp + "][nonceStr:" + nonceStr + "][signature:" + signature + "]");

            wx.config({
                debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                appId: appId, // 必填，公众号的唯一标识
                timestamp: timestamp, // 必填，生成签名的时间戳
                nonceStr: nonceStr, // 必填，生成签名的随机串
                signature: signature,// 必填，签名，见附录1
                jsApiList: ['configWXDeviceWiFi'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
            });

            wx.ready(function () {
                // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
                wx.checkJsApi({
                    jsApiList: ['configWXDeviceWiFi'],
                    success: function (res) {
                        alert("checksuccess");
                        WeixinJSBridge.invoke('configWXDeviceWiFi', {}, function (res) {
                            alert("errmsg：[" + JSON.stringify(res) + "]");
                            var err_msg = res.err_msg;
                            if (err_msg == 'configWXDeviceWiFi:ok') {
                                // $('#message').html("配置 WIFI成功，<span id='second'>5</span>秒后跳转到首页。");
                                // setInterval(count, 1000);
                                alert("配置 WIFI成功");
                                window.location.href = "${path}/client/home?action=index&mobelPhone=" + account.mobelPhone;
                            } else {
                                // $('#message').html("配置 WIFI失败，是否<a href=\"/wechat/scan/airkiss" + window.location.search + "\">再次扫描</a>。<br>不配置WIFI,<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf1867e87a4eeeb16&redirect_uri=http://letux.xyz/wechat/page/main&response_type=code&scope=snsapi_base&state=1#wechat_redirect\">直接进入首页</a>。");
                                alert("配置 WIFI失败");
                            }
                        });
                    }
                });
            });

            wx.error(function (res) {
                // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
                alert("error");
                alert("error:[" + JSON.stringify(res) + "]");
            });

        });

    </script>
</head>
<body>

</body>
</html>
