package com.atat.property.service;

import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface PropertyMapService {

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
     * 依据条件查找分页列表
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String, Object>> getPropertyMapPageTurn(Map<String, Object> param, Integer pageNo,
            Integer pageSize);

    /**
     * 依据Id查找详情
     * @param propertyMapId
     * @return
     */
    public Map<String, Object> getPropertyMapById(String propertyMapId);

    /**
     * 依据Id删除记录
     * @param propertyMapId
     */
    public void delPropertyMapById(String propertyMapId);
}
