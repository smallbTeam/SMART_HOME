package com.atat.device.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface DeviceDataHourDao {

    /**
     * 添加
     * @param param
     */
    public void  addDeviceDataHour(Map<String, Object> param);

    /**
     * 批量插入设备每小时数据
     */
    public void addDeviceDataHourList(List<Map<String, Object>> deviceDataList);

    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectDeviceDataHourList(Map<String, Object> param);

    /**
     * 依据主键删除记录
     * @param deviceDataHourId
     */
    public void  delDeviceDataHourById(Long deviceDataHourId);

    /**
     * 计算设备一天内平均值
     * @param param
     * @return
     */
    public List<Map<String, Object>> timingHourAverageData(Map<String, Object> param);

    /**
     * 删除截止时间前的记录
     * @param recordTimeEnd
     */
    public void  delDeviceDataHourByEndTime(Long recordTimeEnd);

}
