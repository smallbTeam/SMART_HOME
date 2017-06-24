var wxuId = wxId;

$(document).ready(function () {
    $('#loginBtn').click(function () {
        $.ajax({
            url: ${path} + "/client/home?action=accountLogin",
            type: "post",
            dataType: "json",
            data: {
                "mobelPhone": $.trim($("#mobelPhone").val()),
                "password": $.trim($("#password").val()),
                "wxId":wxuId,
            },
            success: function (data) {
                if (data.result == "success" && data.operationResult == 1){
                    $.ajax({
                        url: ${path} + "/client/home?action=index",
                        type: "post",
                        dataType: "json",
                        data: {
                            "wxId": $.trim($("#aou_dormitory_building").val()),
                            "mobelPhone": $.trim($("#dormitory_minRoom").val())
                        },
                        success: function (data) {

                        }
                    });
                } else {
                    layer.alert("用户名或密码错误");
                }

            }
        });
    });
});
