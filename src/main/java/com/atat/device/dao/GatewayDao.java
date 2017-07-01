package com.atat.device.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface GatewayDao {

    /**
     * 添加
     * @param param
     */
    public void  addGateway(Map<String, Object> param);

    /**
     * 依据主键更新
     * @param param
     */
    public void  updateGatewayByGatewayDeviceID(Map<String, Object> param);

    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectGatewayList(Map<String, Object> param);

    public List<Map<String, Object>> selectCustomerGatewayList(Map<String, Object> param);

    /**
     * 依据主键删除记录
     * @param gatewayKey
     */
    public void  delGatewayByKey(Integer gatewayKey);

}
