package com.atat.device.service.impl;

import com.atat.common.util.CollectionUtil;
import com.atat.device.dao.CategoryParameterDao;
import com.atat.device.dao.DeviceCategoryDao;
import com.atat.device.dao.DeviceDao;
import com.atat.device.dao.DeviceDataHourDao;
import com.atat.device.service.DeviceDataHourService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeviceDataHourServiceImpl implements DeviceDataHourService {

    @Autowired
    private DeviceDataHourDao deviceDataHourDao;

    @Autowired
    private DeviceDao deviceDao;

    @Autowired
    private CategoryParameterDao categoryParameterDao;

    @Override
    public void  addDeviceDataHour(Map<String, Object> param) {
        deviceDataHourDao.addDeviceDataHour(param);
    }


    @Override
    public List<Map<String, Object>> selectDeviceDataHourList(Map<String, Object> param) {
        return deviceDataHourDao.selectDeviceDataHourList(param);
    }

    @Override
    public PageInfo<Map<String, Object>> getDeviceDataHourPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;

        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String,Object>> list = deviceDataHourDao.selectDeviceDataHourList(param);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
        return page;
    }

    @Override
    public Map<String, Object> getDeviceDataHourById(String deviceDataHourId) {
        Map<String, Object> deviceDataHourinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("deviceDataHourId", deviceDataHourId);
        List<Map<String, Object>> deviceDataHourList = deviceDataHourDao.selectDeviceDataHourList(rs);
        if ((null != deviceDataHourList) && (deviceDataHourList.size() > 0)) {
            deviceDataHourinfo = deviceDataHourList.get(0);
        }
        return deviceDataHourinfo;
    }

    @Override
    public void delDeviceDataHourById(String deviceDataHourId) {
        deviceDataHourDao.delDeviceDataHourById(deviceDataHourId);
    }

    @Override public Map<String, Object> getOneDayDeviceData(Long deviceId, String code) {
        //查找设备参数Id
        Map<String, Object> categoryParameter = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("deviceId", deviceId);
        List<Map<String, Object>> deviceList = deviceDao.selectDeviceList(param);
        if ((null != deviceList) && (deviceList.size() > 0)) {
            Map<String, Object> device = deviceList.get(0);
            Long deviceCategoryId = (Long) device.get("deviceCategoryId");
            // 依据设备类型和属性code查找设备参数ID
            Map<String, Object> param_secp = new HashMap<String, Object>();
            param_secp.put("deviceCategoryId", deviceCategoryId);
            param_secp.put("code", code);
            if (CollectionUtil.isNotEmpty((List<Map<String, Object>>) categoryParameterDao.selectCategoryParameterList(param_secp))) {
                categoryParameter = categoryParameterDao.selectCategoryParameterList(param_secp).get(0);
                Long categoryParameterId = (Long) categoryParameter.get("categoryParameterId");
                Map<String, Object> param_sdd = new HashMap<String, Object>();
                param_sdd.put("categoryParameterId", categoryParameterId);
                param_sdd.put("deviceId", deviceId);
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DATE, -1);
                param_sdd.put("recordTimeStart", cal.getTime());
                List<Map<String, Object>> deviceThreeHourData = deviceDataHourDao.selectDeviceDataHourList(param);
                categoryParameter.put("deviceThreeHourData", deviceThreeHourData);
            }
        }
        return categoryParameter;
    }
}
