//初始化页面
$(document).ready(function(){
	student_autoResize();
	/*窗体缩放事件*/
	$(window).resize(function(){
		student_autoResize();
	});
	doTurnPage_prorertyRepair(1);
	doTurnPage_dormitoryElectricity(1);
	$("#prorertyRepairLog_tab").on("click",function(){
		doTurnPage_prorertyRepair(1);
	});
	$("#dormitoryElectricityLog_tab").on("click",function(){
		doTurnPage_dormitoryElectricity(1);
	});
	//切换Tab
	$('#myTab a').click(function (e) {
	      e.preventDefault()
	      $(this).tab('show')
	});
	$('#myTab li:eq(0) a').tab('show');
	
	//财产报修页面-查询点击事件
	$("#getGatewayList_btn").on("click",function(){
		doTurnPage_prorertyRepair(1);
	});
	
	//财产报修页面-底部分页“GO”按钮跳转到指定页面
	$("#commonpage_gateway").on("click","#turnTo",function() {
		doTurnPage_prorertyRepair($("#commonpage_gateway #toPage").val());
	});
	
	//财产报修页面-新增按钮
	$("#prorertyRepair_list").on("click","#addGateway_btn",function(){
		$("#aou_addGateway_btn").removeAttr("gatewayLogId");
		if ($("#addGatewaySelect").hasClass("spec-none")) {
			$("#addGatewaySelect").removeClass("spec-none");
			$("#viewGatewayInput").addClass("spec-none");
		}
		$("#aou_prorertyRepair_contactName").val(student.name);
		$("#aou_prorertyRepair_contactPhone").val("");
		$("#aou_prorertyRepair_description").val("");
		if ($("#addOrUpdateGateway").hasClass("spec-none")) {
			$("#addOrUpdateGateway").removeClass("spec-none").siblings().addClass("spec-none");
		}
	});
	
	//新增/更新财产报修记录页面返回按钮
	$("#addOrUpdateGateway").on("click","#aou_cancle_btn",function(){
		doTurnPage_prorertyRepair(1);
		if ($("#prorertyRepair_list").hasClass("spec-none")) {
			$("#prorertyRepair_list").removeClass("spec-none").siblings().addClass("spec-none");
		}
	});
	
	//新增财产报修 -- 财产所在门牌号下拉框 -- 依据宿舍id获取宿舍内部财产
	$("#add_prorertyRepair_innerNumber").focus(function(){pr_getpropertyBydormitory();});
	
	//新增/更新财产保修 -- 保存按钮
	$("#addOrUpdateGateway").on("click","#aou_addGateway_btn",function(){
		$.ajax({
			url : path+"/addOrUpdateGateway",
			type : "post",
			dataType : "json",
			data : {
				"id" : $("#aou_addGateway_btn").attr("gatewayLogId"),
				"dormitoryId" : student.dormitoryId,
				"propertyId" : $("#add_prorertyRepair_innerNumber").val(),
				"contactsName" : $.trim($("#aou_prorertyRepair_contactName").val()),
				"contactsPhone" : $.trim($("#aou_prorertyRepair_contactPhone").val()),
				"repairDescription" : $.trim($("#aou_prorertyRepair_description").val()),
			},
			success : function(data) {
				if(data.result == "success"){
					$("#infoModal_stuIndexcenter_succcess_msg").html("新增/更新财产保修记录成功");
					$('#infoModal_stuIndexcenter_succcess').modal({
						relatedTarget : this,
						width : 141,
						height : 72
					});
					setTimeout(function() {
						$('#infoModal_stuIndexcenter_succcess').modal({
							relatedTarget : this,
						});
					}, 1000);
				} else {
					$("#infoModal_stuIndexcenter_error_msg").html(data.error);
					$('#infoModal_stuIndexcenter_error').modal({
						relatedTarget : this,
						width : 141,
						height : 72
					});
					setTimeout(function() {
						$('#infoModal_stuIndexcenter_error').modal({
							relatedTarget : this,
						});
					}, 1000);
				}
			}
		});
	});
	
	//较验日期输入框
	$('#gateway_minCreatedDate').datepicker().on('changeDate.datepicker.amui', function(event) {
		var max = $('#gateway_maxCreatedDate').val();
		var endDate = new Date(max);
		if((max!=null)&&(max!="")){
			if (event.date.valueOf() > endDate.valueOf()) {
				alert('开始日期应小于结束日期！');
				$('#gateway_minCreatedDate').val("");
			} else {
				var startDate = new Date(event.date);
				$('#gateway_minCreatedDate').val(startDate.format("yyyy-MM-dd"));
			}
			//$(this).datepicker('close');
		}
    });

	$('#gateway_maxCreatedDate').datepicker().on('changeDate.datepicker.amui', function(event) {
		var min = $('#gateway_minCreatedDate').val();
		var startDate = new Date(min);
		if((min!=null)&&(min!="")){
			if (event.date.valueOf() < startDate.valueOf()) {
				alert('结束日期应大于开始日期！');
				$('#gateway_maxCreatedDate').val("");
			} else {
				var endDate = new Date(event.date);
		    	$('#gateway_maxCreatedDate').val(endDate.format("yyyy-MM-dd"));
			}
			//$(this).datepicker('close');
		}
	});
	
	//点击名字修改密码
	$("#student-center-id").on("click",function(){
		stucenter_changePassword();
	});
	
	//宿舍缴费页面-查询点击事件
	$("#getDormitoryElectricity_btn").on("click",function(){
		doTurnPage_dormitoryElectricity(1);
	});
	
	//宿舍缴费页面-底部分页“GO”按钮跳转到指定页面
	$("#commonpage_dormitoryElectricity").on("click","#turnTo",function() {
		doTurnPage_dormitoryElectricity($("#commonpage_dormitoryElectricity #toPage").val());
	});
	
	//较验剩余电量输入框
	$("#dormitoryElectricity_minSurplus,#dormitoryElectricity_maxSurplus").on("change",function(){checkSurplusElectricity_electricity();});
	$("#dormitoryElectricity_minSurplus,#dormitoryElectricity_maxSurplus").blur(function(){checkSurplusElectricity_electricity();});
	
	//电费缴纳  -- 电价选择-城市下拉框-依据城市获取类型
	$("#addDormitoryElectricity").on("change","#aou_dormitoryElectricity_city",function(){
		de_getPriceTypeBycity();
	});
	$("#aou_dormitoryElectricity_type").focus(function(){
		de_getPriceTypeBycity();
	});
	
	//电费缴纳 -- 电价选择-电价类型下拉框-依据类型获取电价
	$("#addDormitoryElectricity").on("change","#aou_dormitoryElectricity_type",function(){
		de_getPriceByType();
	});
	$("#aou_dormitoryElectricity_price").focus(function(){
		de_getPriceByType();
	});
	//电费缴纳  -- 充值金额-依据电价及充值金额自动补齐充值电量以及充值后电量
	$("#aou_dormitoryElectricity_rechargeMoney").blur(function() {
		getRechargeElectricityByMoney();
	});
	$("#aou_dormitoryElectricity_rechargeMoney").change(function() {
		getRechargeElectricityByMoney();
	});
	$("#aou_dormitoryElectricity_rechargeMoney").keyup(function() {
		getRechargeElectricityByMoney();
	});
	
	//电费缴纳  -- 充值电量-依据电价及充值金额自动补齐充值电量以及充值后电量
	$("#aou_dormitoryElectricity_rechargeElectricity").blur(function() {
		getRechargeMoneyByElectricity();
	});
	$("#aou_dormitoryElectricity_rechargeElectricity").change(function() {
		getRechargeMoneyByElectricity();
	});
	$("#aou_dormitoryElectricity_rechargeElectricity").keyup(function() {
		getRechargeMoneyByElectricity();
	});
	
	//宿舍电费缴纳  确定按钮
	$("#addDormitoryElectricity").on("click","#aou_addDormitoryElectricity_btn",function(){
		if(("" == $.trim($("#aou_dormitoryElectricity_rechargeMoney").val())) || (null == $.trim($("#aou_dormitoryElectricity_rechargeMoney").val()))
				|| ("" == $("#aou_dormitoryElectricity_price").val()) || (null == $("#aou_dormitoryElectricity_price").val())){
			alert("请先选择电价及充值金额");
		} else {
			$("#electricityPayChar").html(Number($.trim($("#aou_dormitoryElectricity_rechargeMoney").val())));
			$("#dec_electricityCharge").modal({
				relatedTarget : this,
				width : 434,
				onConfirm : function() {
					$.ajax({
						url : path+"/addDormitoryElectricityLog",
						type : "post",
						dataType : "json",
						data : {
							"dormitoryId" : $("#aou_addDormitoryElectricity_btn").attr("dormitoryId"),
							"rechargeMoney" :Number( $.trim($("#aou_dormitoryElectricity_rechargeMoney").val())),
							"electircityPrice" : Number($("#aou_dormitoryElectricity_price").val()),
						},
						success : function(data) {
							if(data.result == "success"){
								$("#infoModal_stuIndexcenter_succcess_msg").html("充值成功");
								$('#infoModal_stuIndexcenter_succcess').modal({
									relatedTarget : this,
									width : 141,
									height : 72
								});
								setTimeout(function() {
									$('#infoModal_stuIndexcenter_succcess').modal({
										relatedTarget : this,
									});
									$("#electricityPayChar").html("**");
									//重新加载页面
									dormitoryAddElectricity($("#aou_addDormitoryElectricity_btn").attr("dormitoryId"));
								}, 1000);
							} else {
								$("#infoModal_stuIndexcenter_error_msg").html(data.error);
								$('#infoModal_stuIndexcenter_error').modal({
									relatedTarget : this,
									width : 141,
									height : 72
								});
								setTimeout(function() {
									$('#infoModal_stuIndexcenter_error').modal({
										relatedTarget : this,
									});
									$("#electricityPayChar").html("**");
								}, 1000);
							}
						}
					});
				},
				onCancel : function() {
				}
			});
		}
	});
	
	//电费缴纳页面返回按钮
	$("#addDormitoryElectricity").on("click","#aou_cancle_btn",function(){
		doTurnPage_dormitoryElectricity(1);
		if ($("#dormitoryElectricityLog_list").hasClass("spec-none")) {
			$("#dormitoryElectricityLog_list").removeClass("spec-none").siblings().addClass("spec-none");
		}
	});
	
	//显示缴费页面
	$("#dormitoryElectricityLog_list").on("click","#addElectricityCharge_btn",function(){
		dormitoryAddElectricity();
	});
	
});
//宿舍缴费分页
var doTurnPage_dormitoryElectricity = function(currentPage) {
	$.ajax({
		url : path+"/pageTurnServlet",
		type : "post",
		dataType : "json",
		data : {
			"StatementName":"dormitorySpecDaoImpl.dormitoryElectricityPageGrade",
			"rows":7,
			"page":currentPage,
			"rechargeMoney" : $.trim($("#dormitoryElectricity_rechargeMoney").val()),
			"minSurplus" : $.trim($("#dormitoryElectricity_minSurplus").val()),
			"maxSurplus" : $.trim($("#dormitoryElectricity_maxSurplus").val()),
			"dormitoryId" :student.dormitoryId,
		},
		success : function(data) {
			var str = "";
			if (data.data.length == 0) {
				str = "<div class='no_data'><div class='no_data_inner'><img alt='' src='"
					+path
					+ "/page/common/img/nodata.png'/>&nbsp;<span style='font-color:grey;'>查询数据为空</span></div></div>";
				$("#table-dormitoryElectricityList").html(str);
				$("#table-dormitoryElectricityList").show();
				$("#table-content-dormitoryElectricityList").hide();
			} else {
				$("#table-dormitoryElectricityList").empty();
				$("#table-dormitoryElectricityList").hide();
				$("#table-content-dormitoryElectricityList").show();
				for (i = 0; i < data.data.length; i++) {
					// 获取充值记录id
					var id = data.data[i].id;
					// 获取宿舍楼
					var rechargeBuilding = ((data.data[i].rechargeBuilding == null)||($.trim(data.data[i].rechargeBuilding) == "")) ? "":data.data[i].rechargeBuilding;
					// 获取门牌号
					var rechargeRoom = ((data.data[i].rechargeRoom == null)||($.trim(data.data[i].rechargeRoom) == "")) ? "":("&nbsp;-&nbsp;"+data.data[i].rechargeRoom)+"室";
					// 获取充值前剩余电量
					var surplusBeforeRecharge = ((data.data[i].surplusBeforeRecharge == null)||($.trim(data.data[i].surplusBeforeRecharge) == "")) ? "":data.data[i].surplusBeforeRecharge+"&nbsp;KWH";
					// 获取充值金额
					var rechargeMoney = ((data.data[i].rechargeMoney == null)||($.trim(data.data[i].rechargeMoney) == "")) ? "":data.data[i].rechargeMoney+"&nbsp;元";
					// 获取用电单价
					var electircityPrice = ((data.data[i].electircityPrice == null)||($.trim(data.data[i].electircityPrice) == "")) ? "":data.data[i].electircityPrice+"&nbsp;元/KWH";
					// 获取充值电量
					var rechargeElectricity = ((data.data[i].rechargeElectricity == null)||($.trim(data.data[i].rechargeElectricity) == "")) ? "":data.data[i].rechargeElectricity+"&nbsp;KWH";
					// 获取充值后剩余电量
					var surplusAfterRecharge = ((data.data[i].surplusAfterRecharge == null)||($.trim(data.data[i].surplusAfterRecharge) == "")) ? "":data.data[i].surplusAfterRecharge+"&nbsp;KWH";
					str += "<tr><td><div class='center-block short_three_nowrap'>"
							+ (i + 1)
							+ "</div></td>" 
							+ "<td><div class='center-block short_nowrap'>"
							+ rechargeBuilding + rechargeRoom
							+ "</div></td>" 
							+ "<td><div class='center-block short_narrow_nowrap'>"
							+ surplusBeforeRecharge
							+ "</div></td>"
							+ "<td><div class='center-block short_narrow_nowrap'>"
							+ rechargeMoney
							+ "</div></td>"
							+ "<td><div class='center-block short_narrow_nowrap'>"
							+ electircityPrice
							+ "</div></td>"
							+ "<td><div class='center-block short_narrow_nowrap'>"
							+ rechargeElectricity
							+ "</div></td>"
							+ "<td><div class='center-block short_narrow_nowrap'>"
							+ surplusAfterRecharge
							+ "</div></td></tr>";
				}
				$("#updateTb_dormitoryElectricity").html(str);
				var fenye = "doTurnPage_dormitoryElectricity";
				var str_fenye = fenye_admin(data.page, data.total,fenye);				
				$("#commonpage_dormitoryElectricity").empty();
				$("#commonpage_dormitoryElectricity").html(str_fenye);
				$("#commonpage_dormitoryElectricity #toPage").val(currentPage);
			}
		}
	});
}

