package com.atat.device.service.impl;

import com.atat.common.util.CollectionUtil;
import com.atat.device.dao.DeviceDataDayDao;
import com.atat.device.dao.DeviceDataWeekDao;
import com.atat.device.service.DeviceDataDayService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeviceDataDayServiceImpl implements DeviceDataDayService {

    @Autowired
    private DeviceDataDayDao deviceDataDayDao;

    @Autowired
    private DeviceDataWeekDao deviceDataWeekDao;

    @Override
    public void  addDeviceDataDay(Map<String, Object> param) {
        deviceDataDayDao.addDeviceDataDay(param);
    }

    @Override
    public List<Map<String, Object>> selectDeviceDataDayList(Map<String, Object> param) {
        return deviceDataDayDao.selectDeviceDataDayList(param);
    }

    @Override
    public PageInfo<Map<String, Object>> getDeviceDataDayPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;

        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String,Object>> list = deviceDataDayDao.selectDeviceDataDayList(param);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
        return page;
    }

    @Override
    public Map<String, Object> getDeviceDataDayById(Long deviceDataDayId) {
        Map<String, Object> deviceDataDayinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("deviceDataDayId", deviceDataDayId);
        List<Map<String, Object>> deviceDataDayList = deviceDataDayDao.selectDeviceDataDayList(rs);
        if ((null != deviceDataDayList) && (deviceDataDayList.size() > 0)) {
            deviceDataDayinfo = deviceDataDayList.get(0);
        }
        return deviceDataDayinfo;
    }

    @Override
    public void delDeviceDataDayById(Long deviceDataDayId) {
        deviceDataDayDao.delDeviceDataDayById(deviceDataDayId);
    }

    @Override public void timingFormateForOneWeek() {
        //分别计算前一周前的设备平均值的平均值
        List<Map<String, Object>> deviceDataList = new ArrayList<Map<String, Object>>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -7);
        Long recordTimeEnd = cal.getTime().getTime();
        cal.add(Calendar.DATE, -7);
        Long recordTimeStart = cal.getTime().getTime();
        Map<String, Object> param_day = new HashMap<String, Object>();
        param_day.put("recordTimeStart",recordTimeStart);
        param_day.put("recordTimeEnd",recordTimeEnd);
        deviceDataList.addAll(deviceDataDayDao.timingDayAverageData(param_day));
        //存入天表
        if (CollectionUtil.isNotEmpty(deviceDataList)){
            deviceDataWeekDao.addDeviceDataWeekList(deviceDataList);
        }
        //移除Hour表之前的数据
        cal.add(Calendar.DATE, 7);
        deviceDataDayDao.delDeviceDataDayByEndTime(cal.getTime().getTime());
    }
}
