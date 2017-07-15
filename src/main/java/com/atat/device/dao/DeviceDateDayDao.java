package com.atat.device.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface DeviceDateDayDao {

    /**
     * 添加
     * @param param
     */
    public void  addDeviceDateDay(Map<String, Object> param);

    /**
     * 依据主键更新
     * @param param
     */
    public void  updateDeviceDateDayById(Map<String, Object> param);

    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectDeviceDateDayList(Map<String, Object> param);

    /**
     * 依据主键删除记录
     * @param deviceDateDayId
     */
    public void  delDeviceDateDayById(String deviceDateDayId);

}
