package com.atat.device.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface CategoryParameterDao {

    /**
     * 添加
     * @param param
     */
    public void  addCategoryParameter(Map<String, Object> param);

    /**
     * 依据主键更新
     * @param param
     */
    public void  updateCategoryParameterById(Map<String, Object> param);

    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectCategoryParameterList(Map<String, Object> param);

    /**
     * 依据主键删除记录
     * @param categoryParameterId
     */
    public void  delCategoryParameterById(Long categoryParameterId);

}