//宿舍电费缴纳记录页面剩余电量输入框较验
function checkSurplusElectricity_electricity(){
	var minSurplus = $("#dormitoryElectricity_minSurplus").val();
	var maxSurplus = $("#dormitoryElectricity_maxSurplus").val();
	if((minSurplus!=null)&&(maxSurplus!=null)&&(minSurplus!="")&&(maxSurplus!="")&&(minSurplus>maxSurplus)){
	//提示
	alert("最大剩余电量不能小于最小剩余电量");
	$("#dormitoryElectricity_maxSurplus").val("");
	}
}


//显示宿舍电费缴纳页面
var dormitoryAddElectricity = function(){
	$("#aou_dormitoryElectricity_rechargeMoney").val("");
	$("#aou_dormitoryElectricity_rechargeElectricity").val("");
	$("#aou_dormitoryElectricity_supplusAfterRecharge").val("");
	
	//查询获取宿舍信息
	$.ajax({
		url : path+"/getReactDormitory",
		type : "post",
		dataType : "json",
		data : {
			"id":student.dormitoryId,
		},
		success : function(data) {
			if (data.result == "success") {
				$("#aou_dormitoryElectricity_dorBuilding").html(data.operationResult[0].building);
				$("#aou_dormitoryElectricity_dorRoom").html(data.operationResult[0].room);
				$("#aou_dormitoryElectricity_dorTotalElectricity").html(data.operationResult[0].totalElectricity);
				$("#aou_dormitoryElectricity_dorSurplusElectricity").html(data.operationResult[0].surplusElectricity);
				$("#addDormitoryElectricity #aou_addDormitoryElectricity_btn").attr("dormitoryId",student.dormitoryId);
			} else {
				$("#addDormitoryElectricity #aou_addDormitoryElectricity_btn").removeAttr("dormitoryId");
				$("#aou_dormitoryElectricity_dorBuilding").html("***");
				$("#aou_dormitoryElectricity_dorRoom").html("***");
				$("#aou_dormitoryElectricity_dorSurplusElectricity").html("***");
				console.log(data.error);
			}
		}
	});
	if ($("#addDormitoryElectricity").hasClass("spec-none")) {
		$("#addDormitoryElectricity").removeClass("spec-none").siblings().addClass("spec-none");
	}
}

