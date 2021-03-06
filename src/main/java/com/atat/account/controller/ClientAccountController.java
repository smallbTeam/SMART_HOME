/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.account.controller;

import com.atat.account.service.CustomerService;
import com.atat.common.base.controller.BaseController;
import com.atat.common.prop.BasePropertyDate;
import com.atat.common.util.CollectionUtil;
import com.atat.common.util.JsonUtil;
import com.atat.common.util.StringUtil;
import com.atat.common.util.httpClient.HttpClientUtil;
import com.atat.common.util.httpClient.URLUtil;
import com.atat.message.service.WeixinMessageService;
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
 * @author ligw
 * @version $Id AccountController.java, v 0.1 2017-06-03 21:48 ligw Exp $$
 */
@Controller
@RequestMapping(value = "client/account")
public class ClientAccountController extends BaseController {

    @Resource
    private CustomerService customerService;

    @Resource
    private WeixinMessageService weixinMessageService;

    /**
     * 用户注册页面
     * 
     * @param request
     * @param response
     */
    @RequestMapping(params = "action=register")
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("register");
        String wxId = request.getParameter("wxId");
        if (StringUtil.isNotEmpty(wxId)) {
            mav.addObject("wxId", wxId);
        }
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
        ModelAndView mav = new ModelAndView("login");
        String wxId = request.getParameter("wxId");
        if (StringUtil.isNotEmpty(wxId)) {
            mav.addObject("wxId", wxId);
        }
        return mav;
    }

    /**
     * 个人中心页面
     *
     * @return
     */
    @RequestMapping(params = "action=personal")
    public ModelAndView clientPersonal(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("personal");
        String mobelPhone = request.getParameter("mobelPhone");
        if (StringUtil.isNotEmpty(mobelPhone)) {
            mav.addObject("account", customerService.getCustomerByMobelPhone(mobelPhone));
        }
        return mav;
    }

    /**
     * 微信跳转页面
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/wxUidIsExit")
    public ModelAndView wxUidIsExit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("register");
        // 判断微信Id是否已存在
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String appid = BasePropertyDate.WX_APPID;
        String wxId = weixinMessageService.getUserwxId(code);
        if (StringUtil.isNotEmpty(wxId)) {
            // 判定openId是否已经在表中存在
            Map<String, Object> customerMap = customerService.getCustomerByWxId(wxId);
            if (CollectionUtil.isNotEmpty(customerMap)) {
                mav = new ModelAndView("main");
                mav.addObject("account", customerMap);
                GetSignatureUrl signatureUrl = new GetSignatureUrl();
                String mainurl = "http://s-357114.gotocdn.com/smart_home/client/account/wxUidIsExit";
                Map<String, Object> weixinInfoMap = signatureUrl.getSignature(mainurl);
                mav.addObject("appid", appid);
                mav.addObject("noncestr", weixinInfoMap.get("noncestr"));
                mav.addObject("timestamp", weixinInfoMap.get("timestamp"));
                mav.addObject("signaturet", weixinInfoMap.get("signaturet"));
            }
            else {
                mav.addObject("wxId", wxId);
            }
        }
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
    public void registWeixinAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String mobelPhone = request.getParameter("mobelPhone");
        String wxId = request.getParameter("wxId");
        String password = request.getParameter("password");
        String nickName = request.getParameter("nickName");
        String birthday = request.getParameter("birthday");
        String sex = request.getParameter("sex");
        String token = request.getParameter("token");
        if ((StringUtil.isNotEmpty(mobelPhone)) && (StringUtil.isNotEmpty(password))
                && (StringUtil.isNotEmpty(nickName)) && (StringUtil.isNotEmpty(birthday))
                && (StringUtil.isNotEmpty(sex))) {
            Map<String, Object> param = new HashMap<String, Object>();
            // Customer customer = new Customer();
            param.put("mobelPhone", mobelPhone);
            param.put("password", password);
            if (StringUtil.isNotEmpty(wxId)) {
                param.put("wxId", wxId);
            }
            if (StringUtil.isNotEmpty(token)) {
                param.put("token", token);
            }
            if (StringUtil.isNotEmpty(nickName)) {
                param.put("nickName", nickName);
            }
            if (StringUtil.isNotEmpty(birthday)) {
                param.put("birthday", new Date(Long.parseLong(birthday)));
            }
            if (StringUtil.isNotEmpty(sex)) {
                param.put("sex", Integer.parseInt(sex));
            }
            try {
                customerService.addCustomer(param);
                resultMap.put("result", "success");
                resultMap.put("operationResult", mobelPhone);
            }
            catch (Exception e) {
                logger.error("添加用户出错" + e, e);
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
     * 用户登录
     * 0 手机号或密码错误
     * 1 成功
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "action=accountLogin")
    public void accountLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 用户名(必传)
        String mobelPhone = request.getParameter("mobelPhone");
        // 密码(必传)
        String password = request.getParameter("password");
        // wxId
        String wxId = request.getParameter("wxId");
        if (StringUtil.isNotEmpty(mobelPhone) && StringUtil.isNotEmpty(password)) {
            try {
                Integer loginRes = customerService.accountLogin(mobelPhone, password, wxId);
                resultMap.put("result", "success");
                resultMap.put("operationResult", loginRes);
            }
            catch (Exception e) {
                logger.error("用户登录出错" + e, e);
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

    /**
     * 依据手机号较验账户是否存在
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=accountIsExit")
    public void accountIsExit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String strMobelPhone = request.getParameter("mobelPhone");
        if (StringUtil.isNotEmpty(strMobelPhone)) {
            try {
                // 依据手机号查询用户是否已存在
                Map<String, Object> customer = customerService.getCustomerByMobelPhone(strMobelPhone);
                if (CollectionUtil.isNotEmpty(customer)) {
                    resultMap.put("result", "success");
                    resultMap.put("operationResult", true);
                    resultMap.put("customer", customer);
                }
                else {
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
     * 用户更换手机号
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=accountUpdateMobile")
    public void accountUpdateMobile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String newMobelPhone = request.getParameter("newMobelPhone");
        String customerId = request.getParameter("customerId");
        String veridateMsg = request.getParameter("veridateMsg");
        if ((StringUtil.isNotEmpty(veridateMsg))
                && (StringUtil.isNotEmpty(newMobelPhone)) && (StringUtil.isNotEmpty(customerId))) {
            // 验证session
            String msmRandomCode = (String) request.getSession().getAttribute("msgCodeSsion" + newMobelPhone);
            String randomCode = "";
            String timeStamp = "";
            long timePath = 600001;
            if (StringUtil.isNotEmpty(msmRandomCode)) {
                String temp[] = msmRandomCode.split("&&");
                randomCode = temp[0];
                timeStamp = temp[1];
            }
            // 计算时间差
            if ((StringUtil.isNotEmpty(randomCode)) && (StringUtil.isNotEmpty(timeStamp))) {
                timePath = (Long) ((new Date()).getTime()) - Long.parseLong(timeStamp);
            }
            if ((StringUtil.isNotEmpty(randomCode)) && (veridateMsg.equals(randomCode)) && (timePath < 600000)) {
                // 结果返回前台
                try {
                    // 依据手机号查询用户是否已存在
                    Map<String, Object> customer = customerService.getCustomerByMobelPhone(newMobelPhone);
                    if (CollectionUtil.isEmpty(customer)) {
                        Map<String, Object> param = new HashMap<String, Object>();
                        param.put("customerId", Integer.parseInt(customerId));
                        param.put("mobelPhone", newMobelPhone);
                        customerService.updateCustomerById(param);
                        resultMap.put("result", "success");
                        resultMap.put("operationResult", 1);
                    }
                    else {
                        resultMap.put("result", "success");
                        resultMap.put("operationResult", 0);
                    }
                }
                catch (NumberFormatException e) {
                    logger.error("依据手机号查询用户是否已存在出错" + e, e);
                    resultMap.put("result", "failed");
                    resultMap.put("error", "系统出错");
                }
            }
            else {
                resultMap.put("result", "error");
                resultMap.put("operationResult", "验证码错误");
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "重要参数为空");
        }
        this.renderJson(response, resultMap);
    }

    /**
     * 用户分页
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=accountPageTurn")
    public void accountPageTurn(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String strMobelPhone = request.getParameter("mobelPhone");
        if (StringUtil.isNotEmpty(strMobelPhone)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("MobelPhone", strMobelPhone);
            try {
                // 依据手机号查询用户是否已存在
                PageInfo<Map<String, Object>> customerPageInfo = customerService.getCustomerPageTurn(param, null, null);
                if (null != customerPageInfo) {
                    resultMap.put("result", "success");
                    resultMap.put("operationResult", customerPageInfo);
                }
                else {
                    resultMap.put("result", "success");
                    resultMap.put("operationResult", false);
                }
            }
            catch (NumberFormatException e) {
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
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=updateAccount")
    public void updateAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String customerId = request.getParameter("customerId");
        String password = request.getParameter("password");
        String wxId = request.getParameter("wxId");
        String nickName = request.getParameter("nickName");
        String birthday = request.getParameter("birthday");
        String sex = request.getParameter("sex");
        String token = request.getParameter("token");
        if (StringUtil.isNotEmpty(customerId)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("customerId", Integer.parseInt(customerId));
            if (StringUtil.isNotEmpty(password)) {
                param.put("password", password);
            }
            if (StringUtil.isNotEmpty(wxId)) {
                param.put("wxId", wxId);
            }
            if (StringUtil.isNotEmpty(nickName)) {
                param.put("nickName", nickName);
            }
            if (StringUtil.isNotEmpty(sex)) {
                param.put("sex", Integer.parseInt(sex));
            }
            if (StringUtil.isNotEmpty(token)) {
                param.put("token", token);
            }
            if (StringUtil.isNotEmpty(birthday)) {
                param.put("birthday", new Date(Long.parseLong(birthday)));
            }
            try {
                customerService.updateCustomerById(param);
                resultMap.put("result", "success");
            }
            catch (Exception e) {
                logger.error("依据ID更改用户信息出错" + e, e);
                resultMap.put("result", "failed");
                resultMap.put("error", "系统出错");
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "用户ID不能为空");
        }
        this.renderJson(response, resultMap);
    }
}
