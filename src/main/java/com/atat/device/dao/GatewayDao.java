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
    public void  updateGatewayBySerialNumber(Map<String, Object> param);

    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectGatewayList(Map<String, Object> param);

    /**
     * 依据主键删除记录
     * @param gatewayId
     */
    public void  delGatewayById(Long gatewayId);

    /**
     * 依据网关设备序号删除网关
     * @param serialNumber
     */
    public void delGatewayBySerialNumber(String serialNumber);

}
