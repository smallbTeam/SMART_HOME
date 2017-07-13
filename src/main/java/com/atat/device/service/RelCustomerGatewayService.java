package com.atat.device.service;

import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface RelCustomerGatewayService {

    /**
     * 添加
     * @param param
     */
    public void  addRelCustomerGateway(Map<String, Object> param);

    /**
     * 依据主键更新
     * @param param
     */
    public void  updateRelCustomerGatewayById(Map<String, Object> param);

    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectRelCustomerGatewayList(Map<String, Object> param);

    /**
     * 依据条件查找分页列表
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String, Object>> getRelCustomerGatewayPageTurn(Map<String, Object> param, Integer pageNo,
            Integer pageSize);

    /**
     * 依据Id查找详情
     * @param relCustomerGatewayId
     * @return
     */
    public Map<String, Object> getRelCustomerGatewayById(Long relCustomerGatewayId);

    /**
     * 依据Id删除记录
     * @param relCustomerGatewayId
     */
    public void delRelCustomerGatewayById(Long relCustomerGatewayId);

    /**
     * 依据用户ID和网关ID删除关联关系
     * @param param
     */
    public void delGatewayForCustomer(Map<String, Object> param);

    /**
     * 用户绑定网关
     * @param param
     */
    public Integer  addGatewayForCustomer(Map<String, Object> param);

    /**
     *
     * @return
     */
    public Integer addGateWayByInvite(Map<String, Object> param);
}
