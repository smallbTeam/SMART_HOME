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
        <link rel="stylesheet" type="text/css" href="${path}/page/client/home/css/main.css">
        <script type="text/javascript" src="${path}/page/common/js/layer/layer.js" charset="utf8"></script>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <a href="href-"#"" class="navbar-brand">Brand</a>
    </div>
    <ul class="nav navbar-nav">
        <li><a href="#">Home</a></li>
        <li><a href="#">Link</a></li>
        <li><a href="#">Link</a></li>
    </ul>
    <button class="btn btn-class navbar-btn">和ul一起使用的button</button>
</nav>

</body>
</html>