//电费缴纳  -- 电价选择-城市下拉框-依据城市获取类型
var de_getPriceTypeBycity = function(){
	if("" != $.trim($("#aou_dormitoryElectricity_city").val())){
		$.ajax({
			url : path+"/getReactElectricityPrice",
			type : "post",
			dataType : "json",
			data : {
				"city" : $("#aou_dormitoryElectricity_city").val(),
			},
			success : function(data) {
				if(data.result == "success"){
					$("#aou_dormitoryElectricity_type").empty();
					for(i=0;i<data.operationResult.length;i++){
						// 获取电价对应类型
						var type = data.operationResult[i].type;
						if((type == 1) || (type == "1")){
							type = "居民生活电价";
						} else if((type == 2) || (type == "2")){
							type = "非居民生活电价";
						} else if((type == 3) || (type == "3")){
							type = "农业生产电价";
						} else if((type == 4) || (type == "4")){
							type = "商业电价";
						} else if((type == 5) || (type == "5")){
							type = "普通工业电价";
						} else if((type == 6) || (type == "6")){
							type = "大工业电价";
						} else if((type == 7) || (type == "7")){
							type = "非工业电价";
						} else if((type == 8) || (type == "8")){
							type = "趸售电价";
						} else{
							type = "";
						} 
						$("#aou_dormitoryElectricity_type").append("<option value='"+data.operationResult[i].id+"'>"+type+"</option>");
					}
					de_getPriceByType();
				}  else {
					$("#aou_dormitoryElectricity_type").empty();
					console.log("依据城市获取电价类型失败:"+data.error);
				}
			}
		});
	} else {
		$("#aou_dormitoryElectricity_type").empty();
		$("#aou_dormitoryElectricity_type").append("<option value=''>-类型-</option>");
	}
}
//电费缴纳 -- 电价选择-电价类型下拉框-依据类型获取电价
var de_getPriceByType = function(){
	if("" != $.trim($("#aou_dormitoryElectricity_type").val())){
		$.ajax({
			url : path+"/getReactElectricityPrice",
			type : "post",
			dataType : "json",
			data : {
				"id" : $("#aou_dormitoryElectricity_type").val(),
			},
			success : function(data) {
				if(data.result == "success"){
					$("#aou_dormitoryElectricity_price").val("");
						if((null != data.operationResult[0].price) && ("" != $.trim(data.operationResult[0].price))){
							$("#aou_dormitoryElectricity_price").val(data.operationResult[0].price);
					}
						$("#aou_dormitoryElectricity_rechargeMoney").val();
						$("#aou_dormitoryElectricity_rechargeElectricity").val();
				} else {
					$("#aou_dormitoryElectricity_price").val("");
					console.log("依据依据类型获取电价失败:"+data.error);
				}
			}
		});
	} else {
		$("#aou_dormitoryElectricity_price").val("");
	}
}

