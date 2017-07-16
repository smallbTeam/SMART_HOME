/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.message.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @author ligw
 * @version $Id weixinMessageService.java, v 0.1 2017-07-15 16:00 ligw Exp $$
 */
public interface WeixinMessageService {

    /**
     * 发送微信模板消息
     * @param touser
     * @param url
     * @param template_id
     * @param data
     * @return
     */
    public Integer sendWeixinMessage(List<String> touser,String url,String template_id, JSONObject data);


    //获取用户appID
    public String getUserwxId(String code);
}
