package com.atat.account.service;

import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface AdminService {

    /**
     * 添加
     * @param param
     */
    public void  addAdmin(Map<String, Object> param);

    /**
     * 依据主键更新
     * @param param
     */
    public void  updateAdminById(Map<String, Object> param);

    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectAdminList(Map<String, Object> param);

    /**
     * 依据条件查找分页列表
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String, Object>> getAdminPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize);

    /**
     * 依据Id查找详情
     * @param adminId
     * @return
     */
    public Map<String, Object> getAdminById(Integer adminId);

    /**
     * 依据Id删除记录
     * @param adminId
     */
    public void delAdminById(Integer adminId);

    /**
     * 管理员登录较验
     * @param mobelPhone
     * @param password
     * @return
     */
    public Long adminLogin(String mobelPhone,String password);
}
