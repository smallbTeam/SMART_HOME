
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
    <link rel="stylesheet" type="text/css" href="${path}/page/css/detail.css">
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
        <span>详情</span>
    </div>
    <div class="menu">
        <i class="return"></i>
    </div>
    <ul id="rightM" class="dropDown">
        <li><a href="#"><i class="personal"></i> Menu</a></li>
        <li><a href="#">Menu Item 2</a></li>
        <li><a href="#">Menu Item 3</a></li>
    </ul>


</nav>

<!--内容区 -->
<section id="gateWay_content" class="gateWay_content">
    <div class="top">
        <div id="topContent" class="topContent">
            <div class="row">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 ">
                    <h3 class="title"><span>设备信息</span>Qixu Lorem</h3>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 ">

            <div id class="common-detail">
                <div class="line">
                    <img src="${path}/page/img/icon/blue.png" class="pull-left"/>
                    <span class="pull-left">设备类型</span>
                    <span id="deviceType" class="pull-right">空调</span>
                </div>
                <div class="line">
                    <img src="${path}/page/img/icon/blue.png" class="pull-left"/>
                    <span class="pull-left">设备型号</span>
                    <span id="deviceNum" class="pull-right">未开启</span>
                </div>
                <div class="line">
                    <img src="${path}/page/img/icon/blue.png" class="pull-left"/>
                    <span class="pull-left">设备款式</span>
                    <span id="deviceName" class="pull-right">未开启</span>
                </div>
                <div class="line">
                    <img src="${path}/page/img/icon/blue.png" class="pull-left"/>
                    <span class="pull-left">设备编号</span>
                    <span id="deviceNo" class="pull-right">未开启</span>
                </div>
                <div class="line">
                    <img src="${path}/page/img/icon/blue.png" class="pull-left"/>
                    <span class="pull-left">设备状态</span>
                    <span id="deviceStatus" class="pull-right">未开启</span>
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

<!--内容区 -->
<section id="device-ct" class="device-content">
    <div class="row">
        <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 ">
            <h3 class="title"><span>控制区</span>对当前设备进行管控</h3>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12 ">
            <div class="cicle-menu">
                <span>开启</span>
            </div>
        </div><!--col-xs-12 -->
    </div><!--row -->
</section>

<!--内容区 -->
<section id="dev " class="device-content">
    <div class="row">
        <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 ">
            <h3 class="title"><span>图表</span>数据统计内容区</h3>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12 ">
            <div id="chart" class="charts">
                <canvas style="width: 100%;height: 100%"></canvas>
            </div>
        </div><!--col-xs-12 -->
    </div><!--row -->
</section>

<script type="text/javascript" src='${path}/page/js/third/echarts.min.js' charset="utf8"></script>

<script>
    $(document).ready(function () {
//        var ctx = $("#chart").getContext("2d");

//        var myChart = echarts.init(document.getElementById('chart'));
        var dom = document.getElementById("chart");
        var myChart = echarts.init(dom);
        var app = {};
        option = null;
        var base = +new Date(2014, 9, 3);
        var oneDay = 24 * 3600 * 1000;
        var date = [];

        var data = [Math.random() * 150];
        var now = new Date(base);

        function addData(shift) {
            now = [now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/');
            date.push(now);
            data.push((Math.random() - 0.4) * 10 + data[data.length - 1]);

            if (shift) {
                date.shift();
                data.shift();
            }

            now = new Date(+new Date(now) + oneDay);
        }

        for (var i = 1; i < 100; i++) {
            addData();
        }

        option = {
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: date
            },
            yAxis: {
                boundaryGap: [0, '50%'],
                type: 'value'
            },
            series: [
                {
                    name:'成交',
                    type:'line',
                    smooth:true,
                    symbol: 'none',
                    stack: 'a',
                    areaStyle: {
                        normal: {}
                    },
                    data: data
                }
            ]
        };

        setInterval(function () {
            addData(true);
            myChart.setOption({
                xAxis: {
                    data: date
                },
                series: [{
                    name:'成交',
                    data: data
                }]
            });
        }, 500);;
        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }

    $(".menuleft").click(function () {
            window.history.back();
        });

        var deviceId = <%=request.getParameter("deviceId")%>;
//        加载设备信息
        $.ajax({
            url:"${path}/client/device?action=getDeviceByDeviceId",
            data: {
                deviceId : deviceId
            },
            success: function (msg) {
                if (msg.result == "success") {
//                    de.id, de.DeviceTypeId deviceTypeId, de.DeviceNo,
//                        de.State deviceState, de.DeviceData, de.Reserve1 deviceReserve1,
//                        de.Name deviceName,de.GetwayId deviceGetwayId,
//                        dt.Name deviceTypeName, dt.Model deviceTypeModel, dt.Attention deviceTypeAttention,
//                        dt.Describtion deviceTypeDescribtion, dt.Reserve deviceTypeReserve,
//                        dg.GatewayPort gatewayGatewayPort, dg.IP gatewayIP
                    var result = msg.operationResult;
                    var device = {
                        "id": result.id,
                        "deviceTypeId": result.deviceTypeId,
                        "DeviceNo": result.DeviceNo,
                        "deviceState": result.deviceState,
                        "DeviceData": result.DeviceData,
                        "deviceReserve1": result.deviceReserve1,
                        "deviceName": result.deviceName,
                        "deviceTypeModel": result.deviceTypeModel,
                        "deviceTypeAttention": result.deviceTypeAttention,
                        "deviceTypeDescribtion": result.deviceTypeDescribtion,
                        "deviceTypeReserve": result.deviceTypeReserve,
                        "gatewayGatewayPort": result.gatewayGatewayPort,
                        "gatewayIP": result.gatewayIP
                    }
                    $("#deviceName").html(device.deviceName);
                    $("#devicetype").html(device.deviceTypeModel);
                    $("#deviceNo").html(device.deviceTypeId);
                    $("#deviceStatus").html(device.deviceState);
                    $("#deviceNum").html(device.deviceTypeDescribtion);


                }else{
                    layer.msg("设备信息加载失败！");
                }
            },
            error: function (error) {

            }
        });


    });

</script>

</body>
</html>