//依据电价及充值金额计算充值电量及充值后剩余电量
var getRechargeElectricityByMoney = function(){
	var rechargeMoney = Number($.trim($("#aou_dormitoryElectricity_rechargeMoney").val()));
	var electricityPrice =  Number($.trim($("#aou_dormitoryElectricity_price").val()));
	var dorSurplusElectricity = Number($.trim($("#aou_dormitoryElectricity_dorSurplusElectricity").html()));
	if((null != rechargeMoney) && (!isNaN(rechargeMoney))
			&& (null != electricityPrice) && (!isNaN(electricityPrice))
			&& (0 != electricityPrice)){
		var rechargeElectricity = rechargeMoney/electricityPrice;
		$("#aou_dormitoryElectricity_rechargeElectricity").val(rechargeElectricity);
		if((null != dorSurplusElectricity) && (!isNaN(dorSurplusElectricity))){
			$("#aou_dormitoryElectricity_supplusAfterRecharge").val(dorSurplusElectricity+rechargeElectricity);
		}
	}
}

//依据电价及充值电量计算充值电价及充值后剩余电量
var getRechargeMoneyByElectricity = function(){
	var rechargeElectricity = Number($.trim($("#aou_dormitoryElectricity_rechargeElectricity").val()));
	var electricityPrice =  Number($.trim($("#aou_dormitoryElectricity_price").val()));
	var dorSurplusElectricity = Number($.trim($("#aou_dormitoryElectricity_dorSurplusElectricity").html()));
	if((null != rechargeElectricity) && (!isNaN(rechargeElectricity))
			&& (null != electricityPrice) && (!isNaN(electricityPrice)) 
			&& (0 != electricityPrice)){
		var rechargeMoney = rechargeElectricity*electricityPrice;
		$("#aou_dormitoryElectricity_rechargeMoney").val(rechargeMoney);
		if((null != dorSurplusElectricity) && (!isNaN(dorSurplusElectricity))){
			$("#aou_dormitoryElectricity_supplusAfterRecharge").val(dorSurplusElectricity+rechargeElectricity);
		}
	}
}

