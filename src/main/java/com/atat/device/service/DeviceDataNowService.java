package com.atat.device.service;

import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface DeviceDataNowService {

    /**
     * 添加
     * @param param
     */
    public void  addDeviceDataNow(Map<String, Object> param);


    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectDeviceDataNowList(Map<String, Object> param);

    /**
     * 依据条件查找分页列表
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String, Object>> getDeviceDataNowPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize);

    /**
     * 依据Id查找详情
     * @param deviceDataNowId
     * @return
     */
    public Map<String, Object> getDeviceDataNowById(Long deviceDataNowId);

    /**
     * 依据Id删除记录
     * @param deviceDataNowId
     */
    public void delDeviceDataNowById(Long deviceDataNowId);

    /**
     * 添加设备数据
     * @param param
     * @return
     */
    public Integer addDeviceData(Map<String, Object> param);

    /**
     * 获取指定设备指定参数3小时内变化
     * @param deviceId
     * @return
     */
    public Map<String, Object> getThreeHourDeviceData(Long deviceId,String code);

    /**
     * 3H执行一次定时任务 将3h之前的数据清空  每小时求平均值存入小时表数据库
     */
    public void timingFormateForThreeHour();
}
