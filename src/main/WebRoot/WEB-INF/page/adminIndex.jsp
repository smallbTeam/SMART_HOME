<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/page/common/jsp/base.jsp"%>
<html>
	<head>
		<%@include file="/page/common/jsp/baseinclude_admin.jsp"%>
		<title>Insert title here</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript">
		var admin={
				"adminId":"${admin.adminId}",
				"mobelPhone": "${admin.mobelPhone}",
				"nickName":"${admin.nickName}",
			    "permissionLevel":"${admin.permissionLevel}",
			    "token":"${admin.token}"
			};
		$(function(){
			if((("undefined" == typeof(admin)) || (null == admin) 
					|| ("" == admin.adminId) || (null == admin.mobelPhone))){
				window.top.location.href = "${path}/admin/account?action=login";
			}
		});
		//设置登陆用户名
		var nickName = ((admin.nickName == null)||($.trim(admin.nickName) == "")) ? "" : ("你好:"+admin.nickName);
		var logout = function() {
			parent.window.location.href = "${path}/admin/account?action=login";
		}
		$(document).ready(function(){
			$(".admin-header-info span.admin-id").html(nickName);
		});
		</script>
		<!-- 本页面自定义js -->
		<script type="text/javascript" src="${path}/page/js/admin.js" charset="utf8"></script>
		<!-- 本页面css-->
		<link rel="stylesheet" type="text/css" href="${path}/page/css/admin.css">
	</head>
	<body style="background-color:rgb(240, 245, 248);min-width: 900px">
		<div class="spec-center" id="centerpage">
			<div class="spec-loginout">
				<div class="admin-header-info">
				  	<img alt="" class="img-circle" src="${path}/page/img/online.png" />
					<span class="admin-id" id="admin-center-id" style="cursor:pointer;">管理员</span>
			  	</div>
				<span style="vertical-align: middle;padding:0px 40px">
					<span id="loginOut" onclick="logout()" style="cursor:pointer;">退出登录</span>
				</span>
			</div>
			<div class="admin-panel-tabs">
				<ul id="myTab" class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active">
						<a href="#gateway" id="gateway_tab" ria-controls="gateway" role="tab" data-toggle="tab">网关</a>
					</li>
					<li role="presentation">
						<a href="#deviceCategory" id="deviceCategory_tab" ria-controls="deviceCategory" role="tab" data-toggle="tab">设备类型</a>
					</li>
					<li role="presentation">
						<a href="#device" id="device_tab" ria-controls="device" role="tab" data-toggle="tab">设备</a>
					</li>
					<li role="presentation">
						<a href="#deviceData" id="deviceData_tab" ria-controls="deviceData" role="tab" data-toggle="tab">设备数据</a>
					</li>
				</ul>
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="gateway">
						<!-- 网关列表 -->
						<div id="prorertyRepair_list">
							<!-- 搜索表单 -->
							<form class="admin-select-input-group form-horizontal clearfix" role="form">
								<div class="admin-lable-input form-group col-xs-4 col-sm-4">
									<label for="serialNumber" class="col-xs-4 col-sm-4 text-right">网关设备号</label>
									<input type="text" placeholder="可模糊查询" id="serialNumber" name="serialNumber" class="spec-form-control col-xs-8 col-sm-8"/>
								</div>
								<div class="admin-lable-input form-group col-xs-4 col-sm-4">
									<label for="address" class="col-xs-4 col-sm-4 text-right">网关地址</label>
									<input type="text" placeholder="可模糊查询" id="address" name="address" class="spec-form-control col-xs-8 col-sm-8"/>
								<%--<select id="address" name="address" class="spec-form-control col-xs-8 col-sm-8">--%>
								<%--<option value="">全部</option>--%>
      									<%--<option value="0">未支付</option>--%>
      									<%--<option value="1">已支付</option>--%>
   									<%--</select>--%>
								</div>
								<div class="admin-lable-input form-group col-xs-4 col-sm-4">
									<label for="port" class="col-xs-4 col-sm-4 text-right">端口</label>
									<input type="text" placeholder="可模糊查询" id="port" name="port" class="spec-form-control col-xs-8 col-sm-8"/>
									<%--<select id="port" name="port" class="spec-form-control col-xs-8 col-sm-8">--%>
										<%--<option value="">全部</option>--%>
      									<%--<option value="0">已报修</option>--%>
      									<%--<option value="1">已修好</option>--%>
   									<%--</select>--%>
								</div>
								<div class="form-group">
									<label class=" "></label>
								</div>
								<div class="admin-lable-input form-group col-xs-4 col-sm-4">
									<label for="ip" class="col-xs-4 col-sm-4 text-right">Ip</label>
									<input type="text" placeholder="可模糊查询" id="ip" name="ip" class="spec-form-control col-xs-8 col-sm-8"/>
								</div>
								<div class="admin-lable-input form-group col-xs-7 col-sm-7">
									<label for="gateway_minCreatedDate" class="col-xs-2 col-sm-2 text-right">时间</label>
									<input type="date" readonly="readonly"  data-am-datepicker="{format: 'yyyy-mm-dd'}" placeholder="最早日期" id="gateway_minCreatedDate" name="gateway_minCreatedDate" class="spec-form-control col-xs-4 col-sm-4"/>
									<label class="pull-left">&nbsp;--&nbsp;</label>
									<input type="date" readonly="readonly"  data-am-datepicker="{format: 'yyyy-mm-dd'}" placeholder="最晚日期" id="gateway_maxCreatedDate" name="gateway_maxCreatedDate" class="spec-form-control col-xs-4 col-sm-4"/>
								</div>								
								<div class="form-group">
									<label class=" "></label>
								</div>								
								<div class="admin-lable-btn form-group col-xs-3 col-sm-3">
      								<button type="button" id="addGateway_btn" style="background-color:#3B315A" class="col-xs-8 col-sm-8 center-block btn btn-primary glyphicon glyphicon-plus" aria-hidden="true">&nbsp;新增网关</button>
  								</div>
  								<div class="admin-lable-btn form-group pull-right col-xs-offset-1 col-xs-2 col-sm-offset-1 col-sm-2 row">
      								<button type="button"  style="background-color:#3B315A" id="getGatewayList_btn" class="col-xs-9 col-sm-9 center-block btn btn-primary">查询</button>
  								</div>
  								<div class="admin-lable-btn form-group pull-right col-xs-offset-1 col-xs-2 col-sm-offset-1 col-sm-2 row">
      								<button type="reset" class="col-xs-9 col-sm-9 center-block btn btn-default">重置</button>
  								</div>
							</form>
							<!-- 列表表格 -->
							<div>
								<div id="table-gatewayList" style="display: none;"></div>
								<div id="table-content-gatewayList" class="spec-table">
									<table class="table admin_list_table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th>序号</th>
												<th>设备序列号</th>
												<th>地址</th>
												<th>Ip</th>
												<th>端口</th>
												<th>信息</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="updateTb_gateway"></tbody>
									</table>
								</div>
								<ul id="commonpage_gateway" data-am-widget="pagination" class="center-block am-pagination page-break am-no-layout"></ul>
							</div>
						</div>
						<!-- 添加或更改网关 -->
						<div id="addOrUpdateGateway" class="spec-none">
							<form class="admin-aou-input-group form-horizontal clearfix" role="form">
								<fieldset>
									<legend class="default">
										<font style="color:#3B315A;font-size:18px;" class="glyphicon glyphicon-wrench" aria-hidden="true">&nbsp;报修信息</font>
										<button type="button" style="background-color:#3B315A" id="aou_cancle_btn" class="btn pull-right btn-primary btn-sm glyphicon glyphicon-new-window" aria-hidden="true">&nbsp;返回</button>
										<div class="clearfix"></div>
									</legend>
									<div id="addGatewaySelect" class="admin-lable-input form-group col-xs-4 col-sm-4">
										<label for="add_prorertyRepair_dorBuilding" class="col-xs-4 col-sm-4 text-right">财产*</label>
										<select id="add_prorertyRepair_innerNumber" name="add_prorertyRepair_innerNumber" class="spec-form-control col-xs-8 col-sm-8">
											<option value="">--内部编号--</option>
   										</select>
									</div>
									<div class="form-group">
										<label class=""></label>
									</div>
									<div id="viewGatewayInput" class="admin-lable-input form-group col-xs-12 col-sm-12 spec-none">
										<label class="col-xs-1 col-sm-1 text-right">财产</label>
										<input  type="text" readonly="readonly" id="view_prorertyRepair_dorBuilding" name="view_prorertyRepair_dorBuilding" class="spec-form-control col-xs-3 col-sm-3"/>
										<label class="pull-left">&nbsp;--&nbsp;</label>
										<input  type="text" readonly="readonly" id="view_prorertyRepair_dorRoom" name="view_prorertyRepair_dorRoom" class="spec-form-control col-xs-3 col-sm-3"/>
										<label class="pull-left">&nbsp;--&nbsp;</label>
										<input  type="text" readonly="readonly" id="view_prorertyRepair_innerNumber" name="view_prorertyRepair_innerNumber" class="spec-form-control col-xs-3 col-sm-3"/>
									</div>				
									<div class="admin-lable-input form-group col-xs-4 col-sm-4">
										<label for="aou_prorertyRepair_contactName" class="col-xs-4 col-sm-4 text-right">报修人姓名*</label>
										<input type="text" placeholder="" id="aou_prorertyRepair_contactName" name="aou_prorertyRepair_contactName" class="spec-form-control col-xs-8 col-sm-8"/>
									</div>
									<div class="admin-lable-input form-group col-xs-4 col-sm-4">
										<label for="aou_prorertyRepair_contactPhone" class="col-xs-4 col-sm-4 text-right">报修人电话*</label>
										<input type="text" placeholder="" id="aou_prorertyRepair_contactPhone" name="aou_prorertyRepair_contactPhone" class="spec-form-control col-xs-8 col-sm-8"/>
									</div>
									<div class="admin-lable-input form-group col-xs-8 col-sm-8">
										<label for="aou_prorertyRepair_description" class="col-xs-2 col-sm-2 text-right">维修说明</label>
										<textarea type="text" clos="100" rows="3" placeholder="维修备注" id="aou_prorertyRepair_description" name="aou_prorertyRepair_description" class="spec-form-control col-xs-10 col-sm-10"></textarea>
									</div>
									<div class="form-group">
										<label class=""></label>
									</div>
									<div class="admin-lable-btn form-group col-xs-3 col-sm-3">
	      								<button type="button" style="background-color:#3B315A" id="aou_addGateway_btn" class="col-xs-8 col-sm-8 center-block btn btn-primary">保存</button>
	  								</div>								
								</fieldset>
							</form>
						</div>
					</div>
					<div role="tabpanel" class="tab-pane" id="deviceCategory">
						<!-- 缴费记录 -->
						<div id="deviceCategory_list">
							<!-- 搜索表单 -->
							<form class="admin-select-input-group form-horizontal clearfix" role="form">
								<div class="admin-lable-input form-group col-xs-8 col-sm-8">
									<label for="dormitoryElectricity_minSurplus" class="col-xs-3 col-sm-3 text-right">剩余电量</label>
									<div class="input-group  col-xs-4 col-sm-4">
										<input type="text" placeholder="最小剩余电量" id="dormitoryElectricity_minSurplus" name="dormitoryElectricity_minSurplus" class="spec-form-control"/>
										<span class="input-group-addon">KWH</span>
									</div>
									<label class="pull-left">&nbsp;--&nbsp;</label>
									<div class="input-group  col-xs-4 col-sm-4">
										<input type="text" placeholder="最大剩余电量" id="dormitoryElectricity_maxSurplus" name="dormitoryElectricity_maxSurplus" class="spec-form-control"/>
										<span class="input-group-addon">KWH</span>
									</div>
								</div>
								<div class="admin-lable-input form-group col-xs-4 col-sm-4">
									<label for="dormitoryElectricity_rechargeMoney" class="col-xs-4 col-sm-4 text-right">充值金额</label>
									<div class="input-group   col-xs-8 col-sm-8">
										<input type="text" placeholder="" id="dormitoryElectricity_rechargeMoney" name="dormitoryElectricity_rechargeMoney" class="spec-form-control"/>
										<span class="input-group-addon">元</span>
									</div>
								</div>								
								<div class="form-group">
									<label class=" "></label>
								</div>
								<div class="admin-lable-btn form-group col-xs-3 col-sm-3">
      								<button type="button" id="addElectricityCharge_btn" style="background-color:#3B315A" class="col-xs-8 col-sm-8 center-block btn btn-primary glyphicon glyphicon-plus" aria-hidden="true">&nbsp;充值</button>
  								</div> 					
  								<div class="admin-lable-btn form-group pull-right col-xs-offset-1 col-xs-2 col-sm-offset-1 col-sm-2 row">
      								<button type="button"  style="background-color:#3B315A" id="getDormitoryElectricity_btn" class="col-xs-9 col-sm-9 center-block btn btn-primary">查询</button>
  								</div>
  								<div class="admin-lable-btn form-group pull-right col-xs-offset-1 col-xs-2 col-sm-offset-1 col-sm-2 row">
      								<button type="reset" class="col-xs-9 col-sm-9 center-block btn btn-default">重置</button>
  								</div>
							</form>
							<!-- 列表表格 -->
							<div>
								<div id="table-dormitoryElectricityList" style="display: none;"></div>
								<div id="table-content-dormitoryElectricityList" class="spec-table">
									<table class="table admin_list_table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th>序号</th>
												<th>宿舍</th>
												<th>冲值前电量</th>
												<th>充值金额</th>
												<th>用电单价</th>
												<th>充值电量</th>
												<th>充值后电量</th>
											</tr>
										</thead>
										<tbody id="updateTb_dormitoryElectricity"></tbody>
									</table>
								</div>
								<ul id="commonpage_dormitoryElectricity" data-am-widget="pagination" class="am-pagination page-break am-no-layout"></ul>
							</div>
						</div>
						<!-- 新增缴费 -->
						<div id="addDormitoryElectricity" class="spec-none">
							<form class="admin-aou-input-group form-horizontal clearfix" role="form">
								<fieldset>
									<legend class="default am-text-middel">
										<font style="color: #3B315A;" class="glyphicon glyphicon-home" aria-hidden="true">&nbsp;</font>宿舍信息
										<button type="button" id="aou_cancle_btn" style="background-color:#3B315A" class="btn pull-right btn-primary btn-sm glyphicon glyphicon-new-window" aria-hidden="true">&nbsp;返回</button>
										<div class="clearfix"></div>
									</legend>
									<div class="admin-lable-input form-group col-xs-4 col-sm-4">
										<label id="aou_dormitoryElectricity_dorBuilding" class="col-xs-4 col-sm-4 text-right">***</label>
										<label class="pull-left">&nbsp;--&nbsp;</label>
										<label id="aou_dormitoryElectricity_dorRoom" class="col-xs-4 col-sm-4 ">***</label>
									</div>
									<div class="admin-lable-input form-group col-xs-4 col-sm-4">
										<label id="" class="col-xs-6 col-sm-6 text-right">宿舍总用电量:</label>
										<label id="aou_dormitoryElectricity_dorTotalElectricity" class="pull-left">***</label>
										<label class="pull-left">&nbsp;&nbsp;KWH</label>
									</div>
									<div class="admin-lable-input form-group col-xs-4 col-sm-4">
										<label id="" class="col-xs-6 col-sm-6 text-right">冲值前剩余电量:</label>
										<label id="aou_dormitoryElectricity_dorSurplusElectricity" class="pull-left">***</label>
										<label class="pull-left">&nbsp;&nbsp;KWH</label>
									</div>
								</fieldset>
								<fieldset>
									<legend class="default">
										<font style="color: #3B315A;font-size:18px;" class="glyphicon  glyphicon-flash" aria-hidden="true">&nbsp;电费充值</font>
									</legend>
									<div class="admin-lable-input form-group col-xs-8 col-sm-8">
										<label for="aou_dormitoryElectricity_city" class="col-xs-2 col-sm-2 text-right">电价选择*</label>
										<select id="aou_dormitoryElectricity_city" name="aou_dormitoryElectricity_city" class="spec-form-control col-xs-3 col-sm-3">
	      									<option value="">-城市-</option>
	      									<c:forEach items="${electricityPriceCityList}" var="electricityPriceCity">
												<option value="${electricityPriceCity}"/>${electricityPriceCity}
											</c:forEach>
	   									</select>
										<label class="pull-left">&nbsp;--&nbsp;</label>
										<select id="aou_dormitoryElectricity_type" name="aou_dormitoryElectricity_type" class="spec-form-control col-xs-3 col-sm-3">
	      									<option value="">-类型-</option>
	   									</select>
										<label class="pull-left">&nbsp;--&nbsp;</label>
										<div class="input-group col-xs-3 col-sm-3">
											<input type="text" placeholder="电价" readonly="readonly" id="aou_dormitoryElectricity_price" name="aou_dormitoryElectricity_price" class="spec-form-control"/>
											<span class="input-group-addon">元/KWH</span>
										</div>
									</div>			
									<div class="admin-lable-input form-group col-xs-4 col-sm-4">
										<label for="aou_dormitoryElectricity_rechargeMoney" class="col-xs-4 col-sm-4 text-right">充值金额*</label>
										<div class="input-group   col-xs-8 col-sm-8">
											<input type="text" placeholder="" id="aou_dormitoryElectricity_rechargeMoney" name="aou_dormitoryElectricity_rechargeMoney" class="spec-form-control"/>
											<span class="input-group-addon">元</span>
										</div>
									</div>
									<div class="admin-lable-input form-group col-xs-4 col-sm-4">
										<label for="aou_dormitoryElectricity_rechargeElectricity" class="col-xs-4 col-sm-4 text-right">充值电量*</label>
										<div class="input-group   col-xs-8 col-sm-8">
											<input type="text" placeholder="" id="aou_dormitoryElectricity_rechargeElectricity" name="aou_dormitoryElectricity_rechargeElectricity" class="spec-form-control"/>
											<span class="input-group-addon">KWH</span>
										</div>
									</div>
									<div class="admin-lable-input form-group col-xs-4 col-sm-4">
										<label for="aou_dormitoryElectricity_supplusAfterRecharge" class="col-xs-4 col-sm-4 text-right">充值后电量</label>
										<div class="input-group   col-xs-8 col-sm-8">
											<input type="text" placeholder="" readonly="readonly" id="aou_dormitoryElectricity_supplusAfterRecharge" name="aou_dormitoryElectricity_supplusAfterRecharge" class="spec-form-control"/>
											<span class="input-group-addon">KWH</span>
										</div>
									</div>				
									<div class="form-group">
										<label class=""></label>
									</div>									
									<div class="admin-lable-btn form-group col-xs-3 col-sm-3">
	      								<button type="button" style="background-color:#3B315A" id="aou_addDormitoryElectricity_btn" class="col-xs-8 col-sm-8 center-block btn btn-primary">充值</button>
	  								</div>
								</fieldset>
							</form>
						</div>
						<!-- 宿舍缴费详情   -- 结束-->
					</div>
				</div>
			</div>
		</div>
		<div class="spec-footer" id="footerpage">
			<span style="text-align: center;">ATAT</a> 2016-2017 © 版权所有 桂GPS经108.28243814232792号|桂GPS纬22.803510872893987号</span>
 		</div>
 		 <!-- 修改密码模态窗 -->
		<div class="am-modal am-modal-confirm" tabindex="-1" id="accountCenter_changePassword">
			<div class="am-modal-dialog" style="border-radius: 10px;">
				<div class="am-modal-bd popup-title" style="border-top-left-radius: 10px; border-top-right-radius: 10px;">
					<p>修改密码</p>
				</div>
				<form class="admin-select-input-group form-horizontal clearfix" role="form">
					<div class="admin-lable-input form-group col-xs-11 col-sm-11">
						<label for="accountCenter_changePwd_oldPwd" class="col-xs-4 col-sm-4 text-right">原密码</label>
						<input type="text" placeholder="" id="accountCenter_changePwd_oldPwd" name="accountCenter_changePwd_oldPwd" class="spec-form-control col-xs-8 col-sm-8"/>
					</div>
					<div id="msg-info" class="alert-danger spec-hidden"style="text-align: center; padding: 2px 0px;">msg</div>
			       	<div class="admin-lable-input form-group col-xs-11 col-sm-11">
						<label for="accountCenter_changePwd_newPwd" class="col-xs-4 col-sm-4 text-right">新密码</label>
						<input type="text" placeholder="" id="accountCenter_changePwd_newPwd" name="accountCenter_changePwd_newPwd" class="spec-form-control col-xs-8 col-sm-8"/>
					</div>
				</form>
				<div class="am-modal-footer">
					<div class="am-modal-btn" style="border-right: 0px;">
						<input class="alert-btn-blue" data-am-modal-confirm value="确定" type="button"/>
					</div>
					<div class="am-modal-btn" style="border-left: 0px;">
						<input class="alert-btn-white" data-am-modal-cancel value="取消" type="button"/>
					</div>
				</div>
			</div>
		</div>
	</body>
	<!-- 修改/添加成功的拟态窗口 -->
	<div class="am-modal am-modal-confirm" tabindex="-1" id="infoModal_stuIndexcenter_succcess">
		<div class="am-modal-dialog am-text-center"
			style="border: 5px solid #a8a9ab; background-color: #FFFFFF;">
			<div style="font-size: 12px; margin-top: 24px;">
				<img alt="" src="${path}/page/img/success.png">
				<span id="infoModal_stuIndexcenter_succcess_msg">修改成功</span><br>
			</div>
		</div>
	</div>
	<!-- 修改/添加失败的拟态窗口 -->
	<div class="am-modal am-modal-confirm" tabindex="-1" id="infoModal_stuIndexcenter_error">
		<div class="am-modal-dialog am-text-center"
			style="border: 5px solid red; background-color: #FFFFFF;">
			<div style="font-size: 12px; margin-top: 24px;">
				<img alt="" src="${path}/page/img/error.png">
				<span id="infoModal_stuIndexcenter_error_msg">修改失败</span><br>
			</div>
		</div>
	</div>
	<!-- 确认完成支付模态窗 -->
	<div class="am-modal am-modal-confirm" tabindex="-1" id="dpr_toChargeGateway">
		<div class="am-modal-dialog" style="border-radius: 10px;">
			<div class="am-modal-bd popup-title" style="border-top-left-radius: 10px; border-top-right-radius: 10px;">
				<p>请扫码付款<span id="gatewayChar">**</span>元</p>
			</div>
			<div style="height: 400px; margin: 0px 20px 20px 20px; border: 1px solid #D7D7D7; padding: 1px;">
				<img style="max-height: 100%;" class="img-responsive center-block img-rounded" alt="" src="${path}/page/common/img/admin/payQRCode.jpg">
			</div>
			<div class="am-modal-footer">
				<div class="am-modal-btn" style="border-right: 0px;">
					<input class="alert-btn-blue" data-am-modal-confirm value="已付款" type="button"/>
				</div>
				<div class="am-modal-btn" style="border-left: 0px;">
					<input class="alert-btn-white" data-am-modal-cancel value="取消" type="button"/>
				</div>
			</div>
		</div>
	</div>
	<!-- 确认完成支付模态窗 -->
	<div class="am-modal am-modal-confirm" tabindex="-1" id="dec_electricityCharge">
		<div class="am-modal-dialog" style="border-radius: 10px;">
			<div class="am-modal-bd popup-title" style="border-top-left-radius: 10px; border-top-right-radius: 10px;">
				<p>请扫码付款<span id="electricityPayChar">**</span>元</p>
			</div>
			<div style="height: 400px; margin: 0px 20px 20px 20px; border: 1px solid #D7D7D7; padding: 1px;">
				<img style="max-height: 100%;" class="img-responsive center-block img-rounded" alt="" src="${path}/page/common/img/admin/payQRCode.jpg">
			</div>
			<div class="am-modal-footer">
				<div class="am-modal-btn" style="border-right: 0px;">
					<input class="alert-btn-blue" data-am-modal-confirm value="已付款" type="button"/>
				</div>
				<div class="am-modal-btn" style="border-left: 0px;">
					<input class="alert-btn-white" data-am-modal-cancel value="取消" type="button"/>
				</div>
			</div>
		</div>
	</div>
</html>
