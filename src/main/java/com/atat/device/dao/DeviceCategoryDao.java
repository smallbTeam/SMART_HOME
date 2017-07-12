package com.atat.device.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface DeviceCategoryDao {

    /**
     * 添加
     * @param param
     */
    public void  addDeviceCategory(Map<String, Object> param);

    /**
     * 依据主键更新
     * @param param
     */
    public void  updateDeviceCategoryById(Map<String, Object> param);

    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectDeviceCategoryList(Map<String, Object> param);

    /**
     * 依据主键删除记录
     * @param deviceCategoryId
     */
    public void  delDeviceCategoryById(Long deviceCategoryId);

}
