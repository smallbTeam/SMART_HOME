package com.atat.device.service;

import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface DeviceDataDayService {

    /**
     * 添加
     * @param param
     */
    public void  addDeviceDataDay(Map<String, Object> param);

    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectDeviceDataDayList(Map<String, Object> param);

    /**
     * 依据条件查找分页列表
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String, Object>> getDeviceDataDayPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize);

    /**
     * 依据Id查找详情
     * @param deviceDataDayId
     * @return
     */
    public Map<String, Object> getDeviceDataDayById(String deviceDataDayId);

    /**
     * 依据Id删除记录
     * @param deviceDataDayId
     */
    public void delDeviceDataDayById(String deviceDataDayId);
}
