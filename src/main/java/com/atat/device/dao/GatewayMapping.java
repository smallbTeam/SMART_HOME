/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.device.dao;

import java.util.List;
import java.util.Map;

/**
 * @author ligw
 * @version $Id GatewayMapping.java, v 0.1 2017-06-17 17:41 ligw Exp $$
 */
public interface GatewayMapping {

    /**
     * 新增网关
     * @param param
     */
    public void addGateway(Map<String, Object> param);

    /**
     * 更新网关
     * @param param
     */
    public void updateGatewayByID(Map<String, Object> param);

    /**
     * 查找网管列表
     * @param param
     * @return
     */
    public List<Map<String,Object>> selectGatewayList(Map<String, Object> param);

    /**
     * 用户添加网关
     * @param param
     */
    public void addCustomerGatewayRel(Map<String, Object> param);

    /**
     * 用户删除网关
     * @param param
     */
    public void delCustomerGatewayRel(Map<String, Object> param);
}