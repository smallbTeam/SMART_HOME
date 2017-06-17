/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.device.service.impl;

import com.atat.device.dao.GatewayMapping;
import com.atat.device.service.GatewayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author ligw
 * @version $Id GatewayServiceImpl.java, v 0.1 2017-06-17 19:21 ligw Exp $$
 */
@Service
public class GatewayServiceImpl implements GatewayService{

    @Resource
    private GatewayMapping gatewayMapping;

    @Override public void addGateway(Map<String, Object> param) {
        gatewayMapping.addGateway(param);
    }

    @Override public void updateGatewayByID(Map<String, Object> param) {
        gatewayMapping.updateGatewayByID(param);
    }

    @Override public List<Map<String, Object>> selectGatewayList(Map<String, Object> param) {
        return gatewayMapping.selectGatewayList(param);
    }

    @Override public void addCustomerGatewayRel(Map<String, Object> param) {
        gatewayMapping.addCustomerGatewayRel(param);
    }

    @Override public void delCustomerGatewayRel(Map<String, Object> param) {
        gatewayMapping.delCustomerGatewayRel(param);
    }
}