//学生修改密码
var stucenter_changePassword = function(){
	//弹出模态窗 输入新的密码 后保存
	$("#accountCenter_changePassword").attr("studentId",student.id);
	$("#accountCenter_changePwd_newPwd").attr("");
	 $("#accountCenter_changePwd_oldPwd").val("");
	$("#accountCenter_changePassword").modal({
		relatedTarget : this,
		width : 434,
		onConfirm : function() {
			var id = $("#accountCenter_changePassword").attr("studentId");
			var oldPassword = $("#accountCenter_changePwd_oldPwd").val();
			var newPassword = $("#accountCenter_changePwd_newPwd").val();
			if((null != oldPassword) && ("" != oldPassword)
				&&(null != newPassword) && ("" != newPassword)){
					$.ajax({
						url : path+"/getReactStudent",
						type : "post",
						dataType : "json",
						data : {
							"id":id,
							"password":oldPassword,
						},
						success : function(data) {
							if ((data.result == "success") && (0 < data.operationResult.length)) {
								$.ajax({
									url : path+"/addOrUpdateStudent",
									type : "post",
									dataType : "json",
									data : {
										"id":id,
										"password":newPassword,
									},
									success : function(data) {
										if (data.result == "success") {
											$("#infoModal_stuIndexcenter_succcess_msg").html("修改密码成功");
											$('#infoModal_stuIndexcenter_succcess').modal({
												relatedTarget : this,
												width : 141,
												height : 72
											});
											setTimeout(function() {
												$('#infoModal_stuIndexcenter_succcess').modal({
													relatedTarget : this,
												});
												$("#accountCenter_changePassword").removeAttr("studentId");
												$("#accountCenter_changePassword").removeAttr("currentPage");
												parent.window.location.href = path+"/studentLogin";
											}, 1000);
										} else {
											$("#infoModal_stuIndexcenter_error_msg").html("失败-"+data.error);
											$('#infoModal_stuIndexcenter_error').modal({
												relatedTarget : this,
												width : 141,
												height : 72
											});
											setTimeout(function() {
												$('#infoModal_stuIndexcenter_error').modal({
													relatedTarget : this,
												});
												$("#accountCenter_changePassword").removeAttr("studentId");
												$("#accountCenter_changePassword").removeAttr("currentPage");
											}, 1000);
										}
									}
								});
							} else {
								$("#infoModal_stuIndexcenter_error_msg").html("失败-请输入正确原密码");
								$('#infoModal_stuIndexcenter_error').modal({
									relatedTarget : this,
									width : 141,
									height : 72
								});
								setTimeout(function() {
									$('#infoModal_stuIndexcenter_error').modal({
										relatedTarget : this,
									});
									$("#accountCenter_changePassword").removeAttr("studentId");
									$("#accountCenter_changePassword").removeAttr("currentPage");
								}, 1000);
							}
						}
					});
				} else {
					$("#infoModal_stuIndexcenter_error_msg").html("失败-请输入正确原密码");
					$('#infoModal_stuIndexcenter_error').modal({
						relatedTarget : this,
						width : 141,
						height : 72
					});
					setTimeout(function() {
						$('#infoModal_stuIndexcenter_error').modal({
							relatedTarget : this,
						});
						$("#accountCenter_changePassword").removeAttr("studentId");
						$("#accountCenter_changePassword").removeAttr("currentPage");
					}, 1000);
				}
			
		},
		onCancel : function() {
		}
	});
}

