/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.account.service.impl;

import com.atat.account.bean.Customer;
import com.atat.account.dao.CustomerMapper;
import com.atat.account.service.ClientAccountService;
import com.atat.common.util.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Customer getCustomerByMobel(String mobelPhone) {
        List<Customer> customerList = customerMapper.getCustomerByMobel(mobelPhone);
        if (CollectionUtil.isNotEmpty(customerList)){
            return customerList.get(0);
        } else {
            return null;
        }
    }

    @Override public PageInfo<Map<String,Object>> getCustomerPageTurn(Map<String,Object> param,Integer pageNo,Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;

        pageSize = pageSize == null?10:pageSize;

        PageHelper.startPage(pageNo, pageSize);

        List<Map<String,Object>> list = customerMapper.selectCustomerList(param);

        //用PageInfo对结果进行包装

        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);

        return page;
    }

    @Override public void updateCustomerById(Map<String, Object> param) {
        customerMapper.updateCustomerById(param);
    }

    @Override public List<Map<String, Object>> selectCustomerList(Map<String, Object> param) {
        return customerMapper.selectCustomerList(param);
    }

    @Override public Map<String, Object> selectCustomerByWxid(String wxId) {
        Map<String, Object> param = new HashMap<String,Object>();
        param.put("WxId",wxId);
        List<Map<String, Object>> customerList = customerMapper.selectCustomerList(param);
        if (CollectionUtil.isNotEmpty(customerList)){
            return customerList.get(0);
        } else {
            return null;
        }
    }

    @Override public Integer accountLogin(String mobelPhone, String password, String wxId) {
        Map<String, Object> loginRes = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("MobelPhone",mobelPhone);
        param.put("Password",password);
        List<Map<String, Object>> customerList = customerMapper.selectCustomerList(param);
        if (CollectionUtil.isNotEmpty(customerList)){
            Map<String, Object> customer = customerList.get(0);
            //绑定wxId
            Map<String, Object> updateParam = new HashMap<String, Object>();
            updateParam.put("CustomerId",customer.get("id"));
            updateParam.put("WxId",wxId);
            customerMapper.updateCustomerById(param);
            return 1;
        } else {
           return 0;
        }

    }
}
