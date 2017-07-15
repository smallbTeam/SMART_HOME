package com.atat.device.service.impl;

import com.atat.device.dao.DeviceDateNowDao;
import com.atat.device.service.DeviceDateNowService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DeviceDateNowServiceImpl implements DeviceDateNowService {

    @Autowired
    private DeviceDateNowDao deviceDateNowDao;

    @Override
    public void  addDeviceDateNow(Map<String, Object> param) {
        deviceDateNowDao.addDeviceDateNow(param);
    }

    @Override
    public void  updateDeviceDateNowById(Map<String, Object> param) {
        deviceDateNowDao.updateDeviceDateNowById(param);
    }

    @Override
    public List<Map<String, Object>> selectDeviceDateNowList(Map<String, Object> param) {
        return deviceDateNowDao.selectDeviceDateNowList(param);
    }

    @Override
    public PageInfo<Map<String, Object>> getDeviceDateNowPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;

        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String,Object>> list = deviceDateNowDao.selectDeviceDateNowList(param);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
        return page;
    }

    @Override
    public Map<String, Object> getDeviceDateNowById(String deviceDateNowId) {
        Map<String, Object> deviceDateNowinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("deviceDateNowId", deviceDateNowId);
        List<Map<String, Object>> deviceDateNowList = deviceDateNowDao.selectDeviceDateNowList(rs);
        if ((null != deviceDateNowList) && (deviceDateNowList.size() > 0)) {
            deviceDateNowinfo = deviceDateNowList.get(0);
        }
        return deviceDateNowinfo;
    }

    @Override
    public void delDeviceDateNowById(String deviceDateNowId) {
        deviceDateNowDao.delDeviceDateNowById(deviceDateNowId);
    }

}
