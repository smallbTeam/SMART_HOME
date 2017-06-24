$(document).ready(function () {

    var userId="";
    var url = ${path} + "/client/home?action=getGatewayListByCustomerId&CustomerId="+userId;
    ajaxRequest(url,"GET",function (flag,msg) {
        alert("msg---"+msg);
        if (flag == true && msg["result"].equals("success")) {
            //请求成功
            var operationResult=msg["operationResult"];
            [].forEach(function (value,index,operationResult) {
                var divcontent='<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3  " >'+
                    '<div id="gateway_list-item_'+value["id"]+'" class="list-item">'+
                    ' <div id="close_'+value["id"]+'" class="close"><img src="${path}/page/common/img/close.png"></div>'+
                    ' <div class="list-item-content">'+
                    '  <div class="list-item-top ">'+
                    '   <h3> <span class="glyphicon glyphicon-object-align-vertical szblue"></span> <span>'+value["Address"]+'</span></h3>'+
                    ' </div>'+
                    ' <div class="list-item-bottom">'+
                    '  <ul class=" ">'+
                    '  <li><span class="glyphicon glyphicon-send"></span><a  href="#">在线：05</a> </li>'+
                    ' <li><span class="glyphicon glyphicon-adjust"></span><a  href="#">状态：未开启</a> </li>'+
                    '<li><span class="glyphicon glyphicon-alert"></span><a  href="#">设备：10</a> </li>'+
                    '</ul>'+
                    '</div>'+
                    '</div>'+
                    '</div>'+
                    '</div>' ;
                $("#gateway-list .row").appendChild(divcontent);

                $("#gateway-list #gateway_list-item_"+value["id"]+" .list-item-content").bind("click",function () {
                    var ids=$(this).parentNode.attr("id");
                    var id=ids.split("_").last();
                    var url = ${path} + "/client/home?action=getDeviceListByGatewayId&diviceGatewayId="+id;
                    window.open(url);
                });

                $("#gateway-list #close_"+value["id"]).bind("click",function () {
                    var ids=$(this).parentNode.attr("id");
                    var id=ids.split("_").last();
                    var url=${path} + "/client/home?action=delGateway&GatewayId="+id;
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

            });

            var lastItem='<div class="col-xs-12 col-sm-6  col-md-4 col-lg-3  ">'+
                '<div class="list-item-last">'+
                '<button id="add_gateway" ><img src="${path}/page/common/img/add.png"></button>'+
                '</div>'+
                '</div>';
            $("#gateway-list .row").appendChild(lastItem);


        }else{
            //请求失败
            alert("请求失败");
        }
    });




    var dialog = '<div class="box">'+
        '<form role="form" action="addGateway" method="get">'+
        '<div class="form-group">'+
        '<label for="name">网关名称</label>'+
        '<input type="text" class="form-control" id="name" name="Address" placeholder="请输入网关名称">'+

        '<label for="name">网关IP</label>'+
        '<input type="text" class="form-control" id="name" name="IP" placeholder="请输入网关IP">'+

        '<label for="name">端口</label>'+
        '<input type="text" class="form-control" id="name" name="GatewayPort" placeholder="请输入端口信息">'+
        '</div>'+
        '<div class="form-group">'+
        '</div>'+
        '<button type="submit" class="btn btn-default">提交</button>'+
        '</form>'+
        '</div>';

    layer.config({
        extend:'szskin/style.css',
        skin: 'dialog'
    });


    $("#gateway-list .list-item-last #add_gateway").click(function () {

        layer.open({
            type: 1,
            title: false,
            closeBtn: 1,
            shadeClose: true,
            skin: 'dialog',
            content: dialog
        });
    });

    $("#gateway-list .close").click(function () {
        var id = $(this).parent("div").attr('id');
        $("#"+id).remove();


    });

    $("#gateway-list .list-item").hover(function () {
        var id = $(this).attr('id');
        $("#gateway-list #"+id+" .close").css("opacity","0.5");
    });

    $("#gateway-list .list-item").mouseleave(function () {
        var id = $(this).attr('id');
        $("#gateway-list #"+id+" .close").css("opacity","0.0");
    });

    $("#addGetway").on("click",function(){
        window.location.href = ${path} + "/client/home?action=addGetway";
    })
});
