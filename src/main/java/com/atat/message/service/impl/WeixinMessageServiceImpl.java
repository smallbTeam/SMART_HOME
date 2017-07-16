/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.message.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atat.common.util.JsonUtil;
import com.atat.common.util.StringUtil;
import com.atat.common.util.weixinClient.CommonUtil;
import com.atat.message.service.WeixinMessageService;
import com.atat.property.dao.PropertyMapDao;
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

    @Override public Integer sendWeixinMessage(List<String> touser, String url, String template_id, JSONObject data) {

        System.out.print("\n\n\n\n\\n\n\n\n\n\n\naaaaaaaaaaaaaaaaaaaaaaaaaaa");

        Map<String, Object> param_sepm = new HashMap<String, Object>();
        param_sepm.put("propertyMapId","accessToken");
        List<Map<String,Object>> propMapList = propertyMapDao.selectPropertyMapList(param_sepm);
        String accesstoken = (String) propMapList.get(0).get("propval");
        String urls = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accesstoken;
            for(int i=0;i<touser.size();i++){
                //封装数据
                JSONObject json = new JSONObject();
                json.put("touser", touser.get(i));//接收者wxName
                json.put("template_id", template_id);//消息模板
                if (StringUtil.isNotEmpty(url)) {
                    json.put("url", url);//填写url可查看详情
                }
                json.put("data", data);

                JSONObject resJsonObj = CommonUtil.httpsRequest(urls, "POST", json.toString());
                Map<String, Object> resMap = JsonUtil.fromJson(resJsonObj.toString(), Map.class);
                String errcode = resMap.get("errcode")+"";
                String errmsg = (String) resMap.get("errmsg");
                System.out.print("\n\nerrcode:"+errcode+"\n\n\n\nerrmsg:"+errmsg+"\n\n\n\n");
                if(errcode.equals("0")){
                    return 1;
                }else{
                    return 0;
                }
            }
            return 0;
    }

}
