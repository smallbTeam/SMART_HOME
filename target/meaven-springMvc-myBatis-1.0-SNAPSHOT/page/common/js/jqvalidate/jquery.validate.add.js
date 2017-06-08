jQuery.validator.addMethod("agerange", function(value, element,params) {
	return this.optional(element) || (Age.getCompareAbleValue(value,params[0]) >= parseFloat(params[0]) && Age.getCompareAbleValue(value,params[1]) <= parseFloat(params[1]));
}, "必须是在{0}和{1}之间");

jQuery.validator.addMethod("numintc", function(value, element,params) {
	return this.optional(element) || ((value % params[0]) == 0);
}, "{0}的整数倍");

//身份证号
jQuery.validator.addMethod("idno", function(value, element) {
	return this.optional(element) || IdUtil.idNoLegitimacy(value);
}, "请正确输入身份证号码");

//身份证校验同时校验证件类型
jQuery.validator.addMethod("idnoandtype", function(value, element,params) {
	if($("#"+params[0]).val() == 0 || $("#"+params[0]).val() == '0' ){
		//证件类型为身份证时
		return this.optional(element) || IdUtil.idNoLegitimacy(value);
	}else if($("#"+params[0]).val() == 3 || $("#"+params[0]).val() == '3' ){
		//证件类型为护照时
		var reg = /^[A-Za-z0-9]{3,20}$/;
		return this.optional(element) || reg.test(value);
	}else if($("#"+params[0]).val() == 2 || $("#"+params[0]).val() == '2' ){
		//证件类型为出生证时
		var reg = /^[a-zA-Z]{1}[0-9]{7,11}$/;
		return this.optional(element) ||  reg.test(value);
	}else if($("#"+params[0]).val() == 8 || $("#"+params[0]).val() == '8' ){
		//证件类型为港澳居民来往内地通行证 - 字母开头且证件号码位数必须为11个字符
		var reg = /^[a-zA-Z][0-9]{10}$/;
		return this.optional(element) ||  reg.test(value);
	}else if($("#"+params[0]).val() == 7 || $("#"+params[0]).val() == '7'){
		//证件类型为台湾居民来往大陆通行证 - 8-10个字符
		var reg = /^[a-zA-Z0-9]{8,10}$/;
		return this.optional(element) ||  reg.test(value);
	}
	return true;
}, "请正确输入证件号码");

//身份证校验同时校验出生日期，性别
jQuery.validator.addMethod("idNoBirthdaySex", function(value, element,params) {
	if($("#"+params[0]).val() == 0 || $("#"+params[0]).val() == '0' ){
		return this.optional(element) || IdUtil.idNoBirthdaySexCheck($("#"+params[1]).val(),$("#"+params[2]).val(),$("#"+params[3]).val());
	}
	return true;
}, "您输入的身份证号与填写的出生日期、性别不相符");

//校验身份证号同时校验出生日期
jQuery.validator.addMethod("checkIdNoBirthday", function(value, element,params) {
	if($("#"+params[0]).val() == 0 || $("#"+params[0]).val() == '0' ){
		return this.optional(element) || IdUtil.checkIdNoBirthday($("#"+params[1]).val(),$("#"+params[2]).val());
	}
	return true;
}, "您输入的身份证号与填写的出生日期不相符");

//校验身份证号同时校验性别
jQuery.validator.addMethod("checkIdNoSex", function(value, element,params) {
	if($("#"+params[0]).val() == 0 || $("#"+params[0]).val() == '0' ){
		return this.optional(element) || IdUtil.checkIdNoSex($("#"+params[1]).val(),$("#"+params[2]).val());
	}
	return true;
}, "您输入的身份证号与填写的性别不相符");


//护照
jQuery.validator.addMethod("passport", function(value, element) {
	var passportRex = /^(P\d{7})|(G\d{8})$/;
	return this.optional(element) || passportRex.test(value);
}, "请正确输入护照");

//出生证编码
jQuery.validator.addMethod("birthCertificate", function(value, element) {
	var birthCertificateRex = /^(P\d{7})|(G\d{8})$/;
	return this.optional(element) || birthCertificateRex.test(value);
}, "请正确输入出生证编码");

//手机号码
jQuery.validator.addMethod("mobileno", function(value, element) {
	var number = /^1[3|4|5|7|8][0-9]\d{8}$/;
	return this.optional(element) || (number.test(value));
}, "手机号格式不正确");

