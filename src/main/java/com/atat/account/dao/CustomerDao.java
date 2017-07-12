package com.atat.account.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface CustomerDao {

    /**
     * 添加
     * @param param
     */
    public void  addCustomer(Map<String, Object> param);

    /**
     * 依据主键更新
     * @param param
     */
    public void  updateCustomerById(Map<String, Object> param);

    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectCustomerList(Map<String, Object> param);

    /**
     * 依据主键删除记录
     * @param customerId
     */
    public void  delCustomerById(Integer customerId);

}
