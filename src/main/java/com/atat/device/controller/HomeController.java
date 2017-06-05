/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.device.controller;

import com.atat.common.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author ligw
 * @version $Id TestController.java, v 0.1 2017-05-31 18:39 ligw Exp $$
 */
@Controller
@RequestMapping(value="client/home")
public class HomeController extends BaseController{

    @RequestMapping(params = "action=index")
    public ModelAndView clientIndex() {
        ModelAndView mav = new ModelAndView("client/home/index");
        return mav;
    }

    @RequestMapping(params = "action=personal")
    public ModelAndView clientPersonal() {
        ModelAndView mav = new ModelAndView("client/home/personal");
        return mav;
    }

    @RequestMapping(params = "action=deviceList")
    public ModelAndView clientDeviceList() {
        ModelAndView mav = new ModelAndView("client/home/deviceList");
        return mav;
    }


}
