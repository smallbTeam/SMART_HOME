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
    <script type="text/javascript">
        $(function () {
            $.getUrlParam=function(name) {
                var reg=new RegExp("(^[&]"+name+"=([^&]*)(&|$)");
                var r=window.location.search.substr(1).match(reg);
                if (r != null) return unescape(r[2]);return null;
            };

            var gateWayId=$.getUrlParam("GatewayId");
            var url="http://localhost:8080/smarthome/client/home?action=getDeviceListByGatewayId&deviceGateId="+gateWayId;
            $.get(url,function (msg) {
//            alert("msg---"+msg.operationResult);
                    if (msg.result == "success") {
                        //请求成功
                        // var operationResult=msg.operationResult;                    var operationResult=msg["operationResult"];
                        [].forEach(function (value,index,operationResult) {
                        var divcontent='<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3  " onclick="">'+
                        '<div id="gateway_list-item_'+value["id"]+'" class="list-item">'+
                        '<div class="close_'+value["id"]+'"><img src="${path}/page/common/img/close.png"></div>'+
                        ' <div class="list-item-content">'+
                        '  <div class="list-item-top ">'+
                        '   <h3> <span class="glyphicon glyphicon-object-align-vertical szblue"></span> '+value["DeviceNo"]+'</span></h3>'+
                        '</div>'+
                        ' <div class="list-item-bottom">'+
                        ' <ul class=" ">'+
                        '  <li><span class="glyphicon glyphicon-send"></span><a  href="#">空调</a> </li>'+
                        '   <li><span class="glyphicon glyphicon-adjust"></span><a  href="#">开启中</a> </li>'+
                        '  <li><span class="glyphicon glyphicon-alert"></span><a  href="#">无预警</a> </li>'+
                        '  </ul>'+

                        '   </div>'+
                        '   </div>'+
                        '  </div>'+
                        '  </div>';
                        $("#device-list .row").appendChild(divcontent);

                        $("#device-list #device_list-item_"+value["id"]+" .list-item-content").bind("click",function () {
                            var ids=$(this).parentNode.attr("id");
                            var id=ids.split("_").last();

                        });

                        $("#device-list #close_"+value["id"]).bind("click",function () {
                            var ids=$(this).attr("id");
                            var id=ids.split("_").last();
                            var url="http://localhost:8080/smarthome/client/home?action=delDeviceById&DeviceId="+id;
                            ajaxRequest(url,"GET",function(){
                                if (flag == true && msg["result"].equals("success")) {
                                    var pid = $(this).parent("div").attr('id');
                                    $("#"+pid).remove();
                                }else{
                                    //请求失败
                                    layer.alert("删除失败，请稍候重试");
                                }

                            });
                        });

                        var lastItem='<div class="col-xs-12 col-sm-6  col-md-4 col-lg-3  ">'+
                        '<div class="list-item-last">'+
                        '<button id="add_gateway" ><img src="${path}/page/common/img/add.png"></button>'+
                        ' </div>'+
                        '  </div>';
                        $("#device-list .row").appendChild(lastItem);

                    });
                    }else{

                }
                });

        });

        //初始化页面
        $(document).ready(function () {

        });
    </script>
        <!-- home部分通用css -->
        <link rel="stylesheet" type="text/css" href="${path}/page/client/home/css/home.css">
        <link href="${path}/page/assets/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
        <!-- 本页面自定义js -->
        <script type="text/javascript" src="${path}/page/client/home/js/deviceList.js" charset="utf8"></script>
        <script type="text/javascript" src="${path}/page/assets/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
        <script type="text/javascript" src="${path}/page/assets/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
        <script type="text/javascript" src="${path}/page/common/js/layer/layer.js" charset="utf8"></script>
</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header ">
            <button type="button" class="navbar-toggle " data-toggle="collapse"
                    data-target="#example-navbar-collapse">
                <%--<span class="sr-only">切换导航</span>--%>
                <%--<span class="icon-bar"></span>--%>
                <%--<span class="icon-bar"></span>--%>
                <%--<span class="icon-bar"></span>--%>
            </button>
            <div class="clearfix">
                <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-send"></span></a>
                <a style="position: absolute; margin-left:25%;width: 50%;text-align: center;" class="navbar-brand clearfix" href="#">设备列表</a>
            </div>
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="${path}/client/home?action=index">首页</a></li>
                <li class=""><a href="${path}/client/home?action=personal">个人中心</a></li>

            </ul>
        </div>
    </div>
