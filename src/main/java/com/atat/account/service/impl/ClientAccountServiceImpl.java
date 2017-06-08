/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.account.service.impl;

import com.atat.account.bean.Customer;
import com.atat.account.dao.CustomerMapper;
import com.atat.account.service.ClientAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ligw
 * @version $Id ClientAccountServiceImpl.java, v 0.1 2017-06-06 5:09 ligw Exp $$
 */
@Service
public class ClientAccountServiceImpl implements ClientAccountService {

    @Resource
    private CustomerMapper customerMapper;

    @Override
    public void addCustomer(Customer customer) {
        customerMapper.addCustomer(customer);
    }

    @Override
    public List<Customer> getCustomerByMobel(String mobelPhone) {
        return customerMapper.getCustomerByMobel(mobelPhone);
    }
}
