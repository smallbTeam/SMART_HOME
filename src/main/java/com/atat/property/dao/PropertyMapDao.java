package com.atat.property.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface PropertyMapDao {

    /**
     * 添加
     * @param param
     */
    public void  addPropertyMap(Map<String, Object> param);

    /**
     * 依据主键更新
     * @param param
     */
    public void  updatePropertyMapById(Map<String, Object> param);

    /**
     * 依据条件查找列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectPropertyMapList(Map<String, Object> param);

    /**
     * 依据主键删除记录
     * @param propertyMapId
     */
    public void  delPropertyMapById(String propertyMapId);

}
