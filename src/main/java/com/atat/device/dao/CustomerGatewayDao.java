package com.atat.device.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface CustomerGatewayDao {

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
     * 依据主键删除记录
     * @param customerGatewayKey
     */
    public void  delCustomerGatewayByKey(Integer customerGatewayKey);

}