//依据宿舍ID更新房间号
var pr_getpropertyBydormitory =	function(){
	$.ajax({
		url : path+"/getReactProperty",
		type : "post",
		dataType : "json",
		data : {
			"dormitoryId" : student.dormitoryId,
		},
		success : function(data) {
			if(data.result == "success"){
				$("#add_prorertyRepair_innerNumber").empty();
				for(i=0;i<data.operationResult.length;i++){
					if((null != data.operationResult[i].propertyInnerNumber) && ("" != $.trim(data.operationResult[i].propertyInnerNumber))){
						$("#add_prorertyRepair_innerNumber").append("<option value='"+data.operationResult[i].id+"'>"+data.operationResult[i].propertyInnerNumber+"号:"+data.operationResult[i].customName+"</option>");
					}
				}
			} else {
				console.log("依据宿舍id获取宿舍内部财产失败:"+data.error);
			}
		}
	});
}

//财产报修分页
var doTurnPage_prorertyRepair = function(currentPage) {
	$.ajax({
		url : path+"/pageTurnServlet",
		type : "post",
		dataType : "json",
		data : {
			"StatementName":"propertySpecDaoImpl.gatewayLogPageGrade",
			"rows":7,
			"page":currentPage,
			"minCreatedDate" : $("#gateway_minCreatedDate").val(),
			"maxCreatedDate" : $("#gateway_maxCreatedDate").val(),
			"contactsName" : $.trim($("#gateway_contactName").val()),
			"contactsPhone" : $.trim($("#gateway_contactPhone").val()),
			"repairState" : $("#gateway_fixedOk").val(),
			"chargeState" : $("#gateway_isCharge").val(),
			"dormitoryId" : student.dormitoryId,
		},
		success : function(data) {
			var str = "";
			if (data.data.length == 0) {
				str = "<div class='no_data'><div class='no_data_inner'><img alt='' src='"
					+path
					+ "/page/common/img/nodata.png'/>&nbsp;<span style='font-color:grey;'>查询数据为空</span></div></div>";
				$("#table-gatewayList").html(str);
				$("#table-gatewayList").show();
				$("#table-content-gatewayList").hide();
			} else {
				$("#table-gatewayList").empty();
				$("#table-gatewayList").hide();
				$("#table-content-gatewayList").show();
				for (i = 0; i < data.data.length; i++) {
					// 获取财产id
					var id = data.data[i].id;
					// 获取宿舍楼
					var dormitoryBuilding = ((data.data[i].dormitoryBuilding == null)||($.trim(data.data[i].dormitoryBuilding) == "")) ? "":data.data[i].dormitoryBuilding;
					// 获取门牌号
					var dormitoryRoom = ((data.data[i].dormitoryRoom == null)||($.trim(data.data[i].dormitoryRoom) == "")) ? "":("&nbsp;-&nbsp;"+data.data[i].dormitoryRoom+"室");
					// 获取财产宿舍内部编号
					var propertyInnerNumber = ((data.data[i].propertyInnerNumber == null)||($.trim(data.data[i].propertyInnerNumber) == "")) ? "":("&nbsp;-&nbsp;编号:"+data.data[i].propertyInnerNumber);
					// 获取财产类型名称
					var propertyTypeName = ((data.data[i].propertyTypeName == null)||($.trim(data.data[i].propertyTypeName) == "")) ? "":data.data[i].propertyTypeName;
					// 获取财产名称
					var propertyCustomName = ((data.data[i].propertyCustomName == null)||($.trim(data.data[i].propertyCustomName) == "")) ? "":("&nbsp;-&nbsp;"+data.data[i].propertyCustomName);
					// 获取报修人姓名
					var contactsName = ((data.data[i].contactsName == null)||($.trim(data.data[i].contactsName) == "")) ? "":data.data[i].contactsName;
					// 获取报修人电话
					var contactsPhone = ((data.data[i].contactsPhone == null)||($.trim(data.data[i].contactsPhone) == "")) ? "":data.data[i].contactsPhone;
					// 获取报修日期
					var createdDate_str = ((data.data[i].createdDate == null)||($.trim(data.data[i].createdDate) == "")) ? "":data.data[i].createdDate;
					var createdDate = new Date(createdDate_str).format("yyyy-MM-dd hh:mm");
					// 获取维修费用
					var pepairCharge = ((data.data[i].pepairCharge == null)||($.trim(data.data[i].pepairCharge) == "")) ? "":(data.data[i].pepairCharge);
					// 获取维修进度
					var repairState ="";
					if((data.data[i].repairState == 0) || ($.trim(data.data[i].repairState) == "0")){
						repairState ="已报修";
					} else if((data.data[i].repairState == 1) || ($.trim(data.data[i].repairState) == "1")){
						repairState ="已修好";
					}
					// 获取付费进度
					var chargeState ="";
					if((data.data[i].chargeState == 0) || ($.trim(data.data[i].chargeState) == "0")){
						chargeState ="未支付";
					} else if((data.data[i].chargeState == 1) || ($.trim(data.data[i].chargeState) == "1")){
						chargeState ="已支付";
					}
					str += "<tr><td><div class='center-block short_three_nowrap'>"
							+ (i + 1)
							+ "</div></td>" 
							+ "<td><div class='center-block normal_nowrap'>"
							+ dormitoryBuilding +dormitoryRoom+propertyInnerNumber+"&nbsp;-&nbsp;"+propertyTypeName+propertyCustomName
							+ "</div></td>" 
							+ "<td><div class='center-block short_narrow_nowrap'>"
							+ createdDate
							+ "</div></td>"
							+ "<td><div class='center-block short_narrow_nowrap'>"
							+ contactsName
							+ "</div></td>"
							+ "<td><div class='center-block short_narrow_nowrap'>"
							+ contactsPhone
							+ "</div></td>"
							+ "<td><div class='center-block short_three_nowrap'>"
							+ pepairCharge
							+ "</div></td>"
							+ "<td><div class='center-block short_three_nowrap'>"
							+ repairState
							+ "</div></td>"
							+ "<td><div class='center-block short_three_nowrap'>"
							+ chargeState
							+ "</div></td>"
							+ "<td><div class='center-block short_nowrap'>"
							+ "<a onclick='toViweprorertyRepair("
							+ id
							+ ");' href='javascript:void(0);' title='查看/更改详细信息' class='glyphicon glyphicon-edit' aria-hidden='true'>&nbsp;</a>";
					if ((pepairCharge == "") && (null != data.data[i].chargeState) && (data.data[i].chargeState != 1) && ($.trim(data.data[i].chargeState) != "1")){
						str += "<a onclick='toChargeGatewayError("
								+ id
								+ ","
								+ data.page
								+ ");' href='javascript:void(0);' title='支付完成' class='glyphicon glyphicon-yen' aria-hidden='true'>&nbsp;</a>";
					}
					if ((pepairCharge != "") && (null != data.data[i].chargeState) && (data.data[i].chargeState != 1) && ($.trim(data.data[i].chargeState) != "1")){
						str += "<a onclick='toChargeGateway("
								+ id
								+ ","
								+ data.page
								+","
								+pepairCharge
								+ ");' href='javascript:void(0);' title='支付完成' class='glyphicon glyphicon-yen' aria-hidden='true'>&nbsp;</a>";
					}
					str += "</div></td></tr>";
				}
				$("#updateTb_gateway").html(str);
				var fenye = "doTurnPage_prorertyRepair";
				var str_fenye = fenye_admin(data.page, data.total,fenye);				
				$("#commonpage_gateway").empty();
				$("#commonpage_gateway").html(str_fenye);
				$("#commonpage_gateway #toPage").val(currentPage);
			}
		}
	});
}

