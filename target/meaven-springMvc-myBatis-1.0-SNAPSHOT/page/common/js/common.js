/**
 * Created by ligw on 2017/6/6.
 *
 *
 *
 */
//ajax获取表单信息并提交
function ajax_get_form(form_id,method,callback){
    var form =  $("#"+form_id);
    var url = form.attr('action');
    var data = {};
    var textarea = form.find("textarea");
    var select = form.find("select");
    var radio = form.find(":input[type='radio']");
    var hidden = form.find(":input[type='hidden']");
    var checkbox = form.find(":input[type='checkbox']");
    var input = form.find(":input").not(":button").not(":radio");
    var file = form.find(":input[type='file']");
    textarea.each(function(i){
        var name = $(this).attr("name");
        data[name] = $(this).text();
    });
    select.each(function(i){
        var name = $(this).attr("name");
        data[name] = $(this).val();

    });
    radio.each(function(i){
        var name = $(this).attr("name");
        if ($(this).attr("checked") == "checked") {
            data[name] = $(this).val();
        }
    });
    input.each(function(i){
        var name = $(this).attr("name");
        data[name] = $(this).val();
    });
    checkbox.each(function(i){
        var name = $(this).attr("name");
        if ($(this).attr("checked") == "checked") {
            data[name] = 1;
        } else {
            data[name] = -1;
        }
    });
    hidden.each(function(i){
        var name = $(this).attr("name");
        data[name] = $(this).val();
    });
    file.each(function(i){
        var name = $(this).attr("name");
        data[name] = $(this).val();
    });
    // console.log(data);
    // type: "POST",

    $.ajax({
        type: method,
        url: url,
        dataType: "json",
        data: data,
        success: function(msg){
            console.log(msg);
            // var d = eval("("+msg+")");
            // console.log(msg);
            // $('#'+msg.show_id).modal({
            // 	backdrop:true,
            // 	keyboard:false,
            // 	show:true
            // });

            callback(msg);

            // $("#"+msg.show_id).modal('show');
        },
        error: function () {
            callback(null);
        }

    });

}

function ajaxRequest(url,method,callback) {

    //发送请求
    var request;
    if (window.XMLHttpRequest) {
        request = new XMLHttpRequest();
    }else{
        request = new ActiveXObject("Microsoft.XMLHTTP");
    }
    request.open(method,url,true);
    request.send(null);

    //监听服务器处理请求的过程
    request.onreadystatechange =function(){
        if (request.readyState == 4 && request.status == 200) {
            // alert(request.responseText);
            callback(true,request.responseText);
        }else{
            // alert("发生错误："+request.status);
            callback(false,request.responseText);
        }
    }
}
