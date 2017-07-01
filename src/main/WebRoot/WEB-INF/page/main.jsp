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
        //初始化页面
        $(document).ready(function () {

        });
    </script>
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
        <li><a href="#"><i class="personal"></i> Menu</a></li>
        <li><a href="#">Menu Item 2</a></li>
        <li><a href="#">Menu Item 3</a></li>
    </ul>
    <ul id="leftM" class=" leftM">
        <li><a href="#">Menu Item 1</a></li>
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
                    <h3 class="title"><span>网关信息</span>Qixu Lorem</h3>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 ">
            <div id class="common-detail">
                <div class="circle-status">
                    <span>IP: 192.168.92.13:80</span>
                    <span>未开启</span>
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
                            <span>空气湿度：75%</span>
                        </div>
                    </div>
                    <div class="col-xs-4">
                        <div class="item square">
                            <img src="${path}/page/img/icon/settings.png">
                            <span>温度：25</span>
                        </div>
                    </div>
                    <div class="col-xs-4">
                        <div class="item square">
                            <img src="${path}/page/img/icon/settings.png">
                            <span>PM2.5：75%</span>
                        </div>
                    </div>
                    <div class="col-xs-4">
                        <div class="item square">
                            <img src="${path}/page/img/icon/settings.png">
                            <span>toc：75%</span>
                        </div>
                    </div>
                    <div class="col-xs-4">
                        <div class="item square">
                            <img src="${path}/page/img/icon/settings.png">
                            <span>二氧化碳：75%</span>
                        </div>
                    </div>
                    <div class="col-xs-4">
                        <div class="item square">
                            <img src="${path}/page/img/icon/settings.png">
                            <span>空气湿度：75%</span>
                        </div>
                    </div>
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
        <div class="col-xs-12 ">
            <!--col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3 ">-->
            <div id="list-content" class="list-content">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 ">
                    <div class="list-item">
                        <div class="list-item-content">
                            <div class="leftContent">
                                <!--<span class="subtitle">状态：开启中</span>-->
                                <div class="device-menu"><span>空调</span></div>
                            </div>
                            <div class="rightContent">
                                <div class="topLabel">
                                    <div class="title">海尔变频空调</div>
                                </div>
                                <div class="bottomLabel ">
                                    <div class="bottomLabel-item pull-left"><img
                                            src="${path}/page/img/icon/blue.png"/><span>正常</span></div>
                                    <div class="bottomLabel-item pull-left"><img
                                            src="${path}/page/img/icon/blue.png"/><span>正常</span></div>
                                    <div class="bottomLabel-item pull-left"><img
                                             src="${path}/page/img/icon/blue.png"/><span>正常</span></div>
                                </div>
                                <div class="list-item-hover">
                                    <div class="item-icon ">
                                        <img  src="${path}/page/img/icon/delete.png" alt="img30"/>
                                        <span>删除</span>
                                    </div>
                                    <div class="item-icon ">
                                        <img  src="${path}/page/img/icon/edit.png" alt="img30"/>
                                        <span>编辑</span>
                                    </div>
                                    <div class="item-icon ">
                                        <img  src="${path}/page/img/icon/detail.png" alt="img30"/>
                                        <span>详情</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div><!--list-content col-xs-12 -->
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 ">
                    <div class="list-item">
                        <div class="list-item-content">
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 ">
                    <div class="list-item">
                        <div class="list-item-content">
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 ">
                    <div class="list-item">
                        <div class="list-item-content">
                        </div>
                    </div>
                </div>
            </div><!--list-content -->
        </div><!--col-xs-12 -->
    </div><!--row -->
</section>

<script type="text/javascript" src="${path}/page/js/index.js" charset="utf8"></script>

<script>
    $(document).ready(function () {

        var canvas = document.getElementById('shadowcanvas');
        var ctx = canvas.getContext('2d');

        ctx.save();
        ctx.fillStyle = '#fff';
        ctx.lineCap = "round";
        ctx.lineWidth = '0.5';

//        ctx.shadowOffsetX = 15; // 阴影Y轴偏移
        ctx.shadowOffsetY = 2; // 阴影X轴偏移
        ctx.shadowBlur = 2; // 模糊尺寸
        ctx.shadowColor = '#f0f0f0'; // 颜色

        ctx.beginPath();
        ctx.moveTo(0, canvas.height * 4 / 5);
        ctx.quadraticCurveTo(canvas.width / 2, canvas.height, canvas.width, canvas.height * 4 / 5);
//        ctx.lineTo(canvas.width,0);
//        ctx.lineTo(0,0);
//        ctx.arc(canvas.width/2, canvas.height/4, canvas.height/2, 0, 2 * Math.PI, false);
        ctx.fill();
//        ctx.stroke();
        ctx.scale(2, 2);
//        ctx.restore();


        $('.dropDown').mouseleave(function () {
            $('.dropDown').slideUp("slow", function () {
                $(this).fadeOut(2000);
            });
//            $('.leftM').slideUp("slow", function(){
//                $(this).fadeOut(2000);
//            });
        });

//        $(".list-item").click(function () {
//            $('#slideDown').slideToggle();
//        });

//        $(".list-item-content").mouseenter(function() {
//            $(this).find('.list-item-hover').css('display','block');
//        });
//        $(".list-item-content").mouseleave(function() {
//            $(this).find('.list-item-hover').css('display','none');
//        });

    });

</script>

</body>
</html>
