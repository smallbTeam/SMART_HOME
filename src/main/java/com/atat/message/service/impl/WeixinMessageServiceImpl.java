/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.message.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atat.common.prop.BasePropertyDate;
import com.atat.common.util.BaseLogger;
import com.atat.common.util.JsonUtil;
import com.atat.common.util.StringUtil;
import com.atat.common.util.httpClient.HttpClientUtil;
import com.atat.common.util.httpClient.URLUtil;
import com.atat.common.util.weixinClient.CommonUtil;
import com.atat.message.service.WeixinMessageService;
import com.atat.property.dao.PropertyMapDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class WeixinMessageServiceImpl extends BaseLogger implements WeixinMessageService{

    @Resource
    private PropertyMapDao propertyMapDao;

    @Override
    public Integer sendWeixinMessage(List<String> touser, String url, String template_id, JSONObject data) {
        Map<String, Object> param_sepm = new HashMap<String, Object>();
        param_sepm.put("propertyMapId", "accessToken");
        List<Map<String, Object>> propMapList = propertyMapDao.selectPropertyMapList(param_sepm);
        String accesstoken = (String) propMapList.get(0).get("propval");
        String urls = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accesstoken;
        Integer sentRes = 0;
        for (int i = 0;i < touser.size();i++) {
            // 封装数据
            JSONObject json = new JSONObject();
            json.put("touser", touser.get(i));// 接收者wxName
            json.put("template_id", template_id);// 消息模板
            if (StringUtil.isNotEmpty(url)) {
                json.put("url", url);// 填写url可查看详情
            }
            json.put("data", data);
            JSONObject resJsonObj = CommonUtil.httpsRequest(urls, "POST", json.toString());
            Map<String, Object> resMap = JsonUtil.fromJson(resJsonObj.toString(), Map.class);
            String errcode = resMap.get("errcode") + "";
            String errmsg = (String) resMap.get("errmsg");
            if (!errcode.equals("0")) {
                sentRes = sentRes++;
                logger.info("\n\nerrcode:" + errcode + "\n\n\n\nerrmsg:" + errmsg + "\n\n\n\n");
            }
        }
        return sentRes;
    }

    @Override public String getUserwxId(String code) {
        String appid = BasePropertyDate.WX_APPID;
        String secret = BasePropertyDate.WX_SECRET;
        if (StringUtil.isEmpty(code) || StringUtil.isEmpty(appid)
               || StringUtil.isEmpty(secret)) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("appid", appid);
        paramMap.put("secret", secret);
        paramMap.put("code", code);
        paramMap.put("grant_type", "authorization_code");
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
        String resJson = "";
        try {
            resJson = HttpClientUtil.doPost(url, paramMap, "utf-8");
            logger.info("微信平台get请求：" + URLUtil.getDataUrl(url, paramMap));
            logger.info("微信平台请求用户OpenID：[" + resJson + "]");
            // 解析Json 获取AppId
            Map<String, Object> resMap = JsonUtil.fromJson(resJson, Map.class);
            String wxId = (String) resMap.get("openid");
            if (StringUtil.isNotEmpty(wxId)) {
                return wxId;
            }
            else {
                return null;
            }
        } catch (Exception e) {
            logger.info("[微信平台请求用户OpenID][Http请求异常" + e.getMessage() + "]");
            return null;
        }
    }
}
