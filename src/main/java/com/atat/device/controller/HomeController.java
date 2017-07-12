/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.device.controller;

import com.atat.account.service.CustomerService;
import com.atat.common.base.controller.BaseController;
import com.atat.common.prop.BasePropertyDate;
import com.atat.common.util.StringUtil;
import com.atat.property.action.GetSignatureUrl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author ligw
 * @version $Id TestController.java, v 0.1 2017-05-31 18:39 ligw Exp $$
 */
@Controller
@RequestMapping(value = "client/home")
public class HomeController extends BaseController {

    @Resource
    private CustomerService customerService;

    /**
     * 首页
     * 
     * @return
     */
    @RequestMapping(params = "action=index")
    public ModelAndView clientIndex(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("main");
        String mobelPhone = request.getParameter("mobelPhone");
        if (StringUtil.isNotEmpty(mobelPhone)) {
                mav.addObject("account",customerService.getCustomerByMobelPhone(mobelPhone));
        }
        return mav;
    }

    @RequestMapping(params = "action=openWifiScan")
    public ModelAndView openWifiScan(HttpServletRequest request, HttpServletResponse response) {
        String mobelPhone = request.getParameter("mobelPhone");
        ModelAndView mav = new ModelAndView("openWifiScan");
        if (StringUtil.isNotEmpty(mobelPhone)) {
            mav.addObject("account",customerService.getCustomerByMobelPhone(mobelPhone));
        }
        GetSignatureUrl signatureUrl = new GetSignatureUrl();
        String mainurl = "http://s-357114.gotocdn.com/smart_home/client/home?action=openWifiScan&mobelPhone="+mobelPhone;
        Map<String, Object> map = signatureUrl.getSignature(mainurl);
        String appid = BasePropertyDate.WX_APPID;
        mav.addObject("appid", appid);
        mav.addObject("noncestr", map.get("noncestr"));
        mav.addObject("timestamp", map.get("timestamp"));
        mav.addObject("signaturet", map.get("signaturet"));
        return mav;
    }
}
