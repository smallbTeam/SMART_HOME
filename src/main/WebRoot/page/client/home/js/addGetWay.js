$(function(){
    alert("[appId:" + appId + "][timestamp:" + timestamp + "][nonceStr:" + nonceStr + "][signature:" + signature + "]");
    wx.config({
        debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: appId, // 必填，公众号的唯一标识
        timestamp: timestamp, // 必填，生成签名的时间戳
        nonceStr: nonceStr, // 必填，生成签名的随机串
        signature: signature,// 必填，签名，见附录1
        jsApiList: ['configWXDeviceWiFi'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });

    wx.ready(function(){
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

    wx.error(function(res){
        // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
        alert("error");
        alert(JSON.stringify(res));
    });



})
