/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.account.controller;

import com.atat.common.base.controller.BaseController;
import com.atat.common.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户 账户操作相关页面
 * @author ligw
 * @version $Id AccountController.java, v 0.1 2017-06-03 21:48 ligw Exp $$
 */
@Controller
@RequestMapping(value = "client/account")
public class ClientAccountController extends BaseController{

    /**
     * 用户注册页面
     * @param request
     * @param response
     */
    @RequestMapping(params = "action=register")
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView("client/account/register");
        return mav;
    }

    /**
     * 用户登录页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(params = "action=login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView("client/account/login");
        return mav;
    }

    /**
     * 记录用户注册信息
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=registAccount")
    public void registAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        String strMobelPhone = request.getParameter("mobelPhone");
        String password = request.getParameter("password");
        if((StringUtil.isNotEmpty(strMobelPhone)) && (StringUtil.isNotEmpty(password))){
            resultMap.put("result", "success");
            resultMap.put("operationResult", strMobelPhone);
        } else {
            resultMap.put("result", "error");
            resultMap.put("error", "手机号或密码为空");
        }
        this.renderJson(response,resultMap);
    }
}
