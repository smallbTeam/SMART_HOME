package com.atat.device.service;

import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface DeviceService {

    /**
     * 添加
     * @param param
     */
    public void  addDevice(Map<String, Object> param);

    /**
     * 依据主键更新
     * @param param
     */
    public void  updateDeviceById(Map<String, Object> param);

    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectDeviceList(Map<String, Object> param);

    /**
     * 依据条件查找分页列表
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String, Object>> getDevicePageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize);

    /**
     * 依据Id查找详情
     * @param deviceId
     * @return
     */
    public Map<String, Object> getDeviceById(Long deviceId);

    /**
     * 依据Id删除记录
     * @param deviceId
     */
    public void delDeviceById(Long deviceId);
}
