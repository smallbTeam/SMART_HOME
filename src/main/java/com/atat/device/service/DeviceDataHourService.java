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
    public Map<String, Object> getDeviceDataHourById(Long deviceDataHourId);

    /**
     * 依据Id删除记录
     * @param deviceDataHourId
     */
    public void delDeviceDataHourById(Long deviceDataHourId);

    /**
     * 获取指定设备指定参数1天内变化
     * @param deviceId
     * @param code
     * @return
     */
    public Map<String, Object> getOneDayDeviceData(Long deviceId,String code);

    /**
     * 1天执行一次定时任务 1天前的数据清空  每天平均值存入天表数据库
     */
    public void timingFormateForOneDay();
}