</nav>

<!--网关相关信息-->
<section id="gateway_msg" class="container">
    <div class="bg_gateway_msg">

        <div class="row">
            <div class="col-xs-12 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4 col-lg-4 col-lg-offset-4 ">
                <div id="gateway_msg_top">
                    <h3><span class="glyphicon glyphicon-send"></span><span>网关-绮兰之蓝 </span></h3>
                </div>

                <div id="line_msg">
                    <span class="glyphicon glyphicon-arrow-right"> 网关地址: 192.168.3.31:2390</span>
                </div>

                <div id="line_msg1">
                    <span class="glyphicon glyphicon-bullhorn"> 状态：良好</span>
                    <span class="glyphicon glyphicon-magnet"> 辖内设备：9</span>
                </div>
                <div id="line_msg2">
                    <span class="glyphicon glyphicon-bookmark"> 此网关涵盖A室一切用电以及用水设备,此网关涵盖A室一切用电以及用水设备 </span>
                </div>
            </div>
        </div>

    </div>
</section>


<!--设备列表-->
<!--<section id="device-list" class="container">-->
<!--<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3  ">-->
<!--<div id="device-list-item">-->
<!---->
<!--</div>-->
<!--</div>-->
<!--</section>-->
<section id="device-list" class="container">
    <div class="row">
        <!--
        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3  " onclick="">
            <div id="gateway_list-item1" class="list-item">
                <div class="close"><img src="${path}/page/common/img/close.png"></div>
                <div class="list-item-content">
                    <div class="list-item-top ">
                        <h3> <span class="glyphicon glyphicon-object-align-vertical szblue"></span> <span>设备：望海角------7号之蓝</span></h3>
                    </div>
                    <div class="list-item-bottom">
                        <ul class=" ">
                            <li><span class="glyphicon glyphicon-send"></span><a  href="#">空调</a> </li>
                            <li><span class="glyphicon glyphicon-adjust"></span><a  href="#">开启中</a> </li>
                            <li><span class="glyphicon glyphicon-alert"></span><a  href="#">无预警</a> </li>
                        </ul>

                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3  " onclick="">
            <div id="gateway_list-item2" class="list-item">
                <div class="close"><img src="${path}/page/common/img/close.png"></div>
                <div class="list-item-content">
                    <div class="list-item-top ">
                        <h3> <span class="glyphicon glyphicon-object-align-vertical szblue"></span> <span>设备：望海角------7号之蓝</span></h3>
                    </div>
                    <div class="list-item-bottom">
                        <ul class=" ">
                            <li><span class="glyphicon glyphicon-send"></span><a  href="#">空调</a> </li>
                            <li><span class="glyphicon glyphicon-adjust"></span><a  href="#">开启中</a> </li>
                            <li><span class="glyphicon glyphicon-alert"></span><a  href="#">无预警</a> </li>
                        </ul>

                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3  " onclick="">
            <div id="gateway_list-item3" class="list-item">
                <div class="close"><img src="${path}/page/common/img/close.png"></div>
                <div class="list-item-content">
                    <div class="list-item-top ">
                        <h3> <span class="glyphicon glyphicon-object-align-vertical szblue"></span> <span>设备：望海角------7号之蓝</span></h3>
                    </div>
                    <div class="list-item-bottom">
                        <ul class=" ">
                            <li><span class="glyphicon glyphicon-send"></span><a  href="#">空调</a> </li>
                            <li><span class="glyphicon glyphicon-adjust"></span><a  href="#">开启中</a> </li>
                            <li><span class="glyphicon glyphicon-alert"></span><a  href="#">无预警</a> </li>
                        </ul>

                    </div>
                </div>
            </div>
        </div>

        <div class="col-xs-12 col-sm-6  col-md-4 col-lg-3  ">
            <div class="list-item-last">
                <button id="add_gateway" ><img src="${path}/page/common/img/add.png"></button>
            </div>
        </div>
