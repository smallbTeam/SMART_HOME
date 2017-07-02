/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.device.service;

import java.util.List;
import java.util.Map;

/**
 * @author ligw
 * @version $Id DeviceService.java, v 0.1 2017-06-17 19:18 ligw Exp $$
 */
public interface DeviceService {
    /**
     * 添加设备
     * @param param
     */
    public void addDevice(Map<String,Object> param);

    /**
     * 查找设备
     * @param param
     * @return
     */
    public List<Map<String,Object>> selectDeviceList(Map<String,Object> param);

    /**
     * 更新设备
     * @param param
     */
    public void updateDeviceById(Map<String,Object> param);

    /**
     * 添加设备类型
     * @param param
     */
    public void addDeviceType(Map<String,Object> param);

    /**
     * 添加设备类型
     * @param param
     */
    public void addOrUpdateDeviceDataBydeviceTypeAndgateway(Map<String,Object> param);

    /**
     * 更新设备类型
     * @param param
     */
    public void updateDeviceTypeByID(Map<String,Object> param);

    /**
     * 查找设备类型
     * @param param
     * @return
     */
    public List<Map<String,Object>> selectDeviceType(Map<String,Object> param);


    /**
     * 记录实时设备数据
     * @param param
     */
    public void addDeviceData(Map<String,Object> param);

    /**
     * 查询实时设备数据
     * @param param
     * @return
     */
    public List<Map<String,Object>> selectDeviceDataList(Map<String,Object> param);

    /**
     * 记录设备每日数据
     * @param param
     */
    public void addDeviceDailyData(Map<String,Object> param);

    /**
     * 查询设备每日数据
     * @param param
     * @return
     */
    public List<Map<String,Object>> selectDeviceDailyDataList(Map<String,Object> param);

    /**
     * 添加设备每月数据
     * @param param
     */
    public void addDeviceMonthData(Map<String,Object> param);

    /**
     * 查询设备每月数据
     * @param param
     * @return
     */
    public List<Map<String,Object>> selectDeviceMonthDataList(Map<String,Object> param);
}
