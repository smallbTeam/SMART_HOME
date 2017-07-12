package com.atat.account.service;

import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface CustomerService {

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
     * 依据条件查找分页列表
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String, Object>> getCustomerPageTurn(Map<String, Object> param, Integer pageNo,
            Integer pageSize);

    /**
     * 依据Id查找详情
     * @param customerId
     * @return
     */
    public Map<String, Object> getCustomerById(Integer customerId);

    /**
     * 依据Id删除记录
     * @param customerId
     */
    public void delCustomerById(Integer customerId);

    /**
     * 依据手机号查询账户信息
     * @param mobelPhone
     * @return
     */
    public Map<String, Object> getCustomerByMobelPhone(String mobelPhone);

    /**
     * 依据微信Id查询账户信息
     * @param wxid
     * @return
     */
    public Map<String, Object> getCustomerByWxId(String wxid);

    /**
     * 用户登录较验
     * @param mobelPhone
     * @param password
     * @param wxId
     * @return
     */
    public Integer accountLogin(String mobelPhone,String password,String wxId);
}
