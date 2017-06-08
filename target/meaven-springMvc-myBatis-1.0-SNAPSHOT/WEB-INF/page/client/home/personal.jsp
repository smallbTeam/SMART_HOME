<%--
  Created by IntelliJ IDEA.
  User: ligw
  Date: 2017/6/6
  Time: 2:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--引入页面变量配置--%>
<%@include file="/page/common/jsp/base.jsp" %>
<html>
<head>
    <%--引入基础设置--%>
    <%@include file="/page/common/jsp/baseInclude.jsp" %>
    <title>个人中心</title>
    <script type="text/javascript">
        $(function () {

        });

        //初始化页面
        $(document).ready(function () {

        });
    </script>
    <!-- home部分通用css -->
        <link rel="stylesheet" type="text/css" href="${path}/page/client/home/css/home.css">
    <!-- 本页面自定义js -->
    <script type="text/javascript" src="${path}/page/client/home/js/personal.js" charset="utf8"></script>
</head>
<body>


<!--顶部信息区域-->
<section id="panel_top" class="container">
    <div id="returnback" class="return">
        <img src="${path}/page/common/img/return.png"><span>返回</span>
    </div>

    <div class="icon-personal">
        <img src="${path}/page/common/img/bg_ch.png  ">
    </div>
    <center><h4 class="">Linsu</h4> </center>
    <div class="row ">
        <div class="col-sm-10 col-sm-offset-1 col-xs-12 col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3 label-personal ">
            <span >Lorem ipsum dolor sit amet, consectetur adipisicing elit.</span>
        </div>
    </div>

</section>
<!--个人信息部分-->
<div class="row">
    <div class="col-sm-10 col-sm-offset-1 col-xs-12 col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3  ">

        <section id="panel_msg" class="container">
            <div class="row ">
                <div class="col-sm-10 col-sm-offset-1 col-xs-12 col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3  list-personal-item">
                    <h4><img src="${path}/page/common/img/account.png">
                        <span>性别 </span> <span class="pull-right"> 女 <img src="${path}/page/common/img/enter.png"></span></span> </h4>
                </div>
            </div>
            <div class="row ">
                <div class="col-sm-10 col-sm-offset-1 col-xs-12 col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3  list-personal-item">
                    <h4><img src="${path}/page/common/img/iconfont-shouji.png">
                        <span>手机号码 </span> <span class="pull-right">19234565748 <img src="${path}/page/common/img/enter.png"></span></span> </h4>
                </div>
            </div>`
            <div class="row ">
                <div class="col-sm-10 col-sm-offset-1 col-xs-12 col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3  list-personal-item">
                    <h4><img src="${path}/page/common/img/date.png">
                        <span>出生日期 </span> <span class="pull-right">2011-11-11 <img src="${path}/page/common/img/enter.png"></span></span></h4>
                </div>
            </div>
        </section>
    </div>
</div>

<!--个人信息部分-->
<div class="row">
    <div class="col-sm-10 col-sm-offset-1 col-xs-12 col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3  ">

        <section id="panel_msg" class="container">
            <div class="row  ">
                <div class="col-sm-10 col-sm-offset-1 col-xs-12 col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3  list-section-item">
                    <h4><img src="${path}/page/common/img/editor.png">
                        <span>反馈 <img src="${path}/page/common/img/enter.png" class="pull-right"></span></h4>
                </div>
            </div>
            <div class="row ">
                <div class="col-sm-10 col-sm-offset-1 col-xs-12 col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3  list-section-item">
                    <h4><img src="${path}/page/common/img/feedback.png">
                        <span>关于我们 <img src="${path}/page/common/img/enter.png" class="pull-right"></span></h4>
                </div>
            </div>
        </section>
    </div>
</div>

<section id="footer">


</section>


<script>
    $(document).ready(function () {
        $("#returnback").click(function () {
            history.back(-1);
        }) ;
    });
</script>
</body>
</html>
