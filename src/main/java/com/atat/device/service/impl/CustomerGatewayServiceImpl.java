package com.atat.device.service.impl;

import com.atat.common.util.CollectionUtil;
import com.atat.device.dao.CustomerGatewayDao;
import com.atat.device.dao.GatewayDao;
import com.atat.device.service.CustomerGatewayService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CustomerGatewayServiceImpl implements CustomerGatewayService {

    @Autowired
    private CustomerGatewayDao customerGatewayDao;

    @Autowired
    private GatewayDao gatewayDao;

    @Override
    public void  addCustomerGateway(Map<String, Object> param) {
        customerGatewayDao.addCustomerGateway(param);
    }

    @Override
    public void  updateCustomerGatewayByKey(Map<String, Object> param) {
        customerGatewayDao.updateCustomerGatewayByKey(param);
    }

    @Override
    public List<Map<String, Object>> selectCustomerGatewayList(Map<String, Object> param) {
        return customerGatewayDao.selectCustomerGatewayList(param);
    }

    @Override
    public PageInfo<Map<String, Object>> getCustomerGatewayPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;

        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String,Object>> list = customerGatewayDao.selectCustomerGatewayList(param);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
        return page;
    }

    @Override
    public Map<String, Object> getCustomerGatewayByKey(Integer customerGatewayKey) {
        Map<String, Object> customerGatewayinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("customerGatewayKey", customerGatewayKey);
        List<Map<String, Object>> customerGatewayList = customerGatewayDao.selectCustomerGatewayList(rs);
        if ((null != customerGatewayList) && (customerGatewayList.size() > 0)) {
            customerGatewayinfo = customerGatewayList.get(0);
        }
        return customerGatewayinfo;
    }

    @Override
    public void delCustomerGatewayByKey(Integer customerGatewayKey) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("isDeleted", 1);
        param.put("customerGatewayKey", customerGatewayKey);
        customerGatewayDao.updateCustomerGatewayByKey(param);
    }

    @Override
    public void addGatewayForCustomer(Map<String, Object> param) {
        //依据网关设备检验网关是否存在
        Map<String, Object> selectParam = new HashMap<String, Object>();
        selectParam.put("gatewayDeviceID",(String) param.get("gatewayDeviceID"));
        List<Map<String, Object>> gatewayList = gatewayDao.selectGatewayList(selectParam);
        if (CollectionUtil.isEmpty(gatewayList)){
            gatewayDao.addGateway(selectParam);
        }
        //添加关联关系
        customerGatewayDao.addCustomerGateway(param);
    }

    @Override public void delGatewayForCustomer(Map<String, Object> param) {
        List<Map<String, Object>> gatewarCusRelList = customerGatewayDao.selectCustomerGatewayList(param);
        if (CollectionUtil.isNotEmpty(gatewarCusRelList)){
            for (Map<String, Object> gatewarCusRel : gatewarCusRelList) {
                Map<String, Object> delparam = new HashMap<String, Object>();
                delparam.put("isDeleted", 1);
                delparam.put("customerGatewayKey", gatewarCusRel.get("customerGatewayKey"));
                customerGatewayDao.updateCustomerGatewayByKey(delparam);
            }
        }
    }
}
