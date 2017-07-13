$(function () {
    alert("[appId:" + appId + "][timestamp:" + timestamp + "][nonceStr:" + nonceStr + "][signature:" + signature + "]");
    wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: appId, // 必填，公众号的唯一标识
        timestamp: timestamp, // 必填，生成签名的时间戳
        nonceStr: nonceStr, // 必填，生成签名的随机串
        signature: signature,// 必填，签名，见附录1
        jsApiList: ['scanQRCode', 'configWXDeviceWiFi'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });

    wx.ready(function () {
        // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。

    });

    wx.error(function (res) {
        // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
        alert("error");
        alert("error:[" + JSON.stringify(res) + "]");
    });
});

$(document).ready(function () {
    $("#addgetway_btn").click(function () {
        var res = /^[\S]{1,}$/;
        if (!res.test($("#deviceNo").val())) {
            $("#deviceNo").parent().prev("img").attr("src", path+"/page/img/icon/failed.png");
            $("#deviceNo").attr('placeholder', "请扫描添加设备!");
        } else {
            $("#deviceNo").parent().prev("img").attr("src", path+"/page/img/icon/success.png");
            if (!res.test($("#wangguan").val())) {
                $("#wangguan").parent().prev("img").attr("src", path+"/page/img/icon/failed.png");
                $("#wangguan").attr('placeholder', "请填写网关所在地址!");
            } else {
                $("#wangguan").parent().prev("img").attr("src", path+"/page/img/icon/success.png");
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
                                $.ajax({
                                    url: path + "/client/device?action=addGatewayForCustomer",
                                    type: "POST",
                                    data: {
                                        customerId: account.id,
                                        gatewaySerialNumber: $("#deviceNo").val(),
                                        gatewayName: $("#wangguan").val()
                                    },
                                    dataType: "json",
                                    success: function (result) {
                                        console.log(result);
                                        if (result.result == "success") {
                                            if (result.operationResult == 0){
                                                alert("网关已被添加，请联系网管所有者");
                                            } else if (result.operationResult == 1){
                                                alert("网关成功");
                                            } else {
                                                alert("添加网关异常")
                                            }
                                        } else {
                                            alert("添加网关失败："+result.error)
                                        }
                                    },
                                    error: function () {
                                        alert("添加网关失败");

                                    }
                                });
                            } else {
                                // $('#message').html("配置 WIFI失败，是否<a href=\"/wechat/scan/airkiss" + window.location.search + "\">再次扫描</a>。<br>不配置WIFI,<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf1867e87a4eeeb16&redirect_uri=http://letux.xyz/wechat/page/main&response_type=code&scope=snsapi_base&state=1#wechat_redirect\">直接进入首页</a>。");
                                alert("配置 WIFI失败");
                            }
                            window.location.href = path+"/client/home?action=index&mobelPhone=" + account.mobelPhone;
                        });
                    }
                });
            }
        }
    });
    $("#scanfordevice_btn").click(function () {
        alert("cominto QRCode Scan");
        wx.scanQRCode({
            needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
            scanType: ["qrCode", "barCode"], // 可以指定扫二维码还是一维码，默认二者都有
            success: function (res) {
                alert("res：[" + JSON.stringify(res) + "]");
                var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
                alert("result：[" + result + "]");
                $("#deviceNo").val(result);
            }
        });
    });
});


