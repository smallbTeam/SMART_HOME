package com.atat.device.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface DeviceDateHourDao {

    /**
     * 添加
     * @param param
     */
    public void  addDeviceDateHour(Map<String, Object> param);

    /**
     * 依据主键更新
     * @param param
     */
    public void  updateDeviceDateHourById(Map<String, Object> param);

    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectDeviceDateHourList(Map<String, Object> param);

    /**
     * 依据主键删除记录
     * @param deviceDateHourId
     */
    public void  delDeviceDateHourById(String deviceDateHourId);

}
