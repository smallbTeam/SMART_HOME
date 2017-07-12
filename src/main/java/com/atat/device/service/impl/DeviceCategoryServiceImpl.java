package com.atat.device.service.impl;

import com.atat.device.dao.DeviceCategoryDao;
import com.atat.device.service.DeviceCategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DeviceCategoryServiceImpl implements DeviceCategoryService {

    @Autowired
    private DeviceCategoryDao deviceCategoryDao;

    @Override
    public void  addDeviceCategory(Map<String, Object> param) {
        deviceCategoryDao.addDeviceCategory(param);
    }

    @Override
    public void  updateDeviceCategoryById(Map<String, Object> param) {
        deviceCategoryDao.updateDeviceCategoryById(param);
    }

    @Override
    public List<Map<String, Object>> selectDeviceCategoryList(Map<String, Object> param) {
        return deviceCategoryDao.selectDeviceCategoryList(param);
    }

    @Override
    public PageInfo<Map<String, Object>> getDeviceCategoryPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;

        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String,Object>> list = deviceCategoryDao.selectDeviceCategoryList(param);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
        return page;
    }

    @Override
    public Map<String, Object> getDeviceCategoryById(Long deviceCategoryId) {
        Map<String, Object> deviceCategoryinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("deviceCategoryId", deviceCategoryId);
        List<Map<String, Object>> deviceCategoryList = deviceCategoryDao.selectDeviceCategoryList(rs);
        if ((null != deviceCategoryList) && (deviceCategoryList.size() > 0)) {
            deviceCategoryinfo = deviceCategoryList.get(0);
        }
        return deviceCategoryinfo;
    }

    @Override
    public void delDeviceCategoryById(Long deviceCategoryId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("isDeleted", 1);
        param.put("deviceCategoryId", deviceCategoryId);
        deviceCategoryDao.updateDeviceCategoryById(param);
    }

}
