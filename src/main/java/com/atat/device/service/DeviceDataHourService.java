package com.atat.device.service;

import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface DeviceDataHourService {

    /**
     * 添加
     * @param param
     */
    public void  addDeviceDataHour(Map<String, Object> param);


    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectDeviceDataHourList(Map<String, Object> param);

    /**
     * 依据条件查找分页列表
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String, Object>> getDeviceDataHourPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize);

    /**
     * 依据Id查找详情
     * @param deviceDataHourId
     * @return
     */
    public Map<String, Object> getDeviceDataHourById(String deviceDataHourId);

    /**
     * 依据Id删除记录
     * @param deviceDataHourId
     */
    public void delDeviceDataHourById(String deviceDataHourId);

    /**
     * 获取指定设备指定参数1天内变化
     * @param deviceId
     * @param code
     * @return
     */
    public Map<String, Object> getOneDayDeviceData(Long deviceId,String code);
}
