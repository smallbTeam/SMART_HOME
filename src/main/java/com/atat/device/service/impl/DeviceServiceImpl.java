/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.device.service.impl;

import com.atat.device.dao.DeviceMapper;
import com.atat.device.service.DeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author ligw
 * @version $Id DeviceServiceImpl.java, v 0.1 2017-06-17 19:18 ligw Exp $$
 */
@Service
public class DeviceServiceImpl implements DeviceService{

    @Resource
    private DeviceMapper deviceMapper;

    @Override public void addDevice(Map<String, Object> param) {
        deviceMapper.addDevice(param);

    }

    @Override public List<Map<String, Object>> selectDeviceList(Map<String, Object> param) {
        return deviceMapper.selectDeviceList(param);
    }

    @Override public void updateDeviceById(Map<String, Object> param) {
        deviceMapper.updateDeviceById(param);
    }

    @Override public void addDeviceType(Map<String, Object> param) {
        deviceMapper.addDeviceType(param);
    }

    @Override public void updateDeviceTypeByID(Map<String, Object> param) {
        deviceMapper.updateDeviceTypeByID(param);
    }

    @Override public List<Map<String, Object>> selectDeviceType(Map<String, Object> param) {

        return deviceMapper.selectDeviceType(param);
    }

    @Override public void addDeviceData(Map<String, Object> param) {
        deviceMapper.addDeviceData(param);
    }

    @Override public List<Map<String, Object>> selectDeviceDataList(Map<String, Object> param) {
        return deviceMapper.selectDeviceDataList(param);
    }

    @Override public void addDeviceDailyData(Map<String, Object> param) {
        deviceMapper.addDeviceDailyData(param);
    }

    @Override public List<Map<String, Object>> selectDeviceDailyDataList(Map<String, Object> param) {
        return deviceMapper.selectDeviceDailyDataList(param);
    }

    @Override public void addDeviceMonthData(Map<String, Object> param) {
        deviceMapper.addDeviceMonthData(param);
    }

    @Override public List<Map<String, Object>> selectDeviceMonthDataList(Map<String, Object> param) {
        return deviceMapper.selectDeviceMonthDataList(param);
    }
}
