function initCity(province_id,city_id,district_id){
    initEditCity(province_id,city_id,district_id,'','','','');
}

function initEditCity(province_id,city_id,district_id,province_value,city_value,district_value){
        /*初始化，加载省市区*/
        var province;
        var city;
        var district;
        $.ajax({
            type: 'GET',
            url: ctx + '/assets/plugin/cityselect/data.json',
            dataType: 'json',
            success: function(data){
                var htmlProvince = '';
                province = data.province;
                city = data.city;
                district = data.district;
                htmlProvince = selectProvince(province,province_value);
                $('#'+province_id).append(htmlProvince);
                if(province_value != '' && city_value != ''){
                    var htmlCity = selectCity(city,province_value,city_value);
                    $('#'+city_id).html(htmlCity);
                }
                if(city_value != '' && district_value != ''){
                    var htmlDistrict = selectDistrict(district,city_value,district_value);
                    $('#'+district_id).html(htmlDistrict);
                }
            },
            error: function(){
                console.log('请求失败');
            }
        });
        /*选择省*/
        $('#'+province_id).change(function(){
            var code = $(this).val();
            if(code!=''){
                var cityCode  = city[code][0].id;
                var htmlCity = '',
                    htmlDistrict = '';
                htmlCity = selectCity(city,code,'');
                $('#'+city_id).html(htmlCity);
                htmlDistrict = selectDistrict(district,cityCode,'');
                $('#'+district_id).html(htmlDistrict);
            }else{
                $('#'+city_id,'#'+district_id).html('');
            }
        });

        /*选择市*/
        $('#'+city_id).change(function(){
            var cityCode = $(this).val();
            var htmlDistrict = '';
            htmlDistrict = selectDistrict(district,cityCode,'');
            $('#'+district_id).html(htmlDistrict);
        });
}

function selectProvince(province,province_value){
    var htmlProvince = '';
    for(var i=0; i<province.length;i++){
        if(province_value != '' && province_value == province[i].id){
            htmlProvince += '<option value="'+province[i].id+'" selected="selected">'+province[i].name+'</option>';
        } else {
            htmlProvince += '<option value="'+province[i].id+'">'+province[i].name+'</option>';
        }                    
    }
    return htmlProvince;
}

function selectCity(city,code,city_value){
    var htmlCity = '';
    for(var i=0; i<city[code].length;i++){
        if(city_value != '' && city_value == city[code][i].id){
            htmlCity += '<option value="'+city[code][i].id+'" selected="selected">'+city[code][i].name+'</option>';
        } else {
            htmlCity += '<option value="'+city[code][i].id+'">'+city[code][i].name+'</option>';
        }         
    }
    return htmlCity;
}

function selectDistrict(district,cityCode,district_value){
    var htmlDistrict = '';
    for(var i=0; i<district[cityCode].length;i++){
        if (district_value != '' && district_value == district[cityCode][i].id) {
          htmlDistrict += '<option value="'+district[cityCode][i].id+'" selected="selected">'+district[cityCode][i].name+'</option>';
        } else {
          htmlDistrict += '<option value="'+district[cityCode][i].id+'">'+district[cityCode][i].name+'</option>';
        }
    }    
    return htmlDistrict;
}











