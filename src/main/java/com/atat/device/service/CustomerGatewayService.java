package com.atat.device.service;

import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface CustomerGatewayService {

    /**
     * 添加
     * @param param
     */
    public void  addCustomerGateway(Map<String, Object> param);

    /**
     * 依据主键更新
     * @param param
     */
    public void  updateCustomerGatewayByKey(Map<String, Object> param);

    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectCustomerGatewayList(Map<String, Object> param);

    /**
     * 依据条件查找分页列表
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String, Object>> getCustomerGatewayPageTurn(Map<String, Object> param, Integer pageNo,
            Integer pageSize);

    /**
     * 依据Key查找详情
     * @param customerGatewayKey
     * @return
     */
    public Map<String, Object> getCustomerGatewayByKey(Integer customerGatewayKey);

    /**
     * 依据Key删除记录
     * @param customerGatewayKey
     */
    public void delCustomerGatewayByKey(Integer customerGatewayKey);

    /**
     * 为客户添加网关
     * @param param
     */
    public void  addGatewayForCustomer(Map<String, Object> param);

    /**
     * 为客户删除网关
     * @param param
     */
    public void delGatewayForCustomer (Map<String, Object> param);
}
