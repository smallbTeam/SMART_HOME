/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.account.dao;

import com.atat.account.bean.Customer;

import java.util.List;

/**
 * @author ligw
 * @version $Id CustomerMapper.java, v 0.1 2017-06-06 5:17 ligw Exp $$
 */
public interface CustomerMapper {
    /**
     * 添加用户(必须先校验用户是否已存在）
     * @param Customer
     * @return
     */
    public void addCustomer(Customer Customer);

    /**
     * 通过手机号获取用户信息
     * @param mobelPhone
     * @return
     */
    public List<Customer> getCustomerByMobel(String mobelPhone);
}
