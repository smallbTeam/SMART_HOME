/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.account.service.impl;

import com.atat.account.bean.Customer;
import com.atat.account.dao.CustomerMapper;
import com.atat.account.service.ClientAccountService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public List<Customer> getCustomerByMobel(String mobelPhone) {
        return customerMapper.getCustomerByMobel(mobelPhone);
    }

    @Override public PageInfo<Map<String,Object>> getCustomerPageTurn(Map<String,Object> param,Integer pageNo,Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;

        pageSize = pageSize == null?10:pageSize;

        PageHelper.startPage(pageNo, pageSize);

        List<Map<String,Object>> list = customerMapper.selectCustomerList(param);

        //用PageInfo对结果进行包装

        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);

        //测试PageInfo全部属性

        System.out.println(page.getPageNum());

        System.out.println(page.getPageSize());

        System.out.println(page.getStartRow());

        System.out.println(page.getEndRow());

        System.out.println(page.getTotal());

        System.out.println(page.getPages());

        System.out.println(page.getFirstPage());

        System.out.println(page.getLastPage());

        System.out.println(page.isHasPreviousPage());

        System.out.println(page.isHasNextPage());

        return page;
    }

    @Override public void updateCustomerById(Map<String, Object> param) {
        customerMapper.updateCustomerById(param);
    }
}
