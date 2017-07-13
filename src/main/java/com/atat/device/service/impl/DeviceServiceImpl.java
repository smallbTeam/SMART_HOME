package com.atat.device.service.impl;

import com.atat.common.util.CollectionUtil;
import com.atat.device.dao.CategoryParameterDao;
import com.atat.device.dao.DeviceDao;
import com.atat.device.dao.DeviceDataNowDao;
import com.atat.device.service.DeviceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceDao deviceDao;

    @Autowired
    private CategoryParameterDao categoryParameterDao;

    @Autowired
    private DeviceDataNowDao deviceDataNowDao;

    @Override
    public void addDevice(Map<String, Object> param) {
        deviceDao.addDevice(param);
    }

    @Override
    public void updateDeviceById(Map<String, Object> param) {
        deviceDao.updateDeviceById(param);
    }

    @Override
    public List<Map<String, Object>> selectDeviceList(Map<String, Object> param) {
        return deviceDao.selectDeviceList(param);
    }

    @Override
    public PageInfo<Map<String, Object>> getDevicePageTurn(Map<String, Object> param, Integer pageNo,
            Integer pageSize) {
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String, Object>> list = deviceDao.selectDeviceList(param);
        // 用PageInfo对结果进行包装
        PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(list);
        return page;
    }

    @Override
    public Map<String, Object> getDeviceById(Long deviceId) {
        Map<String, Object> deviceinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("deviceId", deviceId);
        List<Map<String, Object>> deviceList = deviceDao.selectDeviceList(rs);
        if ((null != deviceList) && (deviceList.size() > 0)) {
            deviceinfo = deviceList.get(0);
            // 依据设备Id查询设备参数
            // 查询设备对应设备类别的参数
            Long deviceCategoryId = (Long) deviceinfo.get("deviceCategoryId");
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("deviceCategoryId",deviceCategoryId);
            List<Map<String, Object>> deviceDataList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> categoryParameterList = categoryParameterDao.selectCategoryParameterList(param);
            if (CollectionUtil.isNotEmpty(categoryParameterList)){
                for (Map<String, Object> categoryParameter:categoryParameterList){
                    //依次查询设别信号值
                    Long categoryParameterId = (Long) categoryParameter.get("categoryParameterId");
                    Map<String, Object> param_dd = new HashMap<String, Object>();
                    param_dd.put("deviceId", deviceId);
                    param_dd.put("categoryParameterId", categoryParameterId);
                    param_dd.put("limit", 1);
                    List<Map<String, Object>> deviceDataNowList = (List<Map<String, Object>>)deviceDataNowDao.selectDeviceDataNowList(param_dd);
                    if (CollectionUtil.isNotEmpty(deviceDataNowList)){
                        Map<String, Object> deviceDataNow = deviceDataNowList.get(0);
                        categoryParameter.put("recordTime",deviceDataNow.get("recordTime"));
                        categoryParameter.put("value",deviceDataNow.get("value"));
                    }
                    deviceDataList.add(categoryParameter);
                }
            }
            deviceinfo.put("deviceDataList",deviceDataList);
        }
        return deviceinfo;
    }

    @Override
    public void delDeviceById(Long deviceId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("isDeleted", 1);
        param.put("deviceId", deviceId);
        deviceDao.updateDeviceById(param);
    }
}
