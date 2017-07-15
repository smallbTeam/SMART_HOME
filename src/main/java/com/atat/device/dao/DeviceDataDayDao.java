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
     * 批量插入设备每天数据
     */
    public void addDeviceDataDayList(List<Map<String, Object>> deviceDataList);

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
    public void  delDeviceDataDayById(Long deviceDataDayId);

    /**
     * 计算时间段内的平均数据
     * @param param
     * @return
     */
    public List<Map<String, Object>> timingDayAverageData(Map<String, Object> param);

    /**
     * 删除指定时间前的数据
     * @param deviceDataDayId
     */
    public void delDeviceDataDayByEndTime(Long deviceDataDayId);

}
