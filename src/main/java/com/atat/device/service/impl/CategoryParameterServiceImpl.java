package com.atat.device.service.impl;

import com.atat.device.dao.CategoryParameterDao;
import com.atat.device.service.CategoryParameterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CategoryParameterServiceImpl implements CategoryParameterService {

    @Autowired
    private CategoryParameterDao categoryParameterDao;

    @Override
    public void  addCategoryParameter(Map<String, Object> param) {
        categoryParameterDao.addCategoryParameter(param);
    }

    @Override
    public void  updateCategoryParameterById(Map<String, Object> param) {
        categoryParameterDao.updateCategoryParameterById(param);
    }

    @Override
    public List<Map<String, Object>> selectCategoryParameterList(Map<String, Object> param) {
        return categoryParameterDao.selectCategoryParameterList(param);
    }

    @Override
    public PageInfo<Map<String, Object>> getCategoryParameterPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;

        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String,Object>> list = categoryParameterDao.selectCategoryParameterList(param);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
        return page;
    }

    @Override
    public Map<String, Object> getCategoryParameterById(Long categoryParameterId) {
        Map<String, Object> categoryParameterinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("categoryParameterId", categoryParameterId);
        List<Map<String, Object>> categoryParameterList = categoryParameterDao.selectCategoryParameterList(rs);
        if ((null != categoryParameterList) && (categoryParameterList.size() > 0)) {
            categoryParameterinfo = categoryParameterList.get(0);
        }
        return categoryParameterinfo;
    }

    @Override
    public void delCategoryParameterById(Long categoryParameterId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("isDeleted",1);
        param.put("categoryParameterId", categoryParameterId);
        categoryParameterDao.updateCategoryParameterById(param);
    }

}
