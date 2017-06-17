/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.device.controller;

import com.alibaba.druid.sql.visitor.functions.If;
import com.atat.common.base.controller.BaseController;
import com.atat.common.util.CollectionUtil;
import com.atat.common.util.DateUtil;
import com.atat.common.util.StringUtil;
import com.atat.device.service.DeviceService;
import com.atat.device.service.GatewayService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ligw
 * @version $Id TestController.java, v 0.1 2017-05-31 18:39 ligw Exp $$
 */
@Controller
@RequestMapping(value = "client/home")
public class HomeController extends BaseController {

    @Resource
    private GatewayService gatewayService;

    @Resource
    private DeviceService deviceService;

    /**
     * 首页
     * 
     * @return
     */
    @RequestMapping(params = "action=index")
    public ModelAndView clientIndex() {
        ModelAndView mav = new ModelAndView("client/home/index");
        return mav;
    }

    /**
     * 个人中心页面
     * 
     * @return
     */
    @RequestMapping(params = "action=personal")
    public ModelAndView clientPersonal() {
        ModelAndView mav = new ModelAndView("client/home/personal");
        return mav;
    }

    /**
     * 设别列表页面
     * 
     * @return
     */
    @RequestMapping(params = "action=deviceList")
    public ModelAndView clientDeviceList() {
        ModelAndView mav = new ModelAndView("client/home/deviceList");
        return mav;
    }

