/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.property.action;

import com.atat.common.base.controller.BaseController;
import com.atat.common.prop.BasePropertyDate;
import com.atat.common.util.CollectionUtil;
import com.atat.common.util.JsonUtil;
import com.atat.common.util.StringUtil;
import com.atat.common.util.httpClient.URLUtil;
import com.atat.property.service.PropertyMapService;
import com.atat.property.service.impl.PropertyMapServiceImpl;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author ligw
 * @version $Id weixinAction.java, v 0.1 2017-06-24 20:18 ligw Exp $$
 */
public class WeixinAction {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

//    @Autowired
//    PropertyMapService propertyMapService;

    public static final String GETACCESSTOKENURL = "https://api.weixin.qq.com/cgi-bin/token";

    public static final String GETAJSAPITICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";

    /**
     * 更新微信accesstoken  jsapiTacket
     * 保存至数据库
     */
    public void refreshWxaccessToken() {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");//此文件放在SRC目录下

        PropertyMapService propertyMapService=(PropertyMapService)context.getBean("propertyMapService");


        String appid = BasePropertyDate.WX_APPID;
        String secret = BasePropertyDate.WX_SECRET;
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("appid", appid);
        paramMap.put("secret", secret);
        paramMap.put("grant_type", "client_credential");
        String url = GETACCESSTOKENURL;
        String resJson = null;
        Date postTime;
        try {
            postTime = new Date();
            resJson = URLUtil.originalGetData(url, paramMap);
            logger.info("微信平台get请求AccessToken：" + URLUtil.getDataUrl(url, paramMap));
        }
        catch (Exception e) {
            logger.error("[微信平台get请求AccessToken][Http请求异常" + e.getMessage() + "]", e);
        }
        logger.info("微信平台get请求AccessToken结果：\n\n\n\n\n\n\n\n\n\n\n[" + resJson + "]");
        // 解析Json 获取AppId
        Map<String, Object> resMap = JsonUtil.fromJson(resJson, Map.class);
        String access_token = (String) resMap.get("access_token");
        Integer expires_in = (Integer) resMap.get("expires_in");
        if (StringUtil.isNotEmpty(access_token)) {
            // 保存accessToken
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("propertyMapKey", "accessToken");
            List<Map<String, Object>> propList = propertyMapService.selectPropertyMapList(param);
            if (CollectionUtil.isNotEmpty(propList)) {
                param.put("propval", access_token);
                propertyMapService.updatePropertyMapByKey(param);
            }
            else {
                propertyMapService.addPropertyMap(param);
            }
            // 依据AccessToken计算
            Map<String, String> jsapiParamMap = new HashMap<String, String>();
            jsapiParamMap.put("access_token", access_token);
            jsapiParamMap.put("type", "jsapi");
            url = GETAJSAPITICKET;
            resJson = null;
            try {
                resJson = URLUtil.originalGetData(url, jsapiParamMap);
                logger.info("微信平台get请求jsapiTicket：" + URLUtil.getDataUrl(url, paramMap));
            }
            catch (Exception e) {
                logger.error("[微信平台get请求jsapiTicket][Http请求异常" + e.getMessage() + "]", e);
            }
            logger.info("微信平台get请求jsapiTicket：\n\n\n\n\n\n\n\n\n\n\n[" + resJson + "]");
            // 解析Json 获取jsapi_ticket
            Map<String, Object> jsapiResMap = JsonUtil.fromJson(resJson, Map.class);
            Integer errcode = (Integer) jsapiResMap.get("errcode");
             String errmsg = (String) jsapiResMap.get("errmsg");
            String ticket = (String) jsapiResMap.get("ticket");
            Integer expires_in_jsapi = (Integer) jsapiResMap.get("expires_in");
            if (StringUtil.isNotEmpty(ticket)) {
                // 校验签名用的参数 参与签名的字段包括noncestr（随机字符串）, 有效的jsapi_ticket,
                // timestamp（时间戳）, url（当前网页的URL，不包含#及其后面部分）
                // 随机字符串
                String noncestr = UUID.randomUUID().toString().replaceAll("-", "");
                // 时间戳
                String timestamp = String.valueOf(new Date().getTime());
                String mainurl = "http://s-357114.gotocdn.com/smart_home/client/home";
                // 获取签名
                List<String> tmpArr = new ArrayList<String>();
                tmpArr.add(noncestr);
                tmpArr.add(ticket);
                tmpArr.add(timestamp);
                tmpArr.add(mainurl);
                // 将数组按照字符的顺序排列
                Collections.sort(tmpArr);
                //signature
                String signature = null;
                try {
                    signature = sha1(implode(tmpArr));
                    //保存时间戳
                    Map<String, Object> jsapiticket_timestamp = new HashMap<String, Object>();
                    jsapiticket_timestamp.put("propertyMapKey","jsapiticketTimestamp");
                    propList = propertyMapService.selectPropertyMapList(jsapiticket_timestamp);
                    if (CollectionUtil.isNotEmpty(propList)) {
                        jsapiticket_timestamp.put("propval", timestamp);
                        propertyMapService.updatePropertyMapByKey(jsapiticket_timestamp);
                    }
                    else {
                        propertyMapService.addPropertyMap(jsapiticket_timestamp);
                    }
                    //保存随机字符串
                    Map<String, Object> jsapiticket_noncestr = new HashMap<String, Object>();
                    jsapiticket_noncestr.put("propertyMapKey","jsapiticketNnoncestr");
                    propList = propertyMapService.selectPropertyMapList(jsapiticket_noncestr);
                    if (CollectionUtil.isNotEmpty(propList)) {
                        jsapiticket_noncestr.put("propval", noncestr);
                        propertyMapService.updatePropertyMapByKey(jsapiticket_noncestr);
                    }
                    else {
                        propertyMapService.addPropertyMap(jsapiticket_noncestr);
                    }
                    //保存url
                    Map<String, Object> jsapiticket_mainurl = new HashMap<String, Object>();
                    jsapiticket_mainurl.put("propertyMapKey","jsapiticketMainurl");
                    propList = propertyMapService.selectPropertyMapList(jsapiticket_mainurl);
                    if (CollectionUtil.isNotEmpty(propList)) {
                        jsapiticket_mainurl.put("propval", mainurl);
                        propertyMapService.updatePropertyMapByKey(jsapiticket_mainurl);
                    }
                    else {
                        propertyMapService.addPropertyMap(jsapiticket_mainurl);
                    }
                    //保存ticket
                    Map<String, Object> jsapiticket_ticket = new HashMap<String, Object>();
                    jsapiticket_ticket.put("propertyMapKey","jsapiticketTicket");
                    propList = propertyMapService.selectPropertyMapList(jsapiticket_ticket);
                    if (CollectionUtil.isNotEmpty(propList)) {
                        jsapiticket_ticket.put("propval", ticket);
                        propertyMapService.updatePropertyMapByKey(jsapiticket_ticket);
                    }
                    else {
                        propertyMapService.addPropertyMap(jsapiticket_ticket);
                    }
                    //保存signature
                    Map<String, Object> jsapiticket_signature = new HashMap<String, Object>();
                    jsapiticket_signature.put("propertyMapKey","jsapiticketSignaturet");
                    propList = propertyMapService.selectPropertyMapList(jsapiticket_signature);
                    if (CollectionUtil.isNotEmpty(propList)) {
                        jsapiticket_signature.put("propval", signature);
                        propertyMapService.updatePropertyMapByKey(jsapiticket_signature);
                    }
                    else {
                        propertyMapService.addPropertyMap(jsapiticket_signature);
                    }
                }
                catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            // qingqiuAccessTocken失败
            Integer errcode = (Integer) resMap.get("errcode");
            String errmsg = (String) resMap.get("errmsg");
        }
    }

    // 将数组拼接成字符串
    public String implode(List<String> list) {
        StringBuilder sb = new StringBuilder(list.size() * 3);
        for (int i = 0;i < list.size();i++) {
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    // 执行sh1哈希散列运算算法
    public static String sha1(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update(data.getBytes());
        StringBuffer buf = new StringBuffer();
        byte[] bits = md.digest();
        for (int i = 0;i < bits.length;i++) {
            int a = bits[i];
            if (a < 0) {
                a += 256;
            }
            if (a < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(a));
        }
        return buf.toString();
    }
}
