<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/page/common/jsp/base.jsp"%>
<html>
<head>
<%@include file="/page/common/jsp/baseinclude_admin.jsp"%>
<title>管理员登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 本页面css-->
<link rel="stylesheet" type="text/css" href="${path}/page/css/admin.css">
<title>Insert title here</title>

<style type="text/css">
.login-main{
	width: 450px;
	height: 400px;
	background-color: #ffffff;
	padding: 0px;
	border: 1px solid #F3B553;
	border-radius: 10px;
}

.login-main .login-main-top{
	background-color: #F3B553;
	border: 1px solid #F3B553;
	border-radius: 10px 10px 0px 0px;
	height: 50px;
	text-align:left;
	font-size: 18px;
	font-weight: bold;
	color: #ffffff;
	padding:11px 0px 0px 10px;
}

.login-main .login-main-center{
	margin:0px 30px;
}

.login-main .login-main-center .login-form {
	height: 220px;
	display: block;
}

.login-main .login-main-center .login-form button{
	background-color: #F3B553;
	border-color:#F3B553;
}
</style>
</head>
<body>
	<div class="center-middle">
		<div class="login-main">
			<div class="login-main-top">登&nbsp;录</div>
			<div class="login-main-center">
				<div class="login-form">
					<br>
					<div id="msg-info" class="alert-danger spec-hidden"style="text-align: center; padding: 2px 0px;">msg</div>
					<br>
					<form id="lognForm" autocomplete="off" target="_self" role="form">
						<div class="form-group">
		    				<label for="mobelPhone">账号*</label>
		    				<input type="text" class="form-control {required:true,mobileno:true ,messages:{required:'手机号不能为空',mobileno:'请输入正确手机号'} }" id="mobelPhone" name="mobelPhone " placeholder="注册手机号">
		  				</div>
		  				<div class="form-group">
		    				<label for="password">密码*</label>
		    				<input type="password" class="form-control {required:true ,messages:{required:'密码不能为空'} }" id="password" name="password" placeholder="请输入密码">
		  				</div>
						<div class="form-group">
							<button type="button" class="col-xs-4 col-sm-4 btn btn-default" id="loginBtn">登录</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(document).ready(function() {
	$("#lognForm").validate({
		showErrors : function(errorMap, errorList) {
			if ((errorList != null) && (errorList.length > 0)) {
				showMsg(errorList[0].message);
			} else {
				hideMsg();
			}
		}
	});
	
	$("#lognForm").on("click","#loginBtn",function(){
		if($("#lognForm").validate().form()){
			$('#loginBtn').text('登录中...');
			$.ajax({
				url:"${path}/admin/account?action=accountLogin",
				type:"post",
				data:{
					"mobelPhone":$("#mobelPhone").val(),
					"password":$("#password").val()
				},
				dataType:"json",
				success:function(data){
					if(data.result == "success" && null != data.operationResult
                        && "" != data.operationResult){
						if((0 == data.operationResult)){
							showMsg("手机号或密码错误");
							$('#loginBtn').text('登录');
						} else {
							window.top.location.href = "${path}/admin/account?action=index&adminId="+data.operationResult;
						}
					} else {
                        showMsg("系统错误");
						$('#loginBtn').text('登录');
					}
				},
				error:function(e) {
					showMsg("系统错误");
					$('#loginBtn').text('登录');
					console.log(e); 
				}
			}); 
		}
	});
});

var showMsg = function(msg){
	$("#msg-info").html(msg);
	if ($("#msg-info").hasClass("spec-hidden")) {
		$("#msg-info").removeClass("spec-hidden");
	}
}

var hideMsg = function(){
	if (!$("#msg-info").hasClass("spec-hidden")) {
		$("#msg-info").addClass("spec-hidden");
	}
}
</script>
</html>
