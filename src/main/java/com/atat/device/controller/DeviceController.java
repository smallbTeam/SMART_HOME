/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.device.controller;

import com.atat.common.base.controller.BaseController;
import com.atat.common.util.CollectionUtil;
import com.atat.common.util.StringUtil;
import com.atat.device.service.DeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
public class DeviceController extends BaseController {

    @Resource
    private DeviceService deviceService;

    /**
     * 设备列表页面
     * 
     * @return
     */
    @RequestMapping(params = "action=deviceList")
    public ModelAndView clientDeviceList() {
        ModelAndView mav = new ModelAndView("detail");
        return mav;
    }

    /**
     * 设备图表页面
     *
     * @return
     */
    @RequestMapping(params = "action=chartDetail")
    public ModelAndView deviceDataChart(HttpServletRequest request, HttpServletResponse response) {
        String deviceId = request.getParameter("deviceId");
        String code = request.getParameter("code");
        ModelAndView mav = new ModelAndView("chartDetail");
        mav.addObject("deviceId",deviceId);
        mav.addObject("code",code);
        return mav;
    }

    /**
     * 依据网关设备序列号获取设备列表
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=getDeviceListByGatewayId")
    public void getDeviceListByGatewayId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String gatewaySerialNumber = request.getParameter("gatewaySerialNumber");
        if (StringUtil.isNotEmpty(gatewaySerialNumber)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("gatewaySerialNumber", gatewaySerialNumber);
            try {
                List<Map<String, Object>> deviceList = deviceService.selectDeviceList(param);
                resultMap.put("result", "success");
                resultMap.put("operationResult", deviceList);
            }
            catch (Exception e) {
                logger.error("依据网关设备序列号获取设备列表" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "网关设备序列号为空");
        }
        this.renderJson(response, resultMap);
    }

    /**
     * 依据设备Id获取设备(包含设备参数)
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=getDeviceByDeviceId")
    public void getDeviceByDeviceId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String deviceId = request.getParameter("deviceId");
        if (StringUtil.isNotEmpty(deviceId)) {
                try {
                Map<String, Object> device = deviceService.getDeviceById(Long.parseLong(deviceId));
                resultMap.put("result", "success");
                resultMap.put("operationResult", device);
            }
            catch (Exception e) {
                logger.error("依据设备Id获取设备" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "设备Id为空");
        }
        this.renderJson(response, resultMap);
    }

    /**
     * 添加设备
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=addDevice")
    public void addDevice(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String deviceCategoryId = request.getParameter("deviceCategoryId");
        String gatewaySerialNumber = request.getParameter("gatewaySerialNumber");
        String seriaNumber = request.getParameter("seriaNumber");
        String state = request.getParameter("State");
        String parentId = request.getParameter("parentId");
        String name = request.getParameter("name");
        if (StringUtil.isNotEmpty(deviceCategoryId) && (StringUtil.isNotEmpty(gatewaySerialNumber)
        && (StringUtil.isNotEmpty(seriaNumber)))) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("deviceCategoryId", Long.parseLong(deviceCategoryId));
            param.put("seriaNumber", seriaNumber);
            param.put("gatewaySerialNumber", gatewaySerialNumber);
            if (StringUtil.isNotEmpty(state)) {
                param.put("State", Integer.parseInt(state));
            }
            if (StringUtil.isNotEmpty(parentId)) {
                param.put("parentId", Long.parseLong(parentId));
            }
            if (StringUtil.isNotEmpty(name)) {
                param.put("name", name);
            }
            try {
                deviceService.addDevice(param);
                resultMap.put("result", "success");
            } catch (Exception e) {
                logger.error("添加设备出错" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        } else {
            resultMap.put("result", "error");
            resultMap.put("error", "设备类型 设备所属网关 设备序号均不能为空");
        }
        this.renderJson(response, resultMap);
    }

    /**
     * 依据设备Id更新设备/给设备配置网关
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=updateDeviceById")
    public void updateDeviceById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String deviceId = request.getParameter("deviceId");
        String deviceCategoryId = request.getParameter("deviceCategoryId");
        String gatewaySerialNumber = request.getParameter("gatewaySerialNumber");
        String seriaNumber = request.getParameter("seriaNumber");
        String state = request.getParameter("State");
        String parentId = request.getParameter("parentId");
        String name = request.getParameter("name");
        if (StringUtil.isNotEmpty(deviceId)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("deviceId", deviceId);
            if (StringUtil.isNotEmpty(deviceCategoryId)) {
                param.put("deviceCategoryId", Long.parseLong(deviceCategoryId));
            }
            if (StringUtil.isNotEmpty(gatewaySerialNumber)) {
                param.put("gatewaySerialNumber", gatewaySerialNumber);
            }
            if (StringUtil.isNotEmpty(seriaNumber)) {
                param.put("seriaNumber", seriaNumber);
            }
            if (StringUtil.isNotEmpty(state)) {
                param.put("State", Integer.parseInt(state));
            }
            if (StringUtil.isNotEmpty(parentId)) {
                param.put("parentId", Long.parseLong(parentId));
            }
            if (StringUtil.isNotEmpty(name)) {
                param.put("name", name);
            } try {
                deviceService.updateDeviceById(param);
                resultMap.put("result", "success");
            }
            catch (Exception e) {
                logger.error("依据设备Id更新设备出错" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "设备Id为空");
        }
        this.renderJson(response, resultMap);
    }

    /**
     * 依据设备Id删除设备
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=delDeviceById")
    public void delDeviceById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String deviceId = request.getParameter("deviceId");
        if (StringUtil.isNotEmpty(deviceId)) {
            try {
                deviceService.delDeviceById(Long.parseLong(deviceId));
                resultMap.put("result", "success");
            }
            catch (Exception e) {
                logger.error("依据设备Id删除设备" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        } else {
            resultMap.put("result", "error");
            resultMap.put("error", "设备Id为空");
        }
        this.renderJson(response, resultMap);
    }

}
