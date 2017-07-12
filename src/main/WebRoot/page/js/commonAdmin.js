$(document).ready(function(){
	
	/*分页切换*/
	$("body").on("click",".am-pagination li",function(){
		$(this).addClass("am-active").trigger("create").siblings().removeClass("am-active");
	});
	
})

/*调整页面尺寸*/
var autoResize = function(){
	var hw=$(window).height();
	var ww=$(window).width();
	//  头部	尺寸调整
	var Hheader=$(".admin-header").height();
	$(".admin-header").width(ww);
	//  左侧按钮组尺寸调整
	$(".admin-panel-btns").height(hw-Hheader);
	var Wleft=$(".admin-panel-btns").width();
	//  中心内容区尺寸调整
	$(".admin-content-container").height(hw-Hheader);
	$(".admin-panel-tabs").height(hw-Hheader);
	$(".admin-content-container").width(ww-Wleft);	
	$(".admin-panel-tabs").width(ww-Wleft);		
	$(".am-tabs").height(hw-Hheader);
	$(".am-tabs-bd").height(hw-Hheader-42);
	//左侧边栏最宽150px
	$("frame[name='leftFrame']").width(function(index, width){
	    return Math.max(width, 150);
	});
}

//分页底部页码
var fenye_admin = function(currentPage, total, fenye) {
	var str = "";
	currentPage = currentPage>total ? total : currentPage;
	var currentPageMin = parseInt((currentPage - 1) / 10);
	var totalMin = parseInt((total - 1) / 10);
	if (currentPage == 1) {
		str += '<li class="am-disabled"><a class="am-pre-page" >首页</a></li>';
		str += '<li class="am-disabled"><a class="am-pre-page" >上一页</a></li>';
	} else {
		str += '<li class=""><a class="am-pre-page"  onclick="'+fenye+'(1)">首页</a></li>';
		str += '<li class=""><a class="am-pre-page"  onclick="'+fenye+'('
				+ (currentPage - 1) + ')">上一页</a></li>';
	}
	if (currentPageMin != 0) {
		str += '<li class="am-disabled"><a class="am-pre-page" >...</a></li>';
	}
	if (currentPageMin == totalMin) {
		for (var i = ((currentPageMin) * 10 + 1); i <= total; i++) {
			if (currentPage == i) {
				str += '<li class="am-disabled"><a class="am-pre-page" >' + i
						+ '</a></li>';
			} else {
				str += '<li class="am-active"><a onclick="'+fenye+'(' + i
						+ ')">' + i + '</a></li>';
			}
		}
	} else {
		for (var i = (currentPageMin * 10 + 1); i <= ((currentPageMin + 1) * 10); i++) {
			if (currentPage == i) {
				str += '<li class="am-disabled"><a class="am-pre-page" >' + i
						+ '</a></li>';
			} else {
				str += '<li class="am-active"><a onclick="'+fenye+'(' + i
						+ ')">' + i + '</a></li>';
			}
		}
		str += '<li class="am-disabled"><a class="am-pre-page" >...</a></li>';
	}
	if (currentPage == total) {
		str += '<li class="am-disabled"><a class="am-next-page" href="">下一页</a></li>';
		str += '<li class="am-disabled"><a class="am-next-page" href="">末页</a></li>';
	} else {
		str += '<li class=""><a class="am-next-page" onclick="'+fenye+'('
				+ (currentPage + 1) + ')">下一页</a></li>';
		str += '<li class=""><a class="am-next-page" onclick="'+fenye+'('
				+ total + ')">末页</a></li>';
	}
	str += '<li class="" style="padding-right:0;"><span style="margin-right:0;font-size:12px;">第</span></li>'
	str += '<li class=""><input id="toPage" type="text"/></li>';
	str += '<li class=""><span style="margin-right:0;font-size:12px;">页</span></li>'
	str += '<li class=""><a class="am-pre-page" id="turnTo">Go</a></li>';
	return str;
}

//日期格式化
//调用格式:Date().format("yyyy-MM-dd hh:mm:ss")
Date.prototype.format = function(fmt) { 
    var o = {
       "M+" : this.getMonth()+1,                 //月份 
       "d+" : this.getDate(),                    //日 
       "h+" : this.getHours(),                   //小时 
       "m+" : this.getMinutes(),                 //分 
       "s+" : this.getSeconds(),                 //秒 
       "q+" : Math.floor((this.getMonth()+3)/3), //季度 
       "S"  : this.getMilliseconds()             //毫秒 
   }; 
   if(/(y+)/.test(fmt)) {
           fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
   }
    for(var k in o) {
       if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
   return fmt; 
}        