//确定维修支付
var toChargeGateway = function(id,currentPage,pepairCharge){
	$("#dpr_toChargeGateway").attr("logId",id);
	$("#gatewayChar").html(pepairCharge);
	$("#dpr_toChargeGateway").attr("currentPage",currentPage);
	$("#dpr_toChargeGateway").modal({
		relatedTarget : this,
		width : 434,
		onConfirm : function() {
			var id = $("#dpr_toChargeGateway").attr("logId");
			var currentPage = $("#dpr_toChargeGateway").attr("currentPage");
			$.ajax({
				url : path+"/addOrUpdateGateway",
				type : "post",
				dataType : "json",
				data : {
					"id":id,
					"chargeState":1,
				},
				success : function(data) {
					if (data.result == "success") {
						$("#infoModal_property_succcess_msg").html("成功付款");
						$('#infoModal_property_succcess').modal({
							relatedTarget : this,
							width : 141,
							height : 72
						});
						setTimeout(function() {
							$('#infoModal_property_succcess').modal({
								relatedTarget : this,
							});
							doTurnPage_prorertyRepair(currentPage);
							$("#dpr_toChargeGateway").removeAttr("logId");
							$("#dpr_toChargeGateway").removeAttr("currentPage");
							$("#gatewayChar").html("**");
						}, 1000);
					} else {
						$("#infoModal_property_error_msg").html("付款失败-"+data.error);
						$('#infoModal_property_error').modal({
							relatedTarget : this,
							width : 141,
							height : 72
						});
						setTimeout(function() {
							$('#infoModal_property_error').modal({
								relatedTarget : this,
							});
							$("#dpr_toChargeGateway").removeAttr("logId");
							$("#dpr_toChargeGateway").removeAttr("currentPage");
							$("#gatewayChar").html("**");
						}, 1000);
					}
				}
			});
		},
		onCancel : function() {
		}
	});
}

