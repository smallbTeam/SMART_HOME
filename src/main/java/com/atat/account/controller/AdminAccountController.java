/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.account.controller;

import com.atat.account.service.AdminService;
import com.atat.account.service.CustomerService;
import com.atat.common.base.controller.BaseController;
import com.atat.common.prop.BasePropertyDate;
import com.atat.common.util.CollectionUtil;
import com.atat.common.util.JsonUtil;
import com.atat.common.util.StringUtil;
import com.atat.common.util.httpClient.HttpClientUtil;
import com.atat.common.util.httpClient.URLUtil;
import com.atat.property.action.GetSignatureUrl;
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
import java.util.Map;

/**
 *
 * @author ligw
 * @version $Id AccountController.java, v 0.1 2017-06-03 21:48 ligw Exp $$
 */
@Controller
@RequestMapping(value = "admin/account")
public class AdminAccountController extends BaseController {

    @Resource
    private AdminService adminService;


    /**
     * 管理员登录页面
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(params = "action=login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("adminlogin");
        return mav;
    }

    /**
     * 管理员首页
     *
     * @return
     */
    @RequestMapping(params = "action=index")
    public ModelAndView clientPersonal(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("adminIndex");
        String adminId = request.getParameter("adminId");
        if (StringUtil.isNotEmpty(adminId)) {
                mav.addObject("admin", adminService.getAdminById(Integer.parseInt(adminId)));
        }
        return mav;
    }

