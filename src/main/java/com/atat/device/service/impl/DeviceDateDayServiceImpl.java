package com.atat.device.service.impl;

import com.atat.device.dao.DeviceDateDayDao;
import com.atat.device.service.DeviceDateDayService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DeviceDateDayServiceImpl implements DeviceDateDayService {

    @Autowired
    private DeviceDateDayDao deviceDateDayDao;

    @Override
    public void  addDeviceDateDay(Map<String, Object> param) {
        deviceDateDayDao.addDeviceDateDay(param);
    }

    @Override
    public void  updateDeviceDateDayById(Map<String, Object> param) {
        deviceDateDayDao.updateDeviceDateDayById(param);
    }

    @Override
    public List<Map<String, Object>> selectDeviceDateDayList(Map<String, Object> param) {
        return deviceDateDayDao.selectDeviceDateDayList(param);
    }

    @Override
    public PageInfo<Map<String, Object>> getDeviceDateDayPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;

        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String,Object>> list = deviceDateDayDao.selectDeviceDateDayList(param);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
        return page;
    }

    @Override
    public Map<String, Object> getDeviceDateDayById(String deviceDateDayId) {
        Map<String, Object> deviceDateDayinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("deviceDateDayId", deviceDateDayId);
        List<Map<String, Object>> deviceDateDayList = deviceDateDayDao.selectDeviceDateDayList(rs);
        if ((null != deviceDateDayList) && (deviceDateDayList.size() > 0)) {
            deviceDateDayinfo = deviceDateDayList.get(0);
        }
        return deviceDateDayinfo;
    }

    @Override
    public void delDeviceDateDayById(String deviceDateDayId) {
        deviceDateDayDao.delDeviceDateDayById(deviceDateDayId);
    }

}
