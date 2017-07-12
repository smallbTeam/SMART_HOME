package com.atat.device.service;

import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface DeviceCategoryService {

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
     * 依据条件查找分页列表
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String, Object>> getDeviceCategoryPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize);

    /**
     * 依据Id查找详情
     * @param deviceCategoryId
     * @return
     */
    public Map<String, Object> getDeviceCategoryById(Long deviceCategoryId);

    /**
     * 依据Id删除记录
     * @param deviceCategoryId
     */
    public void delDeviceCategoryById(Long deviceCategoryId);
}
