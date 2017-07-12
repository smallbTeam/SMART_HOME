/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.device.controller;

import com.atat.common.base.controller.BaseController;
import com.atat.common.util.StringUtil;
import com.atat.device.service.DeviceCategoryService;
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
public class DeviceCategoryController extends BaseController {

    @Resource
    private DeviceCategoryService deviceCategoryService;

   /**
     * 添加设备类型
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=addDeviceCategory")
    public void addDeviceCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String name = request.getParameter("name");
        String model = request.getParameter("model");
        String attention = request.getParameter("attention");
        String describtion = request.getParameter("describtion");
        String parentId = request.getParameter("parentId");
        if (StringUtil.isNotEmpty(model)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("model", model);
            if (StringUtil.isNotEmpty(name)) {
                param.put("name", name);
            }
            if (StringUtil.isNotEmpty(parentId)) {
                param.put("parentId", Long.parseLong(parentId));
            }
            if (StringUtil.isNotEmpty(attention)) {
                param.put("attention", attention);
            }
            if (StringUtil.isNotEmpty(describtion)) {
                param.put("describtion", describtion);
            }
            try {
                deviceCategoryService.addDeviceCategory(param);
                resultMap.put("result", "success");
            }
            catch (Exception e) {
                logger.error("添加设备类型出错" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "设备类型型号为空");
        }
        this.renderJson(response, resultMap);
    }

    /**
     * 依据Id更新设备类型
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=updateDeviceCategory")
    public void updateDeviceCategoryById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String deviceCategoryId = request.getParameter("deviceCategoryId");
        String name = request.getParameter("name");
        String model = request.getParameter("model");
        String attention = request.getParameter("attention");
        String describtion = request.getParameter("describtion");
        String parentId = request.getParameter("parentId");
        if (StringUtil.isNotEmpty(deviceCategoryId)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("deviceCategoryId", deviceCategoryId);
            if (StringUtil.isNotEmpty(model)) {
                param.put("model", model);
            }
            if (StringUtil.isNotEmpty(name)) {
                param.put("name", name);
            }
            if (StringUtil.isNotEmpty(parentId)) {
                param.put("parentId", Long.parseLong(parentId));
            }
            if (StringUtil.isNotEmpty(attention)) {
                param.put("attention", attention);
            }
            if (StringUtil.isNotEmpty(describtion)) {
                param.put("describtion", describtion);
            }
            try {
                deviceCategoryService.updateDeviceCategoryById(param);
                resultMap.put("result", "success");
            }
            catch (Exception e) {
                logger.error("依据Id更新设备类型出错" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "设备类型Id为空");
        }
        this.renderJson(response, resultMap);
    }

    /**
     * 依据Id删除设备类型
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=delDeviceCategory")
    public void delDeviceCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String deviceCategoryId = request.getParameter("deviceCategoryId");
        if (StringUtil.isNotEmpty(deviceCategoryId)) {
            try {
                deviceCategoryService.delDeviceCategoryById(Long.parseLong(deviceCategoryId));
                resultMap.put("result", "success");
            }
            catch (Exception e) {
                logger.error("依据Id删除设备类型出错" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "设备类型Id为空");
        }
        this.renderJson(response, resultMap);
    }

    /**
     * 依据条件获取设备类型列表
     * name、attention、describtion相似搜索 model匹配搜索
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=getDeviceCategoryList")
    public void getDeviceCategoryList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String deviceCategoryId = request.getParameter("deviceCategoryId");
        String name = request.getParameter("name");
        String model = request.getParameter("model");
        String attention = request.getParameter("attention");
        String describtion = request.getParameter("describtion");
        String parentId = request.getParameter("parentId");
        Map<String, Object> param = new HashMap<String, Object>();
        if (StringUtil.isNotEmpty(name)) {
            param.put("name", name);
        }
        if (StringUtil.isNotEmpty(model)) {
            param.put("model", model);
        }
        if (StringUtil.isNotEmpty(attention)) {
            param.put("attention", attention);
        }
        if (StringUtil.isNotEmpty(describtion)) {
            param.put("describtion", describtion);
        }
        if (StringUtil.isNotEmpty(deviceCategoryId)) {
            param.put("deviceCategoryId", Long.parseLong(deviceCategoryId));
        }
        if (StringUtil.isNotEmpty(parentId)) {
            param.put("parentId", Long.parseLong(parentId));
        }
        try {
            List<Map<String, Object>> deviceTypeList = deviceCategoryService.selectDeviceCategoryList(param);
            resultMap.put("result", "success");
            resultMap.put("operationResult", deviceTypeList);
        } catch (Exception e) {
            logger.error("依据条件获取设备类型列表出错" + e, e);
            resultMap.put("result", "failed");
            resultMap.put("error", "系统出错");
        }
        this.renderJson(response, resultMap);
    }

}
