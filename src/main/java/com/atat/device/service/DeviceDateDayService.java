package com.atat.device.service;

import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface DeviceDateDayService {

    /**
     * 添加
     * @param param
     */
    public void  addDeviceDateDay(Map<String, Object> param);

    /**
     * 依据主键更新
     * @param param
     */
    public void  updateDeviceDateDayById(Map<String, Object> param);

    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectDeviceDateDayList(Map<String, Object> param);

    /**
     * 依据条件查找分页列表
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String, Object>> getDeviceDateDayPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize);

    /**
     * 依据Id查找详情
     * @param deviceDateDayId
     * @return
     */
    public Map<String, Object> getDeviceDateDayById(String deviceDateDayId);

    /**
     * 依据Id删除记录
     * @param deviceDateDayId
     */
    public void delDeviceDateDayById(String deviceDateDayId);
}
