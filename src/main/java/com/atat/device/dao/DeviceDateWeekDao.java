package com.atat.device.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface DeviceDateWeekDao {

    /**
     * 添加
     * @param param
     */
    public void  addDeviceDateWeek(Map<String, Object> param);

    /**
     * 依据主键更新
     * @param param
     */
    public void  updateDeviceDateWeekById(Map<String, Object> param);

    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectDeviceDateWeekList(Map<String, Object> param);

    /**
     * 依据主键删除记录
     * @param deviceDateWeekId
     */
    public void  delDeviceDateWeekById(String deviceDateWeekId);

}