var toChargeGatewayError = function(id,currentPage){
	alert("请联系维修人员录入维修费用");
}

//查看财产维修记录
var toViweprorertyRepair = function(id){
	$.ajax({
		url : path+"/getReactGateway",
		type : "post",
		dataType : "json",
		data : {
			"id":id,
		},
		success : function(data) {
			if (data.result == "success") {
				var gatewayLog = data.operationResult[0];
				$("#aou_addGateway_btn").attr("gatewayLogId",gatewayLog.id);
				$("#aou_propertyType_name").val(gatewayLog.name);
				$("#aou_propertyType_description").val(gatewayLog.description);
				
				if ($("#viewGatewayInput").hasClass("spec-none")) {
					$("#viewGatewayInput").removeClass("spec-none");
					$("#addGatewaySelect").addClass("spec-none");
				}
				$("#aou_prorertyRepair_contactName").val(gatewayLog.contactsName);
				$("#aou_prorertyRepair_contactPhone").val(gatewayLog.contactsPhone);
				$("#aou_prorertyRepair_description").val(gatewayLog.repaipDescription);
				$("#view_prorertyRepair_innerNumber").val(gatewayLog.repaipDescription);
				
				//获取财产
				$.ajax({
					url : path+"/getReactProperty",
					type : "post",
					dataType : "json",
					data : {
						"id":gatewayLog.propertyId,
					},
					success : function(data) {
						if (data.result == "success") {
							var property = data.operationResult[0];
							if((null == data.operationResult) ||(null == property) ){
								$("#infoModal_stuIndexcenter_error_msg").html("查看财产维修记录失败-"+"财产不存在");
								$('#infoModal_stuIndexcenter_error').modal({
									relatedTarget : this,
									width : 141,
									height : 72
								});
								setTimeout(function() {
									$('#infoModal_stuIndexcenter_error').modal({
										relatedTarget : this,
									});
								}, 1000);
							} else {
								$("#view_prorertyRepair_innerNumber").val(property.propertyInnerNumber+"号:"+property.customName);
								//获取财产所在宿舍
								$.ajax({
									url : path+"/getReactDormitory",
									type : "post",
									dataType : "json",
									data : {
										"id":property.dormitoryId,
									},
									success : function(data) {
										if (data.result == "success") {
											var dormitory = data.operationResult[0];
											if((null == data.operationResult) ||(null == dormitory) ){
												$("#infoModal_stuIndexcenter_error_msg").html("查看财产维修记录失败-"+"财产所在宿舍不存在");
												$('#infoModal_stuIndexcenter_error').modal({
													relatedTarget : this,
													width : 141,
													height : 72
												});
												setTimeout(function() {
													$('#infoModal_stuIndexcenter_error').modal({
														relatedTarget : this,
													});
												}, 1000);
											} else {
												$("#view_prorertyRepair_dorBuilding").val(dormitory.building);
												$("#view_prorertyRepair_dorRoom").val(dormitory.room);
												
												if ($("#addOrUpdateGateway").hasClass("spec-none")) {
													$("#addOrUpdateGateway").removeClass("spec-none").siblings().addClass("spec-none");
												}
											}
										} else {
											$("#infoModal_stuIndexcenter_error_msg").html("获取财产所在宿舍失败-"+data.error);
											$('#infoModal_stuIndexcenter_error').modal({
												relatedTarget : this,
												width : 141,
												height : 72
											});
											setTimeout(function() {
												$('#infoModal_stuIndexcenter_error').modal({
													relatedTarget : this,
												});
											}, 1000);
										}
									}
								});
							}
						} else {
							$("#infoModal_stuIndexcenter_error_msg").html("查看财产信息失败-"+data.error);
							$('#infoModal_stuIndexcenter_error').modal({
								relatedTarget : this,
								width : 141,
								height : 72
							});
							setTimeout(function() {
								$('#infoModal_stuIndexcenter_error').modal({
									relatedTarget : this,
								});
							}, 1000);
						}
					}
				});
			} else {
				$("#infoModal_stuIndexcenter_error_msg").html("查看财产维修记录失败-"+data.error);
				$('#infoModal_stuIndexcenter_error').modal({
					relatedTarget : this,
					width : 141,
					height : 72
				});
				setTimeout(function() {
					$('#infoModal_stuIndexcenter_error').modal({
						relatedTarget : this,
					});
				}, 1000);
			}
		}
	});
}

var student_autoResize = function(){
	var hw=$(window).height();
	var ww=$(window).width();
	//  头部	尺寸调整
	console.log(hw-	60);
	$("#centerpage").css("min-height",(hw-80));
}
