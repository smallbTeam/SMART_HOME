/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.account.service;

import com.atat.account.bean.Customer;
import com.atat.common.util.StringUtil;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 用户账户相关服务
 * @author ligw
 * @version $Id ClientAccountService.java, v 0.1 2017-06-06 4:24 ligw Exp $$
 */
public interface ClientAccountService {

    /**
     * 添加用户(必须先校验用户是否已存在）
     * @param customer
     * @return
     */
    public void addCustomer(Customer customer);

    /**
     * 通过手机号获取用户信息
     * @param mobelPhone
     * @return
     */
    public Customer getCustomerByMobel(String mobelPhone);

    /**
     * 获取庄户列表
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String,Object>> getCustomerPageTurn(Map<String,Object> param,Integer pageNo,Integer pageSize);


    /**
     * 依据ID更新客户
     * @param param
     */
    public void updateCustomerById(Map<String,Object> param);

    /**
     * 依据条件查找用户
     * @param param
     * @return
     */
    public List<Map<String,Object>> selectCustomerList (Map<String,Object> param);

    /**
     * 依据微信ID查找用户
     * @param wxId
     * @return
     */
    public Map<String,Object> selectCustomerByWxid(String wxId);

    /**
     * 用户登录较验
     * @param mobelPhone
     * @param password
     * @param wxId
     * @return
     */
    public Integer accountLogin(String mobelPhone,String password,String wxId);
}
