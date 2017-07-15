package com.atat.device.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface DeviceDataWeekDao {

    /**
     * 添加
     * @param param
     */
    public void  addDeviceDataWeek(Map<String, Object> param);

    /**
     * 批量插入设备每周数据
     */
    public void addDeviceDataWeekList(List<Map<String, Object>> deviceDataList);

    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectDeviceDataWeekList(Map<String, Object> param);

    /**
     * 依据主键删除记录
     * @param deviceDataWeekId
     */
    public void  delDeviceDataWeekById(Long deviceDataWeekId);



}