//电子邮箱
jQuery.validator.addMethod("email", function(value, element) {
	var ema = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i;
	return this.optional(element) || (ema.test(value));
}, "电子邮箱格式不正确");

//日期格式
jQuery.validator.addMethod("idate", function(value, element) {
	var idate = /^(\d{4})-(\d{2})-(\d{2})$/;
	return this.optional(element) || (idate.test(value));
}, "日期格式不正确");

//投、被保险人出生日期、性别是否相等
jQuery.validator.addMethod("birthdaysex", function(value,element,params) {
	if(value == '00'){
		if($("input[name="+params[0]+"]").val() != $("input[name="+params[1]+"]").val() || $("input[name="+params[2]+"]:checked").val() != $("input[name="+params[3]+"]:checked").val()){
			alert("投被保险人出生日期不符");
			return false;
		}
	}
	return true;
}, "投保人出生日期与身份证号不符");

//证件有效期的校验
jQuery.validator.addMethod("idexpireddate", function(value, element) {
	var idExpiredDate = value;
	var isLongValidate =$("#"+element.id).parent().next().find('input').val();
	if((idExpiredDate == null || idExpiredDate == '') && isLongValidate == null){
		return false;
	}
	if((idExpiredDate == null || idExpiredDate == '') && isLongValidate == 0){
		return false;
	}
	return true;
}, "证件有效期有误");

//简易银行卡号校验
jQuery.validator.addMethod("checkCardNo", function(value, element) {
	var iCardNo = /^\d{16,19}$/;
	return this.optional(element) || (iCardNo.test(value));
}, "银行卡号输入不正确");

//检验字符长度是否等于阈值
jQuery.validator.addMethod("lengthequal", function(value, element,param) {
	var length = value.length;       
	for(var i = 0; i < value.length; i++){       
		if(value.charCodeAt(i) > 127){       
			length++;
		}
	}    
	return this.optional(element) || (length == param);
}, "应为{0}个字符");

//金钱格式校验
jQuery.validator.addMethod("moneyCheck", function(value, element) {
	var moneyCheck = /^\d*(.\d{1,2})?$/;
		return this.optional(element) || (moneyCheck.test(value));
	}, "格式错误");

//固定电话校验(一个框)
jQuery.validator.addMethod("telephone", function(value, element) {
	var tel = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
	return this.optional(element) || (tel.test(value));
}, "请输入正确的电话号码");

//固定电话校验(两个框)
jQuery.validator.addMethod("telePhone", function(value, element,params) {
	if($("#"+params[0]).val() != ''  || $("#"+params[1]).val() != ''){
		var area = /^(\d{3}|\d{4})$/;
		var phone = /^(\d{7}|\d{8})$/;
		var phoneten = /^(400|800)\d{7}$/;
		if($("#"+params[1]).val().length == 10){
			return  (area.test($("#"+params[0]).val())) && (phoneten.test($("#"+params[1]).val()));
		}
		return  (area.test($("#"+params[0]).val())) && (phone.test($("#"+params[1]).val()));
//	if($("#"+params[0]).val() != '' && $("#"+params[1]).val() != ''){
//		return true;
//	}else if($("#"+params[0]).val() == '' && $("#"+params[1]).val() == ''){
//		return true;
	}else{
		return true;
	}
}, "固定电话填写有误");

//固定电话-区号校验
jQuery.validator.addMethod("areacode", function(value, element,param) {
	if(value != ''){
		$('#' + param).validate().showErrors();
		var area = /^(\d{3}|\d{4})$/;
		return  (area.test(value));
	}else{
		return true;
	}
}, "区号填写有误");

//固定电话-电话号码校验
jQuery.validator.addMethod("phoneNum", function(value, element,param) {
	if(value != ''){
		var phone = /^(\d{7}|\d{8})$/;
		var phoneten = /^(400|800)\d{7}$/;
		if(value.length == 10){
			return  (phoneten.test(value));
		}
		return (phone.test(value));
	}else{
		return true;
	}
}, "电话号码填写有误");

