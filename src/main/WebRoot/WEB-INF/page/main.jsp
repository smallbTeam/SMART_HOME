<%--
  Created by IntelliJ IDEA.
  User: ligw
  Date: 2017/6/6
  Time: 2:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入页面变量配置--%>
<%@include file="/page/common/jsp/base.jsp" %>
<html>
<head>
    <%--引入基础设置--%>
    <%@include file="/page/common/jsp/baseInclude.jsp" %>
    <title>设备列表</title>
        <script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>
    <!-- home部分通用css -->
    <link rel="stylesheet" type="text/css" href="${path}/page/css/main.css">
    <style>
        .row {
            margin: 0;
        }

        .col-lg-1, .col-lg-10, .col-lg-11, .col-lg-12, .col-lg-2, .col-lg-3, .col-lg-4,
        .col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9, .col-md-1, .col-md-10,
        .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6,
        .col-md-7, .col-md-8, .col-md-9, .col-sm-1, .col-sm-10, .col-sm-11, .col-sm-12,
        .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8,
        .col-sm-9, .col-xs-1, .col-xs-10, .col-xs-11, .col-xs-12, .col-xs-2, .col-xs-3,
        .col-xs-4, .col-xs-5, .col-xs-6, .col-xs-7, .col-xs-8, .col-xs-9 {
            padding: 0;
        }

        /*无数据页面内容*/
        #nodata {
            width: 100%;
            height: auto;
            margin: 120px 0;
            /*padding: 0 100px;*/
            /*text-align: center;*/
            /*background: #00b300;*/
            display: block;
            position: absolute;
        }

        #nodata img {
            width: 150px;
            height: auto;
            margin-left: 50%;
            -webkit-transform: translate(-50%, 0);
            -moz-transform: translate(-50%, 0);
            -ms-transform: translate(-50%, 0);
            -o-transform: translate(-50%, 0);
            transform: translate(-50%, 0);
        }

        #nodata .label-title {
            margin-top: 20px;
            font-size: 16px;
            color: rgba(9, 7, 8, 0.5);
            font-size: 17px;
            /*margin-bottom: 10px;*/
            text-align: center;
            font-weight: 500;
            line-height: 1.1;
            width: auto;
        }

        #nodata .label-title span {
            color: #2BB4EA;
            cursor: pointer;
        }

    </style>

    <script>
        function isExist(divId) {
            if ($(divId).length && $(divId).length > 0) {
                return true;
            }
            return false;
        }
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

        });

    </script>

    <script>
        $(document).ready(function () {
            alert("come into main");
//           webscoket
            var ws = null;

//                网关列表数组
            var gatewayArray = new Array();
            var deviceArray = new Array();
            var current_gateway;

            $("#openAirKiss_btn").click(function () {
                window.location.href = "${path}/client/home?action=openWifiScan&mobelPhone=" + account.mobelPhone;
            });

//            webscoket
            function WebSocketTest() {
                if ('WebSocket' in window) {
                    ws = new WebSocket('ws://s-357114.gotocdn.com/smart_home/webSocketServer');
                    //ws = new WebSocket('ws://127.0.0.1:9080/smarthome/webSocketServer');
                }
                else if ('MozWebSocket' in window) {
                    ws = new MozWebSocket("ws://s-357114.gotocdn.com/smart_home/webSocketServer");
                }
                else {
                    ws = new SockJS("http://s-357114.gotocdn.com/smart_home/sockjs/webSocketServer");
                }
                // 打开一个 web socket
                ws.onopen = function () {
                        // Web Socket 已连接上，使用 send() 方法发送数据
                };

                ws.onmessage = function (evt) {
                    var msg = evt.data;
                    alert("msg:"+msg);
                    var jsonmsg = JSON.parse(msg);
                    $("#device_pm_info").html(jsonmsg.pm);
                    $("#device_shidu_info").html(jsonmsg.shidu);
                    $("#device_wendu_info").html(jsonmsg.wendu);
                };

                ws.onclose = function () {

                };

            }
            window.onbeforeunload = function () {
                ws.close();
            }
            WebSocketTest();

//                假数据
//                account.id = '58';
//                account.mobelPhone = '13652091037';
//                var index = layer.load(1, {
//                    shade: [0.1,'#fff'] //0.1透明度的白色背景
//                });

            $('.dropDown').mouseleave(function () {
                $('.dropDown').slideUp("slow", function () {
                    $(this).fadeOut(2000);
                });

            });


//        网关切换，页面数据重新加载
            function reloadPageContent(gateway) {
                if (!gateway) {
                    return;
                }
                $("#page-content").css("display", "block");
                $("#nodata").css("display", "none");
                $("#gatewayIP").html(gateway.address);
                $("#gatewayName").html(gateway.address);
                $("#gatewayStatus").html("isOn");

                $.ajax({
                    url: "${path}/client/home?action=getDeviceListByGatewayId",
                    type: "GET",
                    data: {
                        gatewayDeviceID: gateway.id
                    },
                    dataType: "json",
                    success: function (result) {
                        if (result.result == "success") {
                            var wenduval = "温度：";
                            var shiduval = "空气湿度：";
                            var pm = "PM2.5：";
                            for (var i in result.operationResult) {
                                var itemD = result.operationResult[i];
                                console.log("设备信息："+JSON.stringify(itemD));
//                            alert("itm:"+itemD.id);
                                if (itemD.deviceTypeName == "wendu") {
                                    wenduval += itemD.DeviceData;
                                } else if (itemD.deviceTypeName == "shidu") {
                                    shiduval += itemD.DeviceData;
                                } else if (itemD.deviceTypeName == "pm") {
                                    pm += itemD.DeviceData;
                                } else {
                                    var deviceItem = {
                                        "deviceTypeAttention": itemD.deviceTypeAttention,
                                        "DeviceData": itemD.DeviceData,
                                        "deviceTypeId": itemD.deviceTypeId,
                                        "DeviceNo": itemD.DeviceNo,
                                        "gatewayIP": itemD.gatewayIP,
                                        "deviceTypeName": itemD.deviceTypeName,
                                        "gatewayGatewayPort": itemD.gatewayGatewayPort,
                                        "id": itemD.id,
                                        "deviceGetwayId": itemD.deviceGetwayId,
                                        "deviceName": itemD.deviceName,
                                        "deviceTypeModel": itemD.deviceTypeModel,
                                        "deviceTypeDescribtion": itemD.deviceTypeDescribtion,
                                        "deviceState": itemD.deviceState
                                    };

                                    if ($.inArray(deviceItem, deviceArray) == -1) {

                                        deviceArray.push(deviceItem);
                                        //向设备列表区域添加每条设备信息
                                        var html = '<div id="list-content_' + deviceItem.id + '" class="list-content">' +
                                            '<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 ">' +
                                            '<div class="list-item">' +
                                            '<div class="list-item-content">' +
                                            '<div class="leftContent">' +
                                            '<div id="deviceMenu_' + deviceItem.id + '" class="device-menu">' +
                                            '<img src="${path}/page/img/icon/ison.png"/>' +
                                            '</div>' +
                                            ' <span id="deviceStatus_' + deviceItem.id + '" class="subtitle">状态：开启中</span>' +
                                            '</div>' +
                                            '<div class="rightContent">' +
                                            '<div class="topLabel">' +
                                            '<div class="title">海尔变频空调</div>' +
                                            '<span class="subline">型号：x30698</span>' +
                                            '<span class="subline">设备编号：0102030</span>' +
                                            '<span class="contentline">设备状态：良好</span>' +
                                            '<br/>' +
                                            '<span class="contentline">设备类型：空调</span>' +
                                            '<br/>' +
                                            '<span class="contentline">设备类型：空调</span>' +
                                            '</div>' +
                                            '<div class="bottomLabel ">' +
                                            '<div class="bottomLabel-item pull-left"><img src="${path}/page/img/icon/blue.png"/><span>正常</span></div>' +
                                            '<div class="bottomLabel-item pull-left"><img src="${path}/page/img/icon/blue.png"/><span>18c</span></div>' +
                                            '<div class="bottomLabel-item pull-left"><img src="${path}/page/img/icon/blue.png"/><span>100</span></div>' +
                                            '</div>' +
                                            '<div class="list-item-hover">' +
                                            '<div id="delete_' + deviceItem.id + '" class="item-icon ">' +
                                            '<img src="${path}/page/img/icon/delete.png" alt="img30"/>' +
                                            '<span>删除</span>' +
                                            '</div>' +
                                            '<div id="edit_' + deviceItem.id + '" class="item-icon ">' +
                                            '<img src="${path}/page/img/icon/edit.png" alt="img30"/>' +
                                            '<span>编辑</span>' +
                                            '</div>' +
                                            '<div id="detail_' + deviceItem.id + '" class="item-icon ">' +
                                            '<img src="${path}/page/img/icon/detail.png" alt="img30"/>' +
                                            '<span>详情</span>' +
                                            '</div>' +
                                            '  </div>' +
                                            ' </div>' +
                                            ' </div>' +
                                            '</div>' +
                                            '</div><!--list-content col-xs-12 -->' +
                                            '</div><!--list-content -->';

                                        $("#devicelistPanel").append(html);
                                        $('#delete_' + deviceItem.id).click(function () {
//                                alert("delete:list-content_"+$(this).attr("id").split("_")[1]);

                                            var id = $(this).attr("id").split("_")[1];
                                            $.ajax({
                                               url: "${path}/client/home?action=delDeviceById",
                                                data: {
                                                    DeviceId: id
                                                },
                                                success: function (msg) {
                                                   if (msg.result == "success") {
                                                       $("#list-content_" + id).remove();

                                                   }else{
                                                       layer.msg("删除失败");
                                                   }
                                                },
                                            error: function () {
                                                   layer.msg("删除失败");
                                            }
                                            });
                                        });
                                        $('#edit_' + deviceItem.id).click(function () {
                                            var id = $(this).attr("id").split("_")[1];
                                        });

                                        $('#detail_' + deviceItem.id).click(function () {
                                            var id = $(this).attr("id").split("_")[1];
                                            window.location.href = "${path}/client/home?action=deviceList&deviceId=" + id;
                                        });
                                        $('#deviceMenu_' + deviceItem.id).click(function () {
                                            var id = $(this).attr("id").split("_").last();
                                        });
                                    }
                                }
                            }
                            $('#device_shidu_info').html(shiduval);
                            $('#device_wendu_info').html(wenduval);
                            $('#device_pm_info').html(pm);
                        }

                        if (!isExist("#btnadddevice")) {
                            var btnadddevice = '<div id="btnadddevice" class="list-content">' +
                                '<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 ">' +
                                '<div id="btn-adddevice" class="btn-adddevice">' + '' +
                                '<button >添加设备' +
                                '</button>' +
                                '</div>' +
                                '</div><!--list-content col-xs-12 -->' +
                                '</div><!--list-content -->';
                            $("#devicelistPanel").append(btnadddevice);
                            $("#btn-adddevice").click(function () {
                                addDevice();
                            });
                        }
                    },
                    error: function () {
                        layer.error();
                    }
                });

            }

//        数据请求
            function refresh() {
                $.ajax({
                    url: "${path}/client/home?action=getGatewayListByCustomerId",
                    type: "GET",
                    data: {
                        customerId: account.id
                    },
                    dataType: "json",
                    success: function (result) {
//                        console.log(result);
                        if (result.result == "success") {
//                            alert("itemId:"+JSON.stringify(result));
                            var jsons = result.operationResult;
                            for (var i in jsons) {
                                var item = jsons[i];
//                                alert("item:"+item.gatewayDeviceID);
                                var gatewayItem = {
                                    "id": item.gatewayDeviceID,
//                                        "gatewayPort": item.gatewayPort,
//                                        "ip": item.address,
                                    "address": item.address
//                                        "modifiedDate": item.modifiedDate,
//                                        "reserve": item.reserve
                                };
                                if ($.inArray(gatewayItem, gatewayArray) == -1) {
                                    alert("gatewayItem"+JSON.stringify(gatewayItem));
                                    gatewayArray.push(gatewayItem);
                                    $("#leftM").prepend('<li id="gateWayId_' + gatewayItem.id + '"><a href="#">' + gatewayItem.address + '</a></li>');
                                    $('#gateWayId_' + gatewayItem.id).click(function () {
                                        $("#devicelistPanel").empty();
                                        var id = $(this).attr("id").split("_")[1];

//                                        if ($('#rightM').slideDown){
//                                            $('#rightM').slideUp("slow");
//                                        }
                                        $('#leftM').slideUp("slow");

                                        for (var i in gatewayArray){
                                            if (gatewayArray[i].id == id) {
//                                                alert(current_gateway.id);
                                                current_gateway = gatewayArray[i];
                                                ws.send(current_gateway.id);
                                                reloadPageContent(current_gateway);
                                            }
                                        }

                                    });
                                }
                            }

                            if (gatewayArray.length > 0) {

//                                alert(current_gateway.id);
                                current_gateway = gatewayArray[0];
                                alert("gatewayItemID"+current_gateway.id);
                                ws.send(current_gateway.id);
                                reloadPageContent(current_gateway);
                            }
                            if (!isExist("#gateWayId_nomore")) {
                                $("#leftM").append('<li id="gateWayId_nomore"><a href="#">没有更多数据了哦！</a></li>');
                            }
                        } else {
                            layer.alert(result.error);
                            $("#leftM").append('<li id="gateWayId_nomore"><a href="#">没有更多数据了哦！</a></li>');

                        }
                    },
                    error: function () {
                        layer.error();
                        $("#leftM").append('<li id="gateWayId_nomore"><a href="#">没有更多数据了哦！</a></li>');

                    }
                });

            }

            //请求页面数据
            refresh();



            function addDeviceDialog(deviceTypes) {
                var dialog = '<div id="addDeviceDialog" class="box">' +
                    '<form role="form" >' +
                    '<div class="form-group">' +
                    '<label for="name">设备类型</label>' +
                    '<select id="deviceType"  class="form-control" >';

                for (var i in deviceTypes) {
                    dialog += '<option value="' + deviceTypes[i].id + '">' + deviceTypes[i].name + '</option>';
                }
                dialog += '</select>';
                if (deviceTypes.length <= 0) {
                    dialog += '<div class="click-title">当前无设备类型，<span id="addDeviceType">立即添加?</span></div>';
                }

                dialog += '<div class="form-group">' +
                    '</div>' +
                    '<label for="name">设备名称</label>' +
                    '<input type="text" class="form-control" id="add_gatewayName" placeholder="请输入设备名称" required>' +
                    '<label for="name">设备型号</label>' +
                    '<input type="text" class="form-control" id="add_gatewayNo" placeholder="请输入设备型号" required>' +
                    '</div>' +
                    '</form>' +
                    '</div>';

                layer.confirm(dialog, {
                    title: "添加网关",
                    btn: ["提交"], //按钮
                    width: "100%"
                }, function () {
                    $.ajax({
                        url: "${path}/client/home?action=addDevice",
                        type: "GET",
                        data: {
                            customerId: account.id,
                            Name: $("#add_gatewayName").val(),
                            DeviceTypeId: $("#deviceType").val(),
                            gatewayDeviceID: current_gateway.id,
                            DeviceNo: $("#add_gatewayNo").val(),
                        },
                        dataType: "json",
                        success: function (result) {
                            //console.log(result);
                            if (result.result == "success") {
                                layer.msg("添加成功")
                                reloadPageContent(current_gateway);
                                refresh();
                            } else {
                                layer.alert(result.error);
                            }
                        },
                        error: function () {
                            layer.msg("请求失败！");

                        }
                    });
                });

                $("#addDeviceType").on('click', function () {
                    var dialog = '<div class="box">' +
//                    '<form >'+
                        '<div class="form-group">' +
                        '<label for="name">设备名称</label>' +
                        '<input type="text" class="form-control" id="add_deviceTypeName" placeholder="请输入设备名称" required>' +
                        '<label for="name">设备型号</label>' +
                        '<input type="text" class="form-control" id="add_deviceTypeModel" placeholder="请输入设备型号" required>' +
                        '</div>' +
                        '<div class="form-group">' +
                        '</div>' +
//                            '<div id="addGatewaySubmit" class="btn-default" >提交</div>'+
//                    '</form>'+
                        '</div>';


                    layer.confirm(dialog, {
                        title: "添加设备类型",
                        btn: ["提交"], //按钮
//                            width: "100%"
                    }, function () {
                        $.ajax({
                            url: "${path}/client/home?action=addDeviceType",
                            type: "GET",
                            data: {
                                customerId: account.id,
                                Model: $("#add_deviceTypeModel").val(),
//                          iP: $("#add_gatewayIP").val(),
                                Name: $("#add_deviceTypeName").val()
                            },
                            dataType: "json",
                            success: function (result) {
                                //console.log(result);
                                if (result.result == "success") {
                                    layer.msg("添加成功")
                                } else {
                                    layer.alert(result.error);
                                }
                            },
                            error: function () {
                                layer.msg("请求失败！");

                            }
                        });
                    });
                });
            }

            function addGateway() {
                var dialog = '<div class="box">' +
                    '<form >' +
                    '<div class="form-group">' +
                    '<label for="name">网关名称</label>' +
                    '<input type="text" class="form-control" id="add_gatewayName" placeholder="请输入网关编号" required>' +

//                            '<label for="name">网关IP</label>'+
//                            '<input type="text" class="form-control" id="add_gatewayIP" placeholder="请输入网关IP">'+

                    '<label for="name">网关地址</label>' +
                    '<input type="text" class="form-control" id="add_gatewayPort" placeholder="请输入网关地址" required>' +
                    '</div>' +
                    '<div class="form-group">' +
                    '</div>' +
//                            '<div id="addGatewaySubmit" class="btn-default" >提交</div>'+
                    '</form>' +
                    '</div>';


                layer.confirm(dialog, {
                    title: "添加网关",
                    btn: ["提交"], //按钮
//                            width: "100%"
                }, function () {
                    $.ajax({
                        url: "${path}/client/home?action=addGatewayForCustomer",
                        type: "GET",
                        data: {
                            customerId: account.id,
                            address: $("#add_gatewayPort").val(),
//                          iP: $("#add_gatewayIP").val(),
                            gatewayDeviceID: $("#add_gatewayName").val()
                        },
                        dataType: "json",
                        success: function (result) {
                            //console.log(result);
                            if (result.result == "success") {
                                layer.msg("添加成功")
                                refresh();
                            } else {
                                layer.alert(result.error);
                            }
                        },
                        error: function () {
                            layer.msg("请求失败！");

                        }
                    });
                });
            }

            function addDevice() {
                var deviceTypes = new Array();
                $.ajax({
                    url: "${path}/client/home?action=getDeviceTypeList",
                    type: "GET",
                    data: {},
                    dataType: "json",
                    success: function (result) {
                        //console.log(result);
                        if (result.result == "success") {
                            for (var i in result.operationResult) {
//                                    alert("result:"+JSON.stringify(result.operationResult));
                                var item = result.operationResult[i];
                                var dt = {
                                    "id": item.id,
                                    "name": item.Name,
                                    "Model": item.Model
//                                        "Attention":item.Attention,
//                                        "Describtion":item.Describtion,
//                                        "CreatedDate":item.CreatedDate,
//                                        "ModifiedDate":itme.ModifiedDate,
//                                        "Reserve": itme.Reserve
                                };

                                deviceTypes.push(dt);
                            }
                            addDeviceDialog(deviceTypes);

                        } else {
                            layer.alert(result.error);
                        }
                    },
                    error: function () {
                        layer.msg("请求失败！");
                    }
                });
            }

//              页面事件响应
            $("#addGateWayBtn").click(function () {
//                addGateway();
                window.location.href = "${path}/client/home?action=addGetway&mobelPhone=" + account.mobelPhone;
            });
            $("#addGateWay").click(function () {
//                addGateway();
                window.location.href = "${path}/client/home?action=addGetway&mobelPhone=" + account.mobelPhone;
            });

            $("#personal").click(function () {
                window.location.href = "${path}/client/account?action=personal&mobelPhone=" + account.mobelPhone;
            });

        });

    </script>

