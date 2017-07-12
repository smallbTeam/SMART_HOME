package com.atat.account.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface AdminDao {

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
     * 依据主键删除记录
     * @param adminId
     */
    public void  delAdminById(Integer adminId);

}
