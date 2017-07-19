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
<script type="text/javascript" src="${path}/page/js/third/jweixin-1.2.0.js" charset="utf8"></script>
<%@include file="/page/common/jsp/baseInclude.jsp" %>
<title>添加网关</title>
<link rel="stylesheet" type="text/css" href="${path}/page/css/register.css"/>
<link rel="stylesheet" type="text/css" href="${path}/page/css/site.css">
<link rel="stylesheet" id="cal_style" type="text/css" href="${path}/page/css/flatpickr.min.css">
<style>
    .center-block{
        float: none;
    }

    .spec-middle{
        width: 100%;
        position: absolute;
        top: 50%;
        transform: translate(0, -50%);
    }

</style>
<script type="text/javascript">
    var appId = '${appid}';
    var timestamp = '${timestamp}';
    var nonceStr = '${noncestr}';
    var signature = '${signaturet}';
    var account = {"id":'${account.customerId}',
        "mobelPhone":'${account.mobelPhone}',
        "wxId":'${account.wxId}',
        "nickName":'${account.nickName}',
        "birthday":'${account.birthday}',
        "sex":'${account.sex}',
        "token":'${account.token}'
    }

    $(function () {
        //alert("登录手机号：["+account.mobelPhone+"]");
        //alert("[appId:" + appId + "][timestamp:" + timestamp + "][nonceStr:" + nonceStr + "][signature:" + signature + "]");
    });
</script>
<script type="text/javascript" src="${path}/page/js/addGetWay.js" charset="utf8"></script>
</head>
<body>
<div class="spec-middle center-block">
            <div class="col-xs-12 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4 ">
                <div class="row-item">
                <img src="${path}/page/img/icon/deviceNo.png" class="icon pull-left"/>
                <img  id="scanfordevice_btn" src="${path}/page/img/icon/scanicon.png" class=" remark pull-right"/>
                    <img style="margin-top: 10px;margin-right: 5px" src="${path}/page/img/visible.png" class="remark pull-right"/>
                <div class="row-center">
                    <input style="width: 80%" id="deviceNo" type="text" readonly="true" class="deviceNo" placeholder="扫描添加设备">
                </div>
            </div>
            <div class="row-item">
                <img src="${path}/page/img/icon/wangguan.png" class="icon pull-left"/>
                <img src="${path}/page/img/visible.png" class="remark pull-right"/>
                <div class="row-center">
                    <input id="wangguan" type="text" class="wangguan" placeholder="网关名称">
                </div>
            </div>
            <div class=" btn-content">
                <button id="addgetway_btn">添加</button>
            </div>
        </div>
        <div class="clearfix"></div>
</div>

</body>
</html>
