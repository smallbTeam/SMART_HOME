package com.atat.device.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface DeviceDao {

    /**
     * 添加
     * @param param
     */
    public void  addDevice(Map<String, Object> param);

    /**
     * 依据主键更新
     * @param param
     */
    public void  updateDeviceById(Map<String, Object> param);

    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectDeviceList(Map<String, Object> param);

    /**
     * 依据主键删除记录
     * @param deviceId
     */
    public void  delDeviceById(Long deviceId);

}