-->
    </div>
</section>

<div id=" ">


    <!--<label><input class="mui-switch" type="checkbox" checked> 默认选中</label>-->
    <!--<label><input class="mui-switch mui-switch-animbg" type="checkbox"> 默认未选中,简单的背景过渡效果,加mui-switch-animbg类即可</label>-->
    <!--<label><input class="mui-switch mui-switch-animbg" type="checkbox" checked> 默认选中</label>-->
    <!--<label><input class="mui-switch mui-switch-anim" type="checkbox"> 默认未选中，过渡效果，加 mui-switch-anim-->
    <!--类即可</label>-->
    <!--<label><input class="mui-switch mui-switch-anim" type="checkbox" checked> 默认选中</label>-->

</div>



<section id="footer">


</section>

<script>
    $(document).ready(function () {
        $.getUrlParam=function(name) {
            var reg=new RegExp("(^[&]"+name+"=([^&]*)(&|$)");
            var r=window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]);return null;
        };

        var gateWayId=$.getUrlParam("GatewayId");
        var dialog = '<div class="box">'+
            '<form role="form" action="addDevice">'+
            '<div class="form-group">'+
            '<label for="name">设备名称</label>'+
            '<input type="text" class="form-control" name="Name" id="name" placeholder="请输入设备名称">'+

            '<label for="name">设备型号</label>'+
            '<input type="text" class="form-control" name="DeviceNo" id="name" placeholder="请输入设备型号">'+

            '<label for="name">设备类型</label>'+
            '<input type="text" class="form-control" name="DeviceTypeName" id="name" placeholder="请输入设备类型">'+
            '<input type="hidden" name="DeviceTypeId" value="">'+
            '<input type="hidden" name="GatewayId" value="'+gateWayId+'">'+

            '<label for="name">备注</label>'+
            '<input type="text" class="form-control" name="" id="name" placeholder="请输入备注信息">'+
            '</div>'+
            '<div class="form-group">'+
            '</div>'+
            '<button type="submit" class="btn btn-default">提交</button>'+
            '</form>'+
            '</div>';

        var detail = '<div class="list-item-top ">'+
            '<h3> <span class="glyphicon glyphicon-object-align-vertical szblue"></span> <span>设备：望海角------7号之蓝</span></h3>'+
            '</div>'+
            '<div class="list-item-middle clearfix">'+
            '<span>OFF</span><input id="switchmenu" style="outline: none;" class="mui-switch" type="checkbox"> '+
            '</div>'+
            '<div class="list-item-bottom">'+
            '<ul class=" ">'+
            ' <li><span class="glyphicon glyphicon-send"></span><a  href="#">类型：空调</a> </li>'+
            '<li><span class="glyphicon glyphicon-adjust"></span><a  href="#">状态：开启中</a> </li>'+
            '<li><span class="glyphicon glyphicon-alert"></span><a  href="#">无预警</a> </li>'+
            '   </ul>'+

            '</div>';

        layer.config({
            extend:'szskin/style.css',
            skin: 'dialog'
        });


        $("#device-list .list-item-last #add_gateway").click(function () {

            layer.open({
                type: 1,
                title: false,
                closeBtn: 1,
                shadeClose: true,
                skin: 'dialog',
                content: dialog
            });
        });

        $("#device-list .list-item").click(function () {
            layer.open({
                type: 1,
                title: false,
                closeBtn: 1,
                shadeClose: true,
                skin: 'dialog',
                content: detail
            });
        });

        $("#device-list .close").click(function () {
            var id = $(this).parent("div").attr('id');
            $("#"+id).remove();


        });
        $("#device-list .list-item").hover(function () {
            var id = $(this).attr('id');
            $("#device-list #"+id+" .close").css("opacity","0.5");
        });

        $("#device-list .list-item").mouseleave(function () {
            var id = $(this).attr('id');
            $("#device-list #"+id+" .close").css("opacity","0.0");
        });


        $("#switchmenu").change(function () {
            alert("value:::"+$("switchmenu").val());
        });

    });

</script>
</body>
</html>