//校验体重
jQuery.validator.addMethod("avoirdupoisCheck", function() {
	var avoirdupois = $("#avoirdupois").val();
	var age = Age.getAge($("#appBirthday").attr('name'), new Date());
//	console.log(age);
	if($.trim(avoirdupois)!=""){
		if(parseInt(age)>=2 && parseInt(age)<=6){
			var param = (parseInt(age)*2+8).toFixed(3);
			return ((parseFloat(param)-5)<=parseFloat(avoirdupois).toFixed(3) && (parseFloat(param)+5)>=parseFloat(avoirdupois).toFixed(3));
		}
		else if(parseInt(age)>=7 && parseInt(age)<=12){
			var param = ((parseInt(age)*7-5) / 2).toFixed(3);
			return ((parseFloat(param)-10)<=parseFloat(avoirdupois).toFixed(3) && (parseFloat(param)+10)>=parseFloat(avoirdupois).toFixed(3));
		}
		return true;
	}else{
		return true;
	}
}, "体重不符合投保规则");

//身高校验
jQuery.validator.addMethod("statureCheck", function() {
	var stature = $("#stature").val();
	var age = Age.getAge($("#appBirthday").attr('name'), new Date());
	if($.trim(stature)!=""){
		if(parseInt(age)>=2 && parseInt(age)<=12){
			var param = (parseInt(age)*6+77).toFixed(3);
			return ((parseFloat(param)-15)<=parseFloat(stature).toFixed(3) && (parseFloat(param)+15)>=parseFloat(stature).toFixed(3));
		}
		return true;
	}else{
		return true;
	}
	console.log(age);
}, "身高不符合投保规则");

//身高至少长于2位
jQuery.validator.addMethod("statureSizeCheck", function(value, element) {
	var reg = /^\d{2,3}$/;
	return this.optional(element) || (reg.test(value));
},"请正确填写身高");

//体重至少长于2位
jQuery.validator.addMethod("avoirdupoisSizeCheck", function(value, element) {
	var reg=/^\d{1,3}(?:\.\d{1,2})?$/;
	return this.optional(element) || (reg.test(value));
},"请正确填写体重");

//身高体重比例校验
jQuery.validator.addMethod("stToAvoCheck", function() {
	var stature = $("#stature").val();
	var avoirdupois = $("#avoirdupois").val();
	if($.trim(stature)!="" && $.trim(avoirdupois)!=""){
		var BMI = (parseFloat(avoirdupois).toFixed(3) / ((parseFloat(stature).toFixed(3)*parseFloat(stature).toFixed(3))/10000).toFixed(3)).toFixed(3);
		return (BMI>=17 && BMI<=30);
	}else{
		return true;
	}
}, "身高-体重的比例不符合投保规则");

//不能为纯数字
jQuery.validator.addMethod("notAllNum", function(value, element) {
	var notOnlyNum = /^\d+(\.\d+)?$/;
	return this.optional(element) || (!notOnlyNum.test(value));
}, "输入信息不能为纯数字");

//不能为纯英文字母
jQuery.validator.addMethod("notAllLetter", function(value, element) {
	var notOnlyLetter = /^[A-Za-z]+$/;
	return this.optional(element) || (!notOnlyLetter.test(value));
}, "输入信息不能为纯英文字母");

//字之间可包含“空格”和“·”，第一个字之前和最后一个字之后不能有任何空格和字符
jQuery.validator.addMethod("regname", function(value, element) {
//	var notOnlyNum = /^(.)([a-zA-Z\d\u4E00-\u9FA5]+)(.?)$/;
	var notOnlyNum = /^([a-zA-Z\d\u4E00-\u9FA5]+)([.·•\sa-zA-Z\d\u4E00-\u9FA5]*)([a-zA-Z\d\u4E00-\u9FA5]+)$/;
	return this.optional(element) || (notOnlyNum.test(value));
}, "输入信息包含特殊字符");

//名字中不能包含数字
jQuery.validator.addMethod("noNum", function(value, element) {
	var noNum = /^([a-zA-Z\u4E00-\u9FA5]+)([.·•\sa-zA-Z\u4E00-\u9FA5]*)([a-zA-Z\u4E00-\u9FA5]+)$/;
	return this.optional(element) || (noNum.test(value));
}, "不能包含数字");