</head>
<body>
<!--<div style="clear:both;">-->
<!--<script src="/gg_bd_ad_720x90.js" type="text/javascript"></script>-->
<!--<script src="/follow.js" type="text/javascript"></script>-->
<!--</div>-->

<nav>
    <div class="menuleft">
        <div class="dropdownIcon">
            <span></span>
            <span></span>
            <span></span>
        </div>
    </div>
    <div id="titleV" class="titleView">
        <span>首页</span>
    </div>
    <div class="menu">
        <i class="plus"></i>
    </div>
    <ul id="rightM" class="dropDown">
        <li id="personal"><a href="#"><i class="personal"></i> 个人中心</a></li>
        <li id="addGateWayBtn"><a href="#">添加网关</a></li>
        <li id="openAirKiss_btn"><a href="#">更改网关配置</a></li>
    </ul>
    <ul id="leftM" class=" leftM">
        <%--<li><a href="#">Menu Item 1</a></li>--%>
        <%--<li><a href="#">Menu Item 2</a></li>--%>
        <%--<li id="noMoreData"><a href="#">没有更多数据了哦</a></li>--%>
    </ul>
</nav>

<div id="page-content" style="  display: none;">
    <!--内容区 -->
    <section id="gateWay_content" class="gateWay_content">
        <div class="top">
            <div id="topContent" class="topContent">
                <div class="row">
                    <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 ">
                        <h3 class="title"><span>网关信息</span>
                            <div id="gatewayName"> Qixu Lorem</div>
                        </h3>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 ">
                <div id class="common-detail">
                    <div class="circle-status">
                        <%--<span id="gatewayIP">IP: 192.168.92.13:80</span>--%>
                        <span id="gatewayStatus" class="menu-status">未开启</span>
                    </div>
                    <!--<canvas id="shadowcanvas">-->
                    <!--</canvas>-->
                </div>
            </div>
        </div>
        <div class="gateWay_detail">
            <div class="row">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 ">
                    <div class="row content">
                        <div class="col-xs-4">
                            <div class="item square">
                                <img src="${path}/page/img/icon/settings.png">
                                <span id="device_shidu_info">空气湿度：--%</span>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="item square">
                                <img src="${path}/page/img/icon/settings.png">
                                <span id="device_wendu_info">温度：--</span>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="item square">
                                <img src="${path}/page/img/icon/settings.png">
                                <span id="device_pm_info">PM2.5：--%</span>
                            </div>
                        </div>
                        <%--<div class="col-xs-4">--%>
                        <%--<div class="item square">--%>
                        <%--<img src="${path}/page/img/icon/settings.png">--%>
                        <%--<span>toc：--%</span>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="col-xs-4">--%>
                        <%--<div class="item square">--%>
                        <%--<img src="${path}/page/img/icon/settings.png">--%>
                        <%--<span>二氧化碳：--%</span>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="col-xs-4">--%>
                        <%--<div class="item square">--%>
                        <%--<img src="${path}/page/img/icon/settings.png">--%>
                        <%--<span id="device_shidu_info_copy">空气湿度：--%</span>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                    </div><!--row content-->
                </div><!--col-xs-12-->
            </div> <!--row-->
        </div><!--gateWay_detail-->
    </section>
    <!--内容区 -->
    <section id="device-ct" class="device-content">
        <div class="row">
            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 ">
                <h3 class="title"><span>设备信息</span>当前网关下所有设备</h3>
            </div>
        </div>
    </section>
    <section id="device-list" class="device-list">
        <div class="row">
            <div id="devicelistPanel" class="col-xs-12 ">
                <!--col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3 ">-->

            </div><!--col-xs-12 -->
        </div><!--row -->
    </section>
</div>

<div id="nodata">
    <img src="${path}/page/img/icon/noData.png"/>
    <div class="label-title">当前并无数据哦！<span id="addGateWay">立即添加 </span></div>
</div>
<script type="text/javascript" src="${path}/page/js/index.js" charset="utf8"></script>

</body>
</html>
