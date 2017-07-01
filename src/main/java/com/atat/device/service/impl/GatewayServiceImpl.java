package com.atat.device.service.impl;

import com.atat.device.dao.GatewayDao;
import com.atat.device.service.GatewayService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class GatewayServiceImpl implements GatewayService {

    @Autowired
    private GatewayDao gatewayDao;

    @Override
    public void  addGateway(Map<String, Object> param) {
        gatewayDao.addGateway(param);
    }

    @Override
    public void  updateGatewayByGatewayDeviceID(Map<String, Object> param) {
        gatewayDao.updateGatewayByGatewayDeviceID(param);
    }

    @Override
    public List<Map<String, Object>> selectGatewayList(Map<String, Object> param) {
        return gatewayDao.selectGatewayList(param);
    }

    public List<Map<String, Object>> selectCustomerGatewayList(Map<String, Object> param) {
        return gatewayDao.selectCustomerGatewayList(param);
    }

    @Override
    public PageInfo<Map<String, Object>> getGatewayPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;

        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String,Object>> list = gatewayDao.selectCustomerGatewayList(param);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
        return page;
    }

    @Override
    public Map<String, Object> getGatewayByKey(Integer gatewayKey) {
        Map<String, Object> gatewayinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("gatewayKey", gatewayKey);
        List<Map<String, Object>> gatewayList = gatewayDao.selectGatewayList(rs);
        if ((null != gatewayList) && (gatewayList.size() > 0)) {
            gatewayinfo = gatewayList.get(0);
        }
        return gatewayinfo;
    }

    @Override
    public void delGatewayByGatewayDeviceID(String gatewayDeviceID) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("isDeleted", 1);
        param.put("gatewayDeviceID", gatewayDeviceID);
        gatewayDao.updateGatewayByGatewayDeviceID(param);
    }

}
