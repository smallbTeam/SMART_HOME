/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.device.controller;

import com.atat.common.base.controller.BaseController;
import com.atat.common.util.StringUtil;
import com.atat.device.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ligw
 * @version $Id TestController.java, v 0.1 2017-05-31 18:39 ligw Exp $$
 */
@Controller
@RequestMapping(value = "client/device")
public class CategoryParameterController extends BaseController {

    @Resource
    private CategoryParameterService categoryParameterService;

   /**
     * 添加设备类型关联参数
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=addCategoryParameter")
    public void addCategoryParameter(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String deviceCategoryId = request.getParameter("deviceCategoryId");
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String type = request.getParameter("type");
        String unit = request.getParameter("unit");
        String dataType = request.getParameter("dataType");
        String isReadOnly = request.getParameter("isReadOnly");
        if (StringUtil.isNotEmpty(deviceCategoryId) && StringUtil.isNotEmpty(name)
                && StringUtil.isNotEmpty(code) && StringUtil.isNotEmpty(type)
                && StringUtil.isNotEmpty(unit) && StringUtil.isNotEmpty(dataType)
                && StringUtil.isNotEmpty(isReadOnly)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("deviceCategoryId", Long.parseLong(deviceCategoryId));
            param.put("name", name);
            param.put("code", code);
            param.put("type", Integer.parseInt(type));
            param.put("unit", unit);
            param.put("dataType", Integer.parseInt(dataType));
            param.put("isReadOnly", Integer.parseInt(isReadOnly));
            try {
                categoryParameterService.addCategoryParameter(param);
                resultMap.put("result", "success");
            }
            catch (Exception e) {
                logger.error("添加设备类型关联参数出错" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "设备类型关联参数重要参数为空");
        }
        this.renderJson(response, resultMap);
    }

    /**
     * 依据Id更新设备类型关联参数
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=updateCategoryParameter")
    public void updateCategoryParameterById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String categoryParameterId = request.getParameter("categoryParameterId");
        String deviceCategoryId = request.getParameter("deviceCategoryId");
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String type = request.getParameter("type");
        String unit = request.getParameter("unit");
        String dataType = request.getParameter("dataType");
        String isReadOnly = request.getParameter("isReadOnly");
        if (StringUtil.isNotEmpty(categoryParameterId)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("categoryParameterId", categoryParameterId);
            if (StringUtil.isNotEmpty(deviceCategoryId)) {
            param.put("deviceCategoryId", Long.parseLong(deviceCategoryId));
            }
            if (StringUtil.isNotEmpty(name)) {
            param.put("name", name);
            }
            if (StringUtil.isNotEmpty(code)) {
            param.put("code", code);
            }
            if (StringUtil.isNotEmpty(type)) {
            param.put("type", Integer.parseInt(type));
            }
            if (StringUtil.isNotEmpty(unit)) {
            param.put("unit", unit);
            }
            if (StringUtil.isNotEmpty(dataType)) {
            param.put("dataType", Integer.parseInt(dataType));
            }
            if (StringUtil.isNotEmpty(isReadOnly)) {
                param.put("isReadOnly", Integer.parseInt(isReadOnly));
            }
            try {
                categoryParameterService.updateCategoryParameterById(param);
                resultMap.put("result", "success");
            }
            catch (Exception e) {
                logger.error("依据Id更新设备类型关联参数出错" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "设备类型关联参数Id为空");
        }
        this.renderJson(response, resultMap);
    }

    /**
     * 依据Id删除设备类型关联参数
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=delCategoryParameter")
    public void delCategoryParameter(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String categoryParameterId = request.getParameter("categoryParameterId");
        if (StringUtil.isNotEmpty(categoryParameterId)) {
            try {
                categoryParameterService.delCategoryParameterById(Long.parseLong(categoryParameterId));
                resultMap.put("result", "success");
            }
            catch (Exception e) {
                logger.error("依据Id删除设备类型关联参数出错" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "设备类型关联参数Id为空");
        }
        this.renderJson(response, resultMap);
    }

    /**
     * 依据条件获取设备类型关联参数列表
     * name、attention、describtion相似搜索 model匹配搜索
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=getCategoryParameterList")
    public void getCategoryParameterList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String categoryParameterId = request.getParameter("categoryParameterId");
        String deviceCategoryId = request.getParameter("deviceCategoryId");
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String type = request.getParameter("type");
        String unit = request.getParameter("unit");
        String dataType = request.getParameter("dataType");
        String isReadOnly = request.getParameter("isReadOnly");
        Map<String, Object> param = new HashMap<String, Object>();
        if (StringUtil.isNotEmpty(categoryParameterId)) {
            param.put("categoryParameterId", categoryParameterId);
        }
        if (StringUtil.isNotEmpty(deviceCategoryId)) {
            param.put("deviceCategoryId", Long.parseLong(deviceCategoryId));
        }
        if (StringUtil.isNotEmpty(name)) {
            param.put("name", name);
        }
        if (StringUtil.isNotEmpty(code)) {
            param.put("code", code);
        }
        if (StringUtil.isNotEmpty(type)) {
            param.put("type", Integer.parseInt(type));
        }
        if (StringUtil.isNotEmpty(unit)) {
            param.put("unit", unit);
        }
        if (StringUtil.isNotEmpty(dataType)) {
            param.put("dataType", Integer.parseInt(dataType));
        }
        if (StringUtil.isNotEmpty(isReadOnly)) {
            param.put("isReadOnly", Integer.parseInt(isReadOnly));
        }
        try {
            List<Map<String, Object>> categoryParameterTypeList = categoryParameterService.selectCategoryParameterList(param);
            resultMap.put("result", "success");
            resultMap.put("operationResult", categoryParameterTypeList);
        } catch (Exception e) {
            logger.error("依据条件获取设备类型关联参数列表出错" + e, e);
            resultMap.put("result", "failed");
            resultMap.put("error", "系统出错");
        }
        this.renderJson(response, resultMap);
    }

}
