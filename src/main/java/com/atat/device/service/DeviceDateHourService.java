package com.atat.device.service;

import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface DeviceDateHourService {

    /**
     * 添加
     * @param param
     */
    public void  addDeviceDateHour(Map<String, Object> param);

    /**
     * 依据主键更新
     * @param param
     */
    public void  updateDeviceDateHourById(Map<String, Object> param);

    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectDeviceDateHourList(Map<String, Object> param);

    /**
     * 依据条件查找分页列表
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String, Object>> getDeviceDateHourPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize);

    /**
     * 依据Id查找详情
     * @param deviceDateHourId
     * @return
     */
    public Map<String, Object> getDeviceDateHourById(String deviceDateHourId);

    /**
     * 依据Id删除记录
     * @param deviceDateHourId
     */
    public void delDeviceDateHourById(String deviceDateHourId);
}
