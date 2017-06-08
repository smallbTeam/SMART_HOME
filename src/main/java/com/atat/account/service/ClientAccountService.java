/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.account.service;

import com.atat.account.bean.Customer;

import java.util.List;

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
    public List<Customer> getCustomerByMobel(String mobelPhone);
}
