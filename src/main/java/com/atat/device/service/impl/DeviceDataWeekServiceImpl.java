package com.atat.device.service.impl;

import com.atat.common.util.CollectionUtil;
import com.atat.device.dao.CategoryParameterDao;
import com.atat.device.dao.DeviceCategoryDao;
import com.atat.device.dao.DeviceDao;
import com.atat.device.dao.DeviceDataWeekDao;
import com.atat.device.service.DeviceDataWeekService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeviceDataWeekServiceImpl implements DeviceDataWeekService {

    @Autowired
    private DeviceDataWeekDao deviceDataWeekDao;

    @Autowired
    private DeviceDao deviceDao;

    @Autowired
    private CategoryParameterDao categoryParameterDao;

    @Override
    public void  addDeviceDataWeek(Map<String, Object> param) {
        deviceDataWeekDao.addDeviceDataWeek(param);
    }

    @Override
    public List<Map<String, Object>> selectDeviceDataWeekList(Map<String, Object> param) {
        return deviceDataWeekDao.selectDeviceDataWeekList(param);
    }

    @Override
    public PageInfo<Map<String, Object>> getDeviceDataWeekPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;

        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String,Object>> list = deviceDataWeekDao.selectDeviceDataWeekList(param);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
        return page;
    }

    @Override
    public Map<String, Object> getDeviceDataWeekById(Long deviceDataWeekId) {
        Map<String, Object> deviceDataWeekinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("deviceDataWeekId", deviceDataWeekId);
        List<Map<String, Object>> deviceDataWeekList = deviceDataWeekDao.selectDeviceDataWeekList(rs);
        if ((null != deviceDataWeekList) && (deviceDataWeekList.size() > 0)) {
            deviceDataWeekinfo = deviceDataWeekList.get(0);
        }
        return deviceDataWeekinfo;
    }

    @Override
    public void delDeviceDataWeekById(Long deviceDataWeekId) {
        deviceDataWeekDao.delDeviceDataWeekById(deviceDataWeekId);
    }

    @Override public Map<String, Object> getOneYearDeviceData(Long deviceId, String code) {
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
                cal.add(Calendar.YEAR, -1);
                param_sdd.put("recordTimeStart", cal.getTime().getTime());
                List<Map<String, Object>> deviceThreeHourData = deviceDataWeekDao.selectDeviceDataWeekList(param_sdd);
                categoryParameter.put("deviceEchartsData", deviceThreeHourData);
            }
        }
        return categoryParameter;
    }
}
