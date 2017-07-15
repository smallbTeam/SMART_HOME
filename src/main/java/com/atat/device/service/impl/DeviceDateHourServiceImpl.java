package com.atat.device.service.impl;

import com.atat.device.dao.DeviceDateHourDao;
import com.atat.device.service.DeviceDateHourService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DeviceDateHourServiceImpl implements DeviceDateHourService {

    @Autowired
    private DeviceDateHourDao deviceDateHourDao;

    @Override
    public void  addDeviceDateHour(Map<String, Object> param) {
        deviceDateHourDao.addDeviceDateHour(param);
    }

    @Override
    public void  updateDeviceDateHourById(Map<String, Object> param) {
        deviceDateHourDao.updateDeviceDateHourById(param);
    }

    @Override
    public List<Map<String, Object>> selectDeviceDateHourList(Map<String, Object> param) {
        return deviceDateHourDao.selectDeviceDateHourList(param);
    }

    @Override
    public PageInfo<Map<String, Object>> getDeviceDateHourPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;

        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String,Object>> list = deviceDateHourDao.selectDeviceDateHourList(param);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
        return page;
    }

    @Override
    public Map<String, Object> getDeviceDateHourById(String deviceDateHourId) {
        Map<String, Object> deviceDateHourinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("deviceDateHourId", deviceDateHourId);
        List<Map<String, Object>> deviceDateHourList = deviceDateHourDao.selectDeviceDateHourList(rs);
        if ((null != deviceDateHourList) && (deviceDateHourList.size() > 0)) {
            deviceDateHourinfo = deviceDateHourList.get(0);
        }
        return deviceDateHourinfo;
    }

    @Override
    public void delDeviceDateHourById(String deviceDateHourId) {
        deviceDateHourDao.delDeviceDateHourById(deviceDateHourId);
    }

}
