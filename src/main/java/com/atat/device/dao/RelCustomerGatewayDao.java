package com.atat.device.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface RelCustomerGatewayDao {

    /**
     * 添加 客户网关绑定
     * @param param
     */
    public void  addRelCustomerGateway(Map<String, Object> param);

    /**
     * 依据主键更新客户网关绑定
     * @param param
     */
    public void  updateRelCustomerGatewayById(Map<String, Object> param);

    /**
     * 依据条件查找客户网关绑定列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectRelCustomerGatewayList(Map<String, Object> param);

    /**
     * 依据主键删除客户网关绑定记录
     * @param relCustomerGatewayId
     */
    public void  delRelCustomerGatewayById(Long relCustomerGatewayId);

}