    /**
     * 依据客户ID获取网关列表
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "action=getGatewayListByCustomerId")
    public void getGatewayListByCustomerId(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String customerId = request.getParameter("CustomerId");
        if (StringUtil.isNotEmpty(customerId)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("CustomerId", customerId);
            try {
                List<Map<String, Object>> gatewayList = gatewayService.selectGatewayList(param);
                resultMap.put("result", "success");
                resultMap.put("operationResult", gatewayList);
            }
            catch (Exception e) {
                logger.error("依据客户ID获取网关列表" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "用户Id不能为空");
        }
        this.renderJson(response, resultMap);
    }

    /**
     * 为用户添加网关
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=addGatewayForCustomer")
    public void addGatewayForCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String customerId = request.getParameter("CustomerId");
        String gatewayId = request.getParameter("GatewayId");
        if ((StringUtil.isNotEmpty(customerId)) && (StringUtil.isNotEmpty(gatewayId))) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("CustomerId", customerId);
            param.put("GatewayId", gatewayId);
            try {
                gatewayService.addCustomerGatewayRel(param);
                resultMap.put("result", "success");
            }
            catch (Exception e) {
                logger.error(" 为用户添加网关出错" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "用户Id及网关Id均不能为空");
        }
        this.renderJson(response, resultMap);
    }

    /**
     * 用户删除网关
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=delGatewayForCustomer")
    public void delGatewayForCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String customerId = request.getParameter("CustomerId");
        String gatewayId = request.getParameter("GatewayId");
        if (StringUtil.isNotEmpty(customerId)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("customerId", customerId);
            param.put("GatewayId", gatewayId);
            try {
                gatewayService.addCustomerGatewayRel(param);
                resultMap.put("result", "success");
            }
            catch (Exception e) {
                logger.error(" 用户删除网关出错" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "用户Id及网关Id均不能为空");
        }
        this.renderJson(response, resultMap);
    }

    /**
     * 添加网关
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=addGateway")
    public void addGateway(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String gatewayPort = request.getParameter("GatewayPort");
        String ip = request.getParameter("IP");
        String address = request.getParameter("Address");
        if ((StringUtil.isNotEmpty(gatewayPort)) && (StringUtil.isNotEmpty(ip)) && (StringUtil.isNotEmpty(address))) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("GatewayPort", Integer.parseInt(gatewayPort));
            param.put("IP", ip);
            param.put("Address", address);
            try {
                gatewayService.addGateway(param);
                resultMap.put("result", "success");
            }
            catch (Exception e) {
                logger.error("添加网关出错" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "端口、Ip网关地址均不能为空");
        }
        this.renderJson(response, resultMap);
    }

    /**
     * 更新网关
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=updateGateway")
    public void updateGateway(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String gatewayId = request.getParameter("GatewayId");
        String gatewayPort = request.getParameter("GatewayPort");
        String ip = request.getParameter("IP");
        String address = request.getParameter("Address");
        if (StringUtil.isNotEmpty(gatewayId)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("GatewayId", gatewayId);
            if (StringUtil.isNotEmpty(gatewayPort)) {
                param.put("GatewayPort", Integer.parseInt(gatewayPort));
            }
            if (StringUtil.isNotEmpty(ip)) {
                param.put("IP", ip);
            }
            if (StringUtil.isNotEmpty(address)) {
                param.put("Address", address);
            }
            try {
                gatewayService.updateGatewayByID(param);
                resultMap.put("result", "success");
            }
            catch (Exception e) {
                logger.error("更新网关出错" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "网关Id不能为空");
        }
        this.renderJson(response, resultMap);
    }

    /**
     * 依据网关ID删除网关
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=delGateway")
    public void delGateway(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String gatewayId = request.getParameter("GatewayId");
        if (StringUtil.isNotEmpty(gatewayId)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("GatewayId", gatewayId);
            param.put("IsDeleted", 1);
            try {
                gatewayService.updateGatewayByID(param);
                resultMap.put("result", "success");
            }
            catch (Exception e) {
                logger.error("依据网关ID删除网关出错" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "网关Id不能为空");
        }
        this.renderJson(response, resultMap);
    }

    /**
     * 依据网关Id获取设备列表
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=getDeviceListByGatewayId")
    public void getDeviceListByGatewayId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String deviceGetwayId = request.getParameter("deviceGetwayId");
        if (StringUtil.isNotEmpty(deviceGetwayId)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("deviceGetwayId", deviceGetwayId);
            try {
                List<Map<String, Object>> deviceList = deviceService.selectDeviceList(param);
                resultMap.put("result", "success");
                resultMap.put("operationResult", deviceList);
            }
            catch (Exception e) {
                logger.error("依据网关Id获取设备列表" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "网关Id为空");
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
        String deviceTypeID = request.getParameter("DeviceTypeId");
        String deviceNo = request.getParameter("DeviceNo");
        String state = request.getParameter("State");
        String deviceData = request.getParameter("DeviceData");
        String getwayId = request.getParameter("GetwayId");
        String name = request.getParameter("Name");
        if (StringUtil.isNotEmpty(deviceTypeID)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("DeviceTypeId", Integer.parseInt(deviceTypeID));
            if (StringUtil.isNotEmpty(deviceNo)) {
                param.put("DeviceNo", deviceNo);
            }
            if (StringUtil.isNotEmpty(state)) {
                param.put("State", Integer.parseInt(state));
            }
            if (StringUtil.isNotEmpty(deviceData)) {
                param.put("DeviceData", deviceData);
            }
            if (StringUtil.isNotEmpty(getwayId)) {
                param.put("GetwayId", Integer.parseInt(getwayId));
            }
            if (StringUtil.isNotEmpty(name)) {
                param.put("Name", name);
            }
            try {
                deviceService.addDevice(param);
                resultMap.put("result", "success");
            }
            catch (Exception e) {
                logger.error("添加设备出错" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "设备类型为空");
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
        String deviceId = request.getParameter("DeviceId");
        String deviceTypeID = request.getParameter("DeviceTypeId");
        String deviceNo = request.getParameter("DeviceNo");
        String state = request.getParameter("State");
        String deviceData = request.getParameter("DeviceData");
        String getwayId = request.getParameter("GetwayId");
        String name = request.getParameter("Name");
        if (StringUtil.isNotEmpty(deviceId)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("DeviceId", deviceId);
            if (StringUtil.isNotEmpty(deviceTypeID)) {
                param.put("DeviceTypeId", deviceTypeID);
            }
            if (StringUtil.isNotEmpty(deviceNo)) {
                param.put("DeviceNo", deviceNo);
            }
            if (StringUtil.isNotEmpty(state)) {
                param.put("State", state);
            }
            if (StringUtil.isNotEmpty(deviceData)) {
                param.put("DeviceData", deviceData);
            }
            if (StringUtil.isNotEmpty(getwayId)) {
                param.put("GetwayId", getwayId);
            }
            if (StringUtil.isNotEmpty(name)) {
                param.put("Name", name);
            }
            try {
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
        String deviceId = request.getParameter("DeviceId");
        if (StringUtil.isNotEmpty(deviceId)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("DeviceId", deviceId);
            param.put("IsDeleted", 1);
            try {
                deviceService.updateDeviceById(param);
                resultMap.put("result", "success");
            }
            catch (Exception e) {
                logger.error("依据设备Id删除设备" + e, e);
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
     * 获取设备当天数据
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=getDeviceTodayData")
    public void getDeviceTodayData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String DeviceId = request.getParameter("DeviceId");
        if (StringUtil.isNotEmpty(DeviceId)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("DeviceId", DeviceId);
            param.put("recordTimeStart", DateUtil.getCurrentTimeString(DateUtil.DatePattern.YYYYMMDD.getValue(), new Date()));
            param.put("recordTimeEnd", new Date());
            try {
                List<Map<String, Object>> deviceTodayDataList = deviceService.selectDeviceDataList(param);
                resultMap.put("result", "success");
                resultMap.put("operationResult", deviceTodayDataList);
            }
            catch (Exception e) {
                logger.error("获取设备当天数据出错" + e, e);
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
     * 获取设备当月数据
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=getDeviceMonthData")
    public void getDeviceMonthData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String DeviceId = request.getParameter("DeviceId");
        if (StringUtil.isNotEmpty(DeviceId)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("DeviceId", DeviceId);
            param.put("recordTimeStart", DateUtil.getCurrentTimeString("yyyy-MM", new Date()));
            param.put("recordTimeEnd", new Date());
            try {
                List<Map<String, Object>> deviceMonthData = deviceService.selectDeviceDailyDataList(param);
                resultMap.put("result", "success");
                resultMap.put("operationResult", deviceMonthData);
            }
            catch (Exception e) {
                logger.error("获取设备当月数据出错" + e, e);
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
     * 获取数据当年数据
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=getDeviceThenYearData")
    public void getDeviceThenYearData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String DeviceId = request.getParameter("DeviceId");
        if (StringUtil.isNotEmpty(DeviceId)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("DeviceId", DeviceId);
            param.put("recordTimeStart", DateUtil.getCurrentTimeString("yyyy", new Date()));
            param.put("recordTimeEnd", new Date());
            try {
                List<Map<String, Object>> deviceThenYearDataList = deviceService.selectDeviceMonthDataList(param);
                resultMap.put("result", "success");
                resultMap.put("operationResult", deviceThenYearDataList);
            }
            catch (Exception e) {
                logger.error("获取设备当月数据出错" + e, e);
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
     * 添加设备类型
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=addDeviceType")
    public void addDeviceType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String name = request.getParameter("Name");
        String model = request.getParameter("Model");
        String attention = request.getParameter("Attention");
        String describtion = request.getParameter("Describtion");
        if ((StringUtil.isNotEmpty(name)) && (StringUtil.isNotEmpty(model))) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("Name", name);
            param.put("Model", model);
            if (StringUtil.isNotEmpty(attention)) {
                param.put("Attention", attention);
            }
            if (StringUtil.isNotEmpty(describtion)) {
                param.put("Describtion", describtion);
            }
            try {
                deviceService.addDeviceType(param);
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
            resultMap.put("error", "设备类型名称或设备类型型号为空");
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
    @RequestMapping(params = "action=updateDeviceType")
    public void updateDeviceType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String deviceTypeId = request.getParameter("DeviceTypeId");
        String name = request.getParameter("Name");
        String model = request.getParameter("Model");
        String attention = request.getParameter("Attention");
        String describtion = request.getParameter("Describtion");
        if (StringUtil.isNotEmpty(deviceTypeId)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("DeviceTypeId", deviceTypeId);
            if (StringUtil.isNotEmpty(name)) {
                param.put("Name", name);
            }
            if (StringUtil.isNotEmpty(model)) {
                param.put("Model", model);
            }
            if (StringUtil.isNotEmpty(attention)) {
                param.put("Attention", attention);
            }
            if (StringUtil.isNotEmpty(describtion)) {
                param.put("Describtion", describtion);
            }
            try {
                deviceService.updateDeviceTypeByID(param);
                resultMap.put("result", "success");
            } catch (Exception e) {
                logger.error("依据Id更新设备类型出错" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        } else {
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
    @RequestMapping(params = "action=delDeviceType")
    public void delDeviceType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String deviceTypeId = request.getParameter("DeviceTypeId");
        if (StringUtil.isNotEmpty(deviceTypeId)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("DeviceTypeId", deviceTypeId);
            param.put("IsDeleted", 1);
            try {
                deviceService.updateDeviceTypeByID(param);
                resultMap.put("result", "success");
            } catch (Exception e) {
                logger.error("依据Id删除设备类型出错" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        } else {
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
    @RequestMapping(params = "action=getDeviceTypeList")
    public void getDeviceTypeList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String name = request.getParameter("Name");
        String attention = request.getParameter("Attention");
        String model = request.getParameter("Model");
        String describtion = request.getParameter("Describtion");
        String deviceTypeId = request.getParameter("DeviceTypeId");
        Map<String, Object> param = new HashMap<String, Object>();
        if (StringUtil.isNotEmpty(name)) {
            param.put("Name", name);
        }
        if (StringUtil.isNotEmpty(model)) {
            param.put("Model", model);
        }
        if (StringUtil.isNotEmpty(attention)) {
            param.put("Attention", attention);
        }
        if (StringUtil.isNotEmpty(describtion)) {
            param.put("Describtion", describtion);
        }
        if (StringUtil.isNotEmpty(deviceTypeId)) {
            param.put("DeviceTypeId", deviceTypeId);
        }
        try {
            List<Map<String, Object>> deviceTypeList = deviceService.selectDeviceType(param);
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
