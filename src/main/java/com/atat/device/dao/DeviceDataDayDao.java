package com.atat.device.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface DeviceDataDayDao {

    /**
     * 添加
     * @param param
     */
    public void  addDeviceDataDay(Map<String, Object> param);


    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectDeviceDataDayList(Map<String, Object> param);

    /**
     * 依据主键删除记录
     * @param deviceDataDayId
     */
    public void  delDeviceDataDayById(String deviceDataDayId);

}
