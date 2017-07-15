/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.message.service.impl;

import com.atat.message.service.WeixinMessageService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author ligw
 * @version $Id weixinMessageService.java, v 0.1 2017-07-15 16:03 ligw Exp $$
 */
@Service
public class WeixinMessageServiceImpl implements WeixinMessageService{

    @Override public Integer sendWeixinMessage(String touser, String url, String template_id, Map data) {
        return null;
    }
}
