package com.atat.device.service;

import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface DeviceDateNowService {

    /**
     * 添加
     * @param param
     */
    public void  addDeviceDateNow(Map<String, Object> param);

    /**
     * 依据主键更新
     * @param param
     */
    public void  updateDeviceDateNowById(Map<String, Object> param);

    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectDeviceDateNowList(Map<String, Object> param);

    /**
     * 依据条件查找分页列表
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String, Object>> getDeviceDateNowPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize);

    /**
     * 依据Id查找详情
     * @param deviceDateNowId
     * @return
     */
    public Map<String, Object> getDeviceDateNowById(String deviceDateNowId);

    /**
     * 依据Id删除记录
     * @param deviceDateNowId
     */
    public void delDeviceDateNowById(String deviceDateNowId);
}
