/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.message.service.impl;

import com.atat.common.util.JsonUtil;
import com.atat.common.util.StringUtil;
import com.atat.common.util.httpClient.HttpClientUtil;
import com.atat.message.service.WeixinMessageService;
import com.atat.property.dao.PropertyMapDao;
import com.atat.property.service.PropertyMapService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ligw
 * @version $Id weixinMessageService.java, v 0.1 2017-07-15 16:03 ligw Exp $$
 */
@Service
public class WeixinMessageServiceImpl implements WeixinMessageService{

    @Resource
    private PropertyMapDao propertyMapDao;

    @Override public Integer sendWeixinMessage(List<String> touser, String url, String template_id, Map data) {

        Map<String, Object> param_sepm = new HashMap<String, Object>();
        param_sepm.put("propertyMapId","accessToken");
        List<Map<String,Object>> propMapList = propertyMapDao.selectPropertyMapList(param_sepm);
        String accesstoken = (String) propMapList.get(0).get("propertyMapId");
        String urls = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accesstoken;
            for(int i=0;i<touser.size();i++){
                /////发送数据
                String resJson = "";
                Map<String, String> postdata = new HashMap<String, String>();
                postdata.put("touser",touser.get(i));
                postdata.put("template_id",template_id);
                if(StringUtil.isNotEmpty(url)){
                    postdata.put("url",url);
                }
                postdata.put("data",JsonUtil.toJson(data));
                resJson = HttpClientUtil.doPost(urls,postdata,"utf-8");
                Map<String, Object> resMap = JsonUtil.fromJson(resJson, Map.class);
                String errcode = (String) resMap.get("errcode");
                if(errcode.equals("0")){
                    return 1;
                }else{
                    return 0;
                }
            }
            return 0;
    }

}
