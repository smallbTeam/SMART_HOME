package com.atat.device.service.impl;

import com.atat.device.dao.DeviceDateWeekDao;
import com.atat.device.service.DeviceDateWeekService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DeviceDateWeekServiceImpl implements DeviceDateWeekService {

    @Autowired
    private DeviceDateWeekDao deviceDateWeekDao;

    @Override
    public void  addDeviceDateWeek(Map<String, Object> param) {
        deviceDateWeekDao.addDeviceDateWeek(param);
    }

    @Override
    public void  updateDeviceDateWeekById(Map<String, Object> param) {
        deviceDateWeekDao.updateDeviceDateWeekById(param);
    }

    @Override
    public List<Map<String, Object>> selectDeviceDateWeekList(Map<String, Object> param) {
        return deviceDateWeekDao.selectDeviceDateWeekList(param);
    }

    @Override
    public PageInfo<Map<String, Object>> getDeviceDateWeekPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;

        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String,Object>> list = deviceDateWeekDao.selectDeviceDateWeekList(param);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
        return page;
    }

    @Override
    public Map<String, Object> getDeviceDateWeekById(String deviceDateWeekId) {
        Map<String, Object> deviceDateWeekinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("deviceDateWeekId", deviceDateWeekId);
        List<Map<String, Object>> deviceDateWeekList = deviceDateWeekDao.selectDeviceDateWeekList(rs);
        if ((null != deviceDateWeekList) && (deviceDateWeekList.size() > 0)) {
            deviceDateWeekinfo = deviceDateWeekList.get(0);
        }
        return deviceDateWeekinfo;
    }

    @Override
    public void delDeviceDateWeekById(String deviceDateWeekId) {
        deviceDateWeekDao.delDeviceDateWeekById(deviceDateWeekId);
    }

}