//校验受益人与被保险人关系
jQuery.validator.addMethod("bnfRela", function(value, element,params) {
	//参数说明：1、被保险人性别id

	//受益人性别为男时不能选择的关系：母亲
	var man_no = "04";
	//受益人性别为女时不能选择的关系：父亲
	var woman_no = "03";
	
	//不能重复的关系
	var no_replace ='01,03,04';//配偶、父亲、母亲、
	
	//投保人性别
	var appsex =  $("#appSex").val();
	
	//被保险人性别
	var inssex = "";
	if(appsex != null && appsex != ''){
		if($('#relationToAppnt').val() != '00'){
			inssex =  $("#insSex").val();
		}else{
			inssex =  appsex;
		}
	}
	//受益人性别
	var bnfsex = $("#bnfSex"+element.id.substr(element.id.length-1)).val();
	//var bnfsex = $("#bnfSex"+element.id.substr(-1)).val();
	//判断受益人性别与关系是否一致
	if(bnfsex != null && bnfsex != ''){
		if(value == "01"){
			//如果关系类型为配偶，判断是否与被保险人性别不一致
			if(bnfsex == inssex){
				return false;
			}
		}else if(0 == bnfsex || '0' == bnfsex){
			//当性别为男时，关系是否为一致
			if(man_no.indexOf(value) >=0){
				return false;
			}
		}else if(1 == bnfsex || '1' == bnfsex){
			//当性别为女时，关系是否一致
			if(woman_no.indexOf(value) >=0){
				return false;
			}
		}else{
			return true;
		}
	}
		if(no_replace.indexOf(value) >=0){
			var num = 0;
			var relas = $('#zdsyrFormBody').find($("select[id^='bnfRelationToInsured']"));
			relas.each(function() {
				if( $(this).val() == value ){
					num++;
				}
			});
			if(num > 1){
				return false;
			}
		}
	return true;

}, "受益人性别与您受益人与被保险人关系存在差异");

//校验投保人与被保险人关系
jQuery.validator.addMethod("insRela", function(value, element,params) {
	//参数说明：1、被保险人性别id
	//被保险人性别为男时不能选择的关系：母亲
	var man_no = "04";
	//被保险人性别为女时不能选择的关系：父亲
	var woman_no = "03";
	//投保人性别
	var appsex =  $.trim($("#appSex").val());
	
	//被保险人性别
	var inssex = "";
	if(appsex != null && appsex != ''){
		if($('#relationToAppnt').val() != '00'){
			inssex =  $.trim($("#insSex").val());
		}else{
			return true;
		}
	}
	
	//判断受益人性别与关系是否一致
	if(inssex != ''){
		if(value == "01"){
			//如果关系类型为配偶，判断是否与投保人性别不一致
			if(appsex == inssex){
				$("#insSexm,#insSexw,#insIdno").attr('disabled',false);
				return false;
			}
		}else if(0 == inssex || '0' == inssex){
			//当性别为男时，关系是否为一致
			if(man_no.indexOf(value) >=0){
				$("#insSexm,#insSexw,#insIdno").attr('disabled',false);
				return false;
			}
		}else if(1 == inssex || '1' == inssex){
			//当性别为女时，关系是否一致
			if(woman_no.indexOf(value) >=0){
				$("#insSexm,#insSexw,#insIdno").attr('disabled',false);
				return false;
			}
		}else{
			return true;
		}
	}
	return true;

}, "被保险人性别与您投保人与被保险人关系存在差异");
//相同字符不能连续出现n次
jQuery.validator.addMethod("notContinueChar", function(value, element, params){
	if(params[0]==null || params[0]=="" || params[0]<1 ){
		var notContinueChar=/(\S+)\1{4}/ig;
		return this.optional(element) || (!notContinueChar.test(value));
	}else{
		var numb= params[0]-1;
		var notContinueChar= new RegExp("(\\S+)\\1{"+numb+"}","ig");
		return this.optional(element) || (!notContinueChar.test(value));
	}
},"相同字符不能连续出现");

//比较被保险人出生日期是否一致
jQuery.validator.addMethod("compareBirth", function(value, element, params){
	var old_bir=$("#old_bir").val();
	var new_bir=$("#insBirthday").val();
	if(old_bir != new_bir){
		return false;
	}else{
		return true;
	}
	
},"被保险人出生日期与产品详情页的不符");




