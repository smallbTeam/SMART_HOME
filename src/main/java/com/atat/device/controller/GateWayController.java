/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.device.controller;

import com.atat.account.service.CustomerService;
import com.atat.common.base.controller.BaseController;
import com.atat.common.prop.BasePropertyDate;
import com.atat.common.util.StringUtil;
import com.atat.device.service.GatewayService;
import com.atat.device.service.RelCustomerGatewayService;
import com.atat.property.action.GetSignatureUrl;
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
public class GateWayController extends BaseController {

    @Resource
    private GatewayService gatewayService;

    @Resource
    private CustomerService customerService;

    @Resource
    private RelCustomerGatewayService relCustomerGatewayService;

    /**
     * 添加网管页面
     *
     * @return
     */
    @RequestMapping(params = "action=addGetway")
    public ModelAndView addGetway(HttpServletRequest request, HttpServletResponse response) {
        String mobelPhone = request.getParameter("mobelPhone");
        ModelAndView mav = new ModelAndView("addGetway");
        if (StringUtil.isNotEmpty(mobelPhone)) {
                mav.addObject("account", customerService.getCustomerByMobelPhone(mobelPhone));
        }
        GetSignatureUrl signatureUrl = new GetSignatureUrl();
        String mainurl = "http://s-357114.gotocdn.com/smart_home/client/device?action=addGetway&mobelPhone="+mobelPhone;
        Map<String, Object> map = signatureUrl.getSignature(mainurl);
        String appid = BasePropertyDate.WX_APPID;
        mav.addObject("appid", appid);
        mav.addObject("noncestr", map.get("noncestr"));
        mav.addObject("timestamp", map.get("timestamp"));
        mav.addObject("signaturet", map.get("signaturet"));
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
        String customerId = request.getParameter("customerId");
        if (StringUtil.isNotEmpty(customerId)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("customerId", customerId);
            try {
                List<Map<String, Object>> gatewayList = relCustomerGatewayService.selectRelCustomerGatewayList(param);
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
     * 依据条件获取网关列表
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=getGatewayList")
    public void getGatewayList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String port = request.getParameter("port");
        String ip = request.getParameter("ip");
        String address = request.getParameter("address");
        String serialNumber = request.getParameter("serialNumber");
        Map<String, Object> param = new HashMap<String, Object>();
        if (StringUtil.isNotEmpty(port)) {
            param.put("port", Integer.parseInt(port));
        }
        if (StringUtil.isNotEmpty(ip)) {
            param.put("ip", ip);
        }
        if (StringUtil.isNotEmpty(address)) {
            param.put("address", "%"+address+"%");
        }
        if (StringUtil.isNotEmpty(serialNumber)) {
            param.put("serialNumber", serialNumber);
        }
        try {
            List<Map<String, Object>> gatewayList = gatewayService.selectGatewayList(param);
            resultMap.put("result", "success");
            resultMap.put("operationResult", gatewayList);
        }
        catch (Exception e) {
            logger.error("获取网关列表" + e, e);
            resultMap.put("result", "failed");
            resultMap.put("error", "系统出错");
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
        String customerId = request.getParameter("customerId");
        String gatewaySerialNumber = request.getParameter("gatewaySerialNumber");
        String gatewayName = request.getParameter("gatewayName");
        if ((StringUtil.isNotEmpty(customerId)) && (StringUtil.isNotEmpty(gatewaySerialNumber))
                && (StringUtil.isNotEmpty(gatewayName))) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("customerId", Integer.parseInt(customerId));
            param.put("gatewaySerialNumber", gatewaySerialNumber);
            param.put("gatewayName", gatewayName);
            try {
                relCustomerGatewayService.addGatewayForCustomer(param);
                resultMap.put("result", "success");
            }
            catch (Exception e) {
                logger.error(" 为用户添加网关出错" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        } else {
            resultMap.put("result", "error");
            resultMap.put("error", "用户Id及网关名称均不能为空");
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
        String customerId = request.getParameter("customerId");
        String gatewaySerialNumber = request.getParameter("gatewaySerialNumber");
        if (StringUtil.isNotEmpty(customerId)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("customerId", customerId);
            param.put("gatewaySerialNumber", gatewaySerialNumber);
            try {
                relCustomerGatewayService.delGatewayForCustomer(param);
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
        String port = request.getParameter("port");
        String ip = request.getParameter("ip");
        String address = request.getParameter("address");
        String serialNumber = request.getParameter("serialNumber");
        if (StringUtil.isNotEmpty(serialNumber)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("serialNumber", serialNumber);
            if (StringUtil.isNotEmpty(port)){
                param.put("port", Integer.parseInt(port));
            }
            if (StringUtil.isNotEmpty(ip)){
                param.put("ip", ip);
            }
            if (StringUtil.isNotEmpty(ip)){
                param.put("address", address);
            }
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
            resultMap.put("error", "网关设备序列号不能为空");
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
    public void updateGatewayBySerialNumber(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String port = request.getParameter("port");
        String ip = request.getParameter("ip");
        String address = request.getParameter("address");
        String serialNumber = request.getParameter("serialNumber");
        if (StringUtil.isNotEmpty(serialNumber)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("serialNumber", serialNumber);
            if (StringUtil.isNotEmpty(port)) {
                param.put("port", Integer.parseInt(port));
            }
            if (StringUtil.isNotEmpty(ip)) {
                param.put("ip", ip);
            }
            if (StringUtil.isNotEmpty(ip)){
                param.put("address", address);
            }
            try {
                gatewayService.updateGatewayBySerialNumber(param);
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
     * 依据网关设备序列号删除网关
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=delGateway")
    public void delGatewayBySerialNumber(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String serialNumber = request.getParameter("serialNumber");
        if (StringUtil.isNotEmpty(serialNumber)) {
            try {
                gatewayService.delGatewayBySerialNumber(serialNumber);
                resultMap.put("result", "success");
            }
            catch (Exception e) {
                logger.error("依据网关设备序列号删除网关出错" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "网关设备序列号不能为空");
        }
        this.renderJson(response, resultMap);
    }

}
