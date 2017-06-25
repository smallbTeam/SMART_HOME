package com.atat.property.service.impl;

import com.atat.property.dao.PropertyMapDao;
import com.atat.property.service.PropertyMapService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class PropertyMapServiceImpl implements PropertyMapService {

    @Autowired
    private PropertyMapDao propertyMapDao;

    @Override
    public void  addPropertyMap(Map<String, Object> param) {
        propertyMapDao.addPropertyMap(param);
    }

    @Override
    public void  updatePropertyMapByKey(Map<String, Object> param) {
        propertyMapDao.updatePropertyMapByKey(param);
    }

    @Override
    public List<Map<String, Object>> selectPropertyMapList(Map<String, Object> param) {
        return propertyMapDao.selectPropertyMapList(param);
    }

    @Override
    public PageInfo<Map<String, Object>> getPropertyMapPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;

        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String,Object>> list = propertyMapDao.selectPropertyMapList(param);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
        return page;
    }

    @Override
    public Map<String, Object> getPropertyMapByKey(String propertyMapKey) {
        Map<String, Object> propertyMapinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("propertyMapKey", propertyMapKey);
        List<Map<String, Object>> propertyMapList = propertyMapDao.selectPropertyMapList(rs);
        if ((null != propertyMapList) && (propertyMapList.size() > 0)) {
            propertyMapinfo = propertyMapList.get(0);
        }
        return propertyMapinfo;
    }

    @Override
    public void delPropertyMapByKey(String propertyMapKey) {
        propertyMapDao.delPropertyMapByKey(propertyMapKey);
    }

}
