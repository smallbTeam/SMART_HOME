package com.atat.account.service.impl;

import com.atat.account.dao.CustomerDao;
import com.atat.account.service.CustomerService;
import com.atat.common.util.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public void  addCustomer(Map<String, Object> param) {
        customerDao.addCustomer(param);
    }

    @Override
    public void  updateCustomerById(Map<String, Object> param) {
        customerDao.updateCustomerById(param);
    }

    @Override
    public List<Map<String, Object>> selectCustomerList(Map<String, Object> param) {
        return customerDao.selectCustomerList(param);
    }

    @Override
    public PageInfo<Map<String, Object>> getCustomerPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;

        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String,Object>> list = customerDao.selectCustomerList(param);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
        return page;
    }

    @Override
    public Map<String, Object> getCustomerById(Integer customerId) {
        Map<String, Object> customerinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("customerId", customerId);
        List<Map<String, Object>> customerList = customerDao.selectCustomerList(rs);
        if ((null != customerList) && (customerList.size() > 0)) {
            customerinfo = customerList.get(0);
        }
        return customerinfo;
    }

    @Override
    public void delCustomerById(Integer customerId) {
        customerDao.delCustomerById(customerId);
    }

    @Override public Map<String, Object> getCustomerByMobelPhone(String mobelPhone) {
        Map<String, Object> customerinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("mobelPhone", mobelPhone);
        List<Map<String, Object>> customerList = customerDao.selectCustomerList(rs);
        if ((null != customerList) && (customerList.size() > 0)) {
            customerinfo = customerList.get(0);
        }
        return customerinfo;
    }

    @Override public Map<String, Object> getCustomerByWxId(String wxId) {
        Map<String, Object> customerinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("wxId", wxId);
        List<Map<String, Object>> customerList = customerDao.selectCustomerList(rs);
        if ((null != customerList) && (customerList.size() > 0)) {
            customerinfo = customerList.get(0);
        }
        return customerinfo;
    }

    @Override public Integer accountLogin(String mobelPhone, String password, String wxId) {
        Map<String, Object> loginRes = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mobelPhone",mobelPhone);
        param.put("password",password);
        List<Map<String, Object>> customerList = customerDao.selectCustomerList(param);
        if (CollectionUtil.isNotEmpty(customerList)){
            //            Map<String, Object> customer = customerList.get(0);
            //            //绑定wxId
            //            Map<String, Object> updateParam = new HashMap<String, Object>();
            //            updateParam.put("CustomerId",customer.get("id"));
            //            updateParam.put("WxId",wxId);
            //            customerMapper.updateCustomerById(param);
            return 1;
        } else {
            return 0;
        }

    }
}