//校验投保人与被保险人关系 - wap版
jQuery.validator.addMethod("insRelaWap", function(value, element,params) {
	//参数说明：1、被保险人性别id
	//被保险人性别为男时不能选择的关系：母亲
	var man_no = "04";
	//被保险人性别为女时不能选择的关系：父亲
	var woman_no = "03";
	//投保人性别
	var appsex =  $.trim($("input[name='ebizAppntDTO.sex']:checked").val());
	
	//被保险人性别
	var inssex = "";
	if(appsex != null && appsex != ''){
		if($('#relationToAppnt').val() != '00'){
			inssex =  $.trim($("input[name='orderDTOLst[0].ebizInsuredDTO.sex']:checked").val());
		}else{
			return true;
		}
	}
	
	//判断受益人性别与关系是否一致
	if(inssex != ''){
		if(value == "01"){
			//如果关系类型为配偶，判断是否与投保人性别不一致
			if(appsex == inssex){
				return false;
			}
		}else if(0 == inssex || '0' == inssex){
			//当性别为男时，关系是否为一致
			if(man_no.indexOf(value) >=0){
				return false;
			}
		}else if(1 == inssex || '1' == inssex){
			//当性别为女时，关系是否一致
			if(woman_no.indexOf(value) >=0){
				return false;
			}
		}else{
			return true;
		}
	}
	return true;
}, "被保险人性别与您投保人与被保险人关系存在差异");

//校验受益人与被保险人关系 - wap版
jQuery.validator.addMethod("bnfRelaWap", function(value, element,params) {
	//参数说明：1、被保险人性别id

	//受益人性别为男时不能选择的关系：母亲
	var man_no = "04";
	//受益人性别为女时不能选择的关系：父亲
	var woman_no = "03";
	
	//不能重复的关系
	var no_replace ='01,03,04';//配偶、父亲、母亲、
	
	//投保人性别
	var appsex = $("input[name='ebizAppntDTO.sex']:checked").val();
	
	//被保险人性别
	var inssex = "";
	if(appsex != null && appsex != ''){
		if($('#relationToAppnt').val() != '00'){
			inssex =  $("input[name='orderDTOLst[0].ebizInsuredDTO.sex']:checked").val();
		}else{
			inssex =  appsex;
		}
	}
	//受益人性别  orderDTOLst[0].ebizBnfList[0].sex
	var bnfsex = $("input[name='orderDTOLst[0].ebizBnfList["+element.id.substr(-1)+"].sex']:checked").val();

	//判断受益人性别与关系是否一致
	if(bnfsex != null && bnfsex != ''){
		if(value == "01"){
			//如果关系类型为配偶，判断是否与被保险人性别不一致
			if(bnfsex == inssex){
				return false;
			}
		}else if(0 == bnfsex || '0' == bnfsex){
			//当性别为男时，关系是否为一致
			if(man_no.indexOf(value) >=0){
				return false;
			}
		}else if(1 == bnfsex || '1' == bnfsex){
			//当性别为女时，关系是否一致
			if(woman_no.indexOf(value) >=0){
				return false;
			}
		}else{
			return true;
		}
	}
	if(no_replace.indexOf(value) >=0){
		var num = 0;
		var relas = $('#zdsyrFormBody').find($("select[id^='bnfRelationToInsured']"));
		relas.each(function() {
			if( $(this).val() == value ){
				num++;
			}
		});
		if(num > 1){
			return false;
		}
	}
	return true;

}, "受益人性别与您受益人与被保险人关系存在差异");
//相同字符不能连续出现n次
jQuery.validator.addMethod("notContinueChar", function(value, element, params){
	if(params[0]==null || params[0]=="" || params[0]<1 ){
		var notContinueChar=/(\S+)\1{4}/ig;
		return this.optional(element) || (!notContinueChar.test(value));
	}else{
		var numb= params[0]-1;
		var notContinueChar= new RegExp("(\\S+)\\1{"+numb+"}","ig");
		return this.optional(element) || (!notContinueChar.test(value));
	}
},"相同字符不能连续出现");

//包含汉字
jQuery.validator.addMethod("hasCh", function(value, element) {
	var hasCH = /[\u4E00-\u9FA5]/;
	return this.optional(element) || (hasCH.test(value));
}, "输入信息需要包含汉字");

//工号
jQuery.validator.addMethod("agentcode", function(value, element) {
	var agentcodeRex = /^\d{6}$/;
	return this.optional(element) || agentcodeRex.test(value);
}, "请正确输入工号");

//工号
jQuery.validator.addMethod("mobilecode", function(value, element) {
	var mobilecodeRex = /^\d{6}$/;
	return this.optional(element) || mobilecodeRex.test(value);
}, "校验码格式不正确");