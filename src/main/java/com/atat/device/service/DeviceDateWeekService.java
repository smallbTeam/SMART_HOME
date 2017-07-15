package com.atat.device.service;

import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface DeviceDateWeekService {

    /**
     * 添加
     * @param param
     */
    public void  addDeviceDateWeek(Map<String, Object> param);

    /**
     * 依据主键更新
     * @param param
     */
    public void  updateDeviceDateWeekById(Map<String, Object> param);

    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectDeviceDateWeekList(Map<String, Object> param);

    /**
     * 依据条件查找分页列表
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String, Object>> getDeviceDateWeekPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize);

    /**
     * 依据Id查找详情
     * @param deviceDateWeekId
     * @return
     */
    public Map<String, Object> getDeviceDateWeekById(String deviceDateWeekId);

    /**
     * 依据Id删除记录
     * @param deviceDateWeekId
     */
    public void delDeviceDateWeekById(String deviceDateWeekId);
}
