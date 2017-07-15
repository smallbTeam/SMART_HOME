package com.atat.device.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface DeviceDateNowDao {

    /**
     * 添加
     * @param param
     */
    public void  addDeviceDateNow(Map<String, Object> param);

    /**
     * 依据主键更新
     * @param param
     */
    public void  updateDeviceDateNowById(Map<String, Object> param);

    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectDeviceDateNowList(Map<String, Object> param);

    /**
     * 依据主键删除记录
     * @param deviceDateNowId
     */
    public void  delDeviceDateNowById(String deviceDateNowId);

}
