package com.atat.device.service;

import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface GatewayService {

    /**
     * 添加
     * @param param
     */
    public void  addGateway(Map<String, Object> param);

    /**
     * 依据网关设备ID更新
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
     * 依据条件查找分页列表
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String, Object>> getGatewayPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize);

    /**
     * 依据Key查找详情
     * @param gatewayKey
     * @return
     */
    public Map<String, Object> getGatewayByKey(Integer gatewayKey);

    /**
     * 依据Key删除记录
     * @param gatewayDeviceID
     */
    public void delGatewayByGatewayDeviceID(String gatewayDeviceID);
}
