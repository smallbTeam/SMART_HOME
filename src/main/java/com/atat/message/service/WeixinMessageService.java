/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.message.service;

import java.util.Map;

/**
 * @author ligw
 * @version $Id weixinMessageService.java, v 0.1 2017-07-15 16:00 ligw Exp $$
 */
public interface WeixinMessageService {
    public Integer sendWeixinMessage(String touser,String url,String template_id, Map data);
}
