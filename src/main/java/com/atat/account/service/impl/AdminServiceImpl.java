package com.atat.account.service.impl;

import com.atat.account.dao.AdminDao;
import com.atat.account.service.AdminService;
import com.atat.common.util.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public void  addAdmin(Map<String, Object> param) {
        adminDao.addAdmin(param);
    }

    @Override
    public void  updateAdminById(Map<String, Object> param) {
        adminDao.updateAdminById(param);
    }

    @Override
    public List<Map<String, Object>> selectAdminList(Map<String, Object> param) {
        return adminDao.selectAdminList(param);
    }

    @Override
    public PageInfo<Map<String, Object>> getAdminPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;

        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String,Object>> list = adminDao.selectAdminList(param);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
        return page;
    }

    @Override
    public Map<String, Object> getAdminById(Integer adminId) {
        Map<String, Object> admininfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("adminId", adminId);
        List<Map<String, Object>> adminList = adminDao.selectAdminList(rs);
        if ((null != adminList) && (adminList.size() > 0)) {
            admininfo = adminList.get(0);
        }
        return admininfo;
    }

    @Override
    public void delAdminById(Integer adminId) {
        adminDao.delAdminById(adminId);
    }

    @Override public Long adminLogin(String mobelPhone, String password) {
        Map<String, Object> loginRes = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mobelPhone",mobelPhone);
        param.put("password",password);
        List<Map<String, Object>> adminList = adminDao.selectAdminList(param);
        if (CollectionUtil.isNotEmpty(adminList)){
            //            Map<String, Object> customer = adminList.get(0);
            //            //绑定wxId
            //            Map<String, Object> updateParam = new HashMap<String, Object>();
            //            updateParam.put("CustomerId",customer.get("id"));
            //            updateParam.put("WxId",wxId);
            //            customerMapper.updateCustomerById(param);
            Long adminId = (Long) adminList.get(0).get("adminId");
            return adminId;
        } else {
            return 0L;
        }
    }
}
