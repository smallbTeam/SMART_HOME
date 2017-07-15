package com.atat.device.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface DeviceDataNowDao {

    /**
     * 添加
     * @param param
     */
    public void  addDeviceDataNow(Map<String, Object> param);


    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectDeviceDataNowList(Map<String, Object> param);

    /**
     * 依据主键删除记录
     * @param deviceDataNowId
     */
    public void  delDeviceDataNowById(Long deviceDataNowId);

    /**
     * 分类计算平均值
     * @param param
     * @return
     */
    public List<Map<String, Object>> timingNowAverageData(Map<String, Object> param);

    /**
     * 移除截止时间前的记录
     * @param recordTimeEnd
     */
    public void  delDeviceDataNowByEndTime(Long recordTimeEnd);

}