   /**
     * 管理员注册
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=registAccount")
    public void registWeixinAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String mobelPhone = request.getParameter("mobelPhone");
        String password = request.getParameter("password");
        String nickName = request.getParameter("nickName");
        String permissionLevel = request.getParameter("permissionLevel");
        String token = request.getParameter("token");
        if ((StringUtil.isNotEmpty(mobelPhone)) && (StringUtil.isNotEmpty(password))
                && (StringUtil.isNotEmpty(nickName)) && (StringUtil.isNotEmpty(permissionLevel))) {
            Map<String, Object> param = new HashMap<String, Object>();
            //Customer customer = new Customer();
            param.put("mobelPhone",mobelPhone);
            param.put("password",password);
            if (StringUtil.isNotEmpty(token)) {
                param.put("token",token);
            }
            if (StringUtil.isNotEmpty(nickName)) {
                param.put("nickName",nickName);
            }
            if (StringUtil.isNotEmpty(permissionLevel)) {
                param.put("permissionLevel",Integer.parseInt(permissionLevel));
            }
            try {
                adminService.addAdmin(param);
                resultMap.put("result", "success");
                resultMap.put("operationResult", mobelPhone);
            }
            catch (Exception e) {
                logger.error("添加管理员出错" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "有必填项为空");
        }
        this.renderJson(response, resultMap);
    }

    /**
     * 管理员登录
     * 0 手机号或密码错误
     *  Id 成功
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "action=accountLogin")
    public void accountLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 管理员名(必传)
        String mobelPhone = request.getParameter("mobelPhone");
        // 密码(必传)
        String password = request.getParameter("password");
        if (StringUtil.isNotEmpty(mobelPhone) && StringUtil.isNotEmpty(password)) {
            try {
                Long loginRes = adminService.adminLogin(mobelPhone,password);
                resultMap.put("result", "success");
                resultMap.put("operationResult", loginRes);
            }
            catch (Exception e) {
                logger.error("管理员登录出错" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "未知错误-系统异常");
        }
        this.renderJson(response, resultMap);
    }

//    /**
//     * 依据手机号较验账户是否存在
//     *
//     * @param request
//     * @param response
//     * @throws IOException
//     */
//    @RequestMapping(params = "action=accountIsExit")
//    public void accountIsExit(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//        String strMobelPhone = request.getParameter("mobelPhone");
//        if (StringUtil.isNotEmpty(strMobelPhone)) {
//            try {
//                // 依据手机号查询管理员是否已存在
//                Map<String, Object> customer = customerService.getCustomerByMobelPhone(strMobelPhone);
//                if (null != customer) {
//                    resultMap.put("result", "success");
//                    resultMap.put("operationResult", true);
//                }
//                else {
//                    resultMap.put("result", "success");
//                    resultMap.put("operationResult", false);
//                }
//            }
//            catch (NumberFormatException e) {
//                logger.error("依据手机号查询管理员是否已存在出错" + e, e);
//                resultMap.put("result", "failed");
//                resultMap.put("error", "系统出错");
//            }
//        }
//        else {
//            resultMap.put("result", "error");
//            resultMap.put("error", "手机号为空");
//        }
//        this.renderJson(response, resultMap);
//    }
//
//    /**
//     * 管理员分页
//     *
//     * @param request
//     * @param response
//     * @throws IOException
//     */
//    @RequestMapping(params = "action=accountPageTurn")
//    public void accountPageTurn(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//        String strMobelPhone = request.getParameter("mobelPhone");
//        if (StringUtil.isNotEmpty(strMobelPhone)) {
//            Map<String, Object> param = new HashMap<String, Object>();
//            param.put("MobelPhone", strMobelPhone);
//            try {
//                // 依据手机号查询管理员是否已存在
//                PageInfo<Map<String, Object>> customerPageInfo = customerService.getCustomerPageTurn(param, null, null);
//                if (null != customerPageInfo) {
//                    resultMap.put("result", "success");
//                    resultMap.put("operationResult", customerPageInfo);
//                }
//                else {
//                    resultMap.put("result", "success");
//                    resultMap.put("operationResult", false);
//                }
//            }
//            catch (NumberFormatException e) {
//                logger.error("依据手机号查询管理员是否已存在出错" + e, e);
//                resultMap.put("result", "failed");
//                resultMap.put("error", "系统出错");
//            }
//        }
//        else {
//            resultMap.put("result", "error");
//            resultMap.put("error", "手机号为空");
//        }
//        this.renderJson(response, resultMap);
//    }
//
//    /**
//     * 依据ID更改管理员信息 密码
//     *
//     * @param request
//     * @param response
//     * @throws IOException
//     */
//    @RequestMapping(params = "action=updateAccount")
//    public void updateAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//        String customerId = request.getParameter("customerId");
//        String mobelPhone = request.getParameter("mobelPhone");
//        String password = request.getParameter("password");
//        String wxId = request.getParameter("wxId");
//        String nickName = request.getParameter("nickName");
//        String birthday = request.getParameter("birthday");
//        String sex = request.getParameter("sex");
//        String token = request.getParameter("token");
//        if (StringUtil.isNotEmpty(customerId)) {
//            Map<String, Object> param = new HashMap<String, Object>();
//            param.put("customerId", Integer.parseInt(customerId));
//            if (StringUtil.isNotEmpty(mobelPhone)) {
//                param.put("mobelPhone", mobelPhone);
//            }
//            if (StringUtil.isNotEmpty(password)) {
//                param.put("password", password);
//            }
//            if (StringUtil.isNotEmpty(wxId)) {
//                param.put("wxId", wxId);
//            }
//            if (StringUtil.isNotEmpty(nickName)) {
//                param.put("nickName", nickName);
//            }
//            if (StringUtil.isNotEmpty(sex)) {
//                param.put("sex", Integer.parseInt(sex));
//            }
//            if (StringUtil.isNotEmpty(token)) {
//                param.put("token", token);
//            }
//            if (StringUtil.isNotEmpty(birthday)) {
//                param.put("birthday", new Date(Long.parseLong(birthday)));
//            }
//            try {
//                customerService.updateCustomerById(param);
//                resultMap.put("result", "success");
//            }
//            catch (Exception e) {
//                logger.error("依据ID更改管理员信息出错" + e, e);
//                resultMap.put("result", "failed");
//                resultMap.put("error", "系统出错");
//            }
//        }
//        else {
//            resultMap.put("result", "error");
//            resultMap.put("error", "管理员ID不能为空");
//        }
//        this.renderJson(response, resultMap);
//    }
}
