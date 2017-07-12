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
     * 依据网关设备号更新
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
     * 依据条件查找分页列表
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String, Object>> getGatewayPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize);

    /**
     * 依据网关设备号查找详情
     * @param serialNumber
     * @return
     */
    public Map<String, Object> getGatewayBySerialNumber(String serialNumber);

    /**
     * 依据Id删除记录
     * @param gatewayId
     */
    public void delGatewayById(Long gatewayId);

    /**
     * 依据网关设备序号删除网关
     * @param serialNumber
     */
    public void delGatewayBySerialNumber(String serialNumber);
}
