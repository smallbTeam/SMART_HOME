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
import java.util.Map;

/**
 * @author ligw
 * @version $Id TestController.java, v 0.1 2017-05-31 18:39 ligw Exp $$
 */
@Controller
@RequestMapping(value = "client/device")
public class DeviceDataController extends BaseController {

    @Resource
    private DeviceDataWeekService deviceDataWeekService;

    @Resource
    private DeviceDataDayService deviceDataDayService;

    @Resource
    private DeviceDataHourService deviceDataHourService;

    @Resource
    private DeviceDataNowService deviceDataNowService;



    /**
     * 获取设备数据 依据网关设备号 设备序号 获取设备数据
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=getDeviceDataMap")
    public void getDeviceTodayData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String type = request.getParameter("type");
        String deviceId = request.getParameter("deviceId");
        String code = request.getParameter("code");
        if (StringUtil.isNotEmpty(type) && StringUtil.isNotEmpty(deviceId)
        && StringUtil.isNotEmpty(code)) {
            try {
                Map<String, Object> deviceDataList = new HashMap<String, Object>();
                if ("hour".equals(type)){
                    deviceDataList = deviceDataNowService.getThreeHourDeviceData(Long.parseLong(deviceId),code);
                } else if ("day".equals(type)){
                    deviceDataList = deviceDataHourService.getOneDayDeviceData(Long.parseLong(deviceId),code);
                } else if("year".equals(type)){
                    deviceDataList = deviceDataWeekService.getOneYearDeviceData(Long.parseLong(deviceId),code);
                }
                resultMap.put("result", "success");
                resultMap.put("operationResult", deviceDataList);
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

}
