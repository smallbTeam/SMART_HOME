/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.account.controller;

import com.atat.account.bean.Customer;
import com.atat.account.service.ClientAccountService;
import com.atat.common.base.controller.BaseController;
import com.atat.common.util.CollectionUtil;
import com.atat.common.util.StringUtil;
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
 * 用户 账户操作相关页面
 * 
 * @author ligw
 * @version $Id AccountController.java, v 0.1 2017-06-03 21:48 ligw Exp $$
 */
@Controller
@RequestMapping(value = "client/account")
public class ClientAccountController extends BaseController {

    @Resource
    private ClientAccountService clientAccountService;

    /**
     * 用户注册页面
     * 
     * @param request
     * @param response
     */
    @RequestMapping(params = "action=register")
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("client/account/register");
        return mav;
    }

    /**
     * 用户登录页面
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(params = "action=login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("client/account/login");
        return mav;
    }

    /**
     * 用户注册
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=registAccount")
    public void registAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String mobelPhone = request.getParameter("mobelPhone");
        String wxId = request.getParameter("wxId");
        String password = request.getParameter("password");
        String nickName = request.getParameter("nickName");
        String birthday = request.getParameter("birthday");
        String sex = request.getParameter("sex");
        String token = request.getParameter("token");
        if ((StringUtil.isNotEmpty(mobelPhone)) && (StringUtil.isNotEmpty(password))) {
            Customer customer = new Customer();
            customer.setMobelPhone(mobelPhone);
            customer.setPassword(password);
            if (StringUtil.isNotEmpty(wxId)) {
                customer.setWxId(wxId);
            }
            if (StringUtil.isNotEmpty(token)) {
                customer.setToken(token);
            }
            if (StringUtil.isNotEmpty(nickName)) {
                customer.setNickName(nickName);
            }
            if (StringUtil.isNotEmpty(birthday)) {
                customer.setBirthday(new Date(Long.parseLong(birthday)));
            }
            if (StringUtil.isNotEmpty(sex)) {
                customer.setSex(Integer.parseInt(sex));
            }
            try {
                clientAccountService.addCustomer(customer);
                resultMap.put("result", "success");
                resultMap.put("operationResult", mobelPhone);
            } catch (Exception e) {
                logger.error("添加用户出错" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        } else {
            resultMap.put("result", "error");
            resultMap.put("error", "手机号或密码为空");
        }
        this.renderJson(response, resultMap);
    }

    @RequestMapping(params = "action=accountIsExit")
    public void accountIsExit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String strMobelPhone = request.getParameter("mobelPhone");
        if (StringUtil.isNotEmpty(strMobelPhone)) {
            try {
                // 依据手机号查询用户是否已存在
                List<Customer> customerList = clientAccountService.getCustomerByMobel(strMobelPhone);
                if (CollectionUtil.isNotEmpty(customerList)) {
                    resultMap.put("result", "success");
                    resultMap.put("operationResult", true);
                } else {
                    resultMap.put("result", "success");
                    resultMap.put("operationResult", false);
                }
            } catch (NumberFormatException e) {
                logger.error("依据手机号查询用户是否已存在出错" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "手机号为空");
        }
        this.renderJson(response, resultMap);
    }

    /**
     * 用户分页
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=accountPageTurn")
    public void accountPageTurn(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String strMobelPhone = request.getParameter("mobelPhone");
        if (StringUtil.isNotEmpty(strMobelPhone)) {
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("MobelPhone",strMobelPhone);
            try {
                // 依据手机号查询用户是否已存在
                PageInfo<Map<String,Object>> customerPageInfo = clientAccountService.getCustomerPageTurn(param,null,null);
                if (null != customerPageInfo) {
                    resultMap.put("result", "success");
                    resultMap.put("operationResult", customerPageInfo);
                } else {
                    resultMap.put("result", "success");
                    resultMap.put("operationResult", false);
                }
            } catch (NumberFormatException e) {
                logger.error("依据手机号查询用户是否已存在出错" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "手机号为空");
        }
        this.renderJson(response, resultMap);
    }

    /**
     * 依据ID更改用户信息 密码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=updateAccount")
    public void updateAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String customerId = request.getParameter("customerId");
        String mobelPhone = request.getParameter("mobelPhone");
        String password = request.getParameter("password");
        String wxId = request.getParameter("wxId");
        String nickName = request.getParameter("nickName");
        String birthday = request.getParameter("birthday");
        String sex = request.getParameter("sex");
        String token = request.getParameter("token");
        if (StringUtil.isNotEmpty(customerId)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("CustomerId", Integer.parseInt(customerId));

            if (StringUtil.isNotEmpty(mobelPhone)) {
                param.put("MobelPhone", mobelPhone);
            }
            if (StringUtil.isNotEmpty(password)) {
                param.put("Password", password);
            }
            if (StringUtil.isNotEmpty(wxId)) {
                param.put("WxId", wxId);
            }
            if (StringUtil.isNotEmpty(nickName)) {
                param.put("NickName", nickName);
            }
            if (StringUtil.isNotEmpty(sex)) {
                param.put("Sex", Integer.parseInt(sex));
            }
            if (StringUtil.isNotEmpty(token)) {
                param.put("Token", token);
            }
            if (StringUtil.isNotEmpty(birthday)) {
                param.put("Birthday", new Date(Long.parseLong(birthday)));
            }
            try {
                clientAccountService.updateCustomerById(param);
                resultMap.put("result", "success");
            } catch (Exception e) {
                logger.error("依据ID更改用户信息出错" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        } else {
            resultMap.put("result", "error");
            resultMap.put("error", "用户ID不能为空");
        }
        this.renderJson(response, resultMap);
    }
}
