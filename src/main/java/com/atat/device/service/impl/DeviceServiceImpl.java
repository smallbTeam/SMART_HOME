/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.device.service.impl;

import com.atat.common.util.CollectionUtil;
import com.atat.device.dao.DeviceMapper;
import com.atat.device.service.DeviceService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
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

    @Override public void addOrUpdateDeviceDataBydeviceTypeAndgateway(Map<String, Object> param) {
        //判断设备类型是否存在并返回id
        Integer deviceTypeId = addOrGetdeviceTypeByName((String) param.get("Name"));
        if (null != deviceTypeId){
            Map<String, Object> selectparam = new HashMap<String, Object>();
            selectparam.put("DeviceTypeId",deviceTypeId);
            selectparam.put("gatewayDeviceID",(String) param.get("gatewayDeviceID"));
            List<Map<String, Object>> deviceList = deviceMapper.selectDeviceList(selectparam);
            if (CollectionUtil.isNotEmpty(deviceList)){
                selectparam.put("DeviceId",Integer.parseInt(((Long) deviceList.get(0).get("id")).toString()));
                selectparam.put("DeviceData",(String) param.get("DeviceData"));
                deviceMapper.updateDeviceById(selectparam);
            } else {
                selectparam.put("DeviceData",(String) param.get("DeviceData"));
                deviceMapper.addDevice(selectparam);
            }
        } else {
            System.out.println("\n\n\n\n\n\n\n\n\\n\n\n设备类型添加失败\n\n\n\n\n\n\n\n\n\n\n");
        }
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

    /**
     * 依据设备类型名获取或新增设备类型
     * @return
     */
    public Integer addOrGetdeviceTypeByName(String Name){
        Integer deviceTypeId;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("Name",Name);
        List<Map<String, Object>> deviceTypeList = deviceMapper.selectDeviceType(param);
        if (CollectionUtil.isNotEmpty(deviceTypeList)){
            deviceTypeId = Integer.parseInt(((Long) deviceTypeList.get(0).get("id")).toString());
            return deviceTypeId;
        } else {
            //添加设备属性
            deviceMapper.addDeviceType(param);
            deviceTypeList = deviceMapper.selectDeviceType(param);
            if (CollectionUtil.isNotEmpty(deviceTypeList)) {
                deviceTypeId = Integer.parseInt(((Long) deviceTypeList.get(0).get("id")).toString());
                return deviceTypeId;
            }
            return null;
        }

    }
}
