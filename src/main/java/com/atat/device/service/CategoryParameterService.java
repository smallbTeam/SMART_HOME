package com.atat.device.service;

import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface CategoryParameterService {

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
     * 依据条件查找分页列表
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String, Object>> getCategoryParameterPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize);

    /**
     * 依据Id查找详情
     * @param categoryParameterId
     * @return
     */
    public Map<String, Object> getCategoryParameterById(Long categoryParameterId);

    /**
     * 依据Id删除记录
     * @param categoryParameterId
     */
    public void delCategoryParameterById(Long categoryParameterId);
}
