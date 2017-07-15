package com.atat.device.service;

import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface DeviceDataWeekService {

    /**
     * 添加
     * @param param
     */
    public void  addDeviceDataWeek(Map<String, Object> param);


    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectDeviceDataWeekList(Map<String, Object> param);

    /**
     * 依据条件查找分页列表
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String, Object>> getDeviceDataWeekPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize);

    /**
     * 依据Id查找详情
     * @param deviceDataWeekId
     * @return
     */
    public Map<String, Object> getDeviceDataWeekById(Long deviceDataWeekId);

    /**
     * 依据Id删除记录
     * @param deviceDataWeekId
     */
    public void delDeviceDataWeekById(Long deviceDataWeekId);

    /**
     * 获取指定设备指定参数1年内变化
     * @param deviceId
     * @param code
     * @return
     */
    public Map<String, Object> getOneYearDeviceData(Long deviceId,String code);
}
