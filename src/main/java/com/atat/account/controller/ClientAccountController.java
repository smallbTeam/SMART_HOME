/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.account.controller;

import com.atat.account.bean.Customer;
import com.atat.account.service.ClientAccountService;
import com.atat.common.base.controller.BaseController;
import com.atat.common.prop.BasePropertyDate;
import com.atat.common.util.CollectionUtil;
import com.atat.common.util.JsonUtil;
import com.atat.common.util.StringUtil;
import com.atat.common.util.httpClient.HttpClientUtil;
import com.atat.common.util.httpClient.URLUtil;
import com.atat.property.action.GetSignatureUrl;
import com.atat.property.action.WeixinAction;
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
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("MobelPhone", mobelPhone);
            List<Map<String, Object>> customerList = clientAccountService.selectCustomerList(param);
            if (CollectionUtil.isNotEmpty(customerList)) {
                mav.addObject("account", customerList.get(0));
            }
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
    public ModelAndView wxUidIsExit(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mav = new ModelAndView("register");
        // 判断微信Id是否已存在
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String appid = BasePropertyDate.WX_APPID;
        String secret = BasePropertyDate.WX_SECRET;
        if (StringUtil.isNotEmpty(code) && StringUtil.isNotEmpty(appid)
                && StringUtil.isNotEmpty(secret)) {
            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("appid", appid);
            paramMap.put("secret", secret);
            paramMap.put("code", code);
            paramMap.put("grant_type", "authorization_code");
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
            String resJson = "";
                try {
                resJson = HttpClientUtil.doPost(url,paramMap,"utf-8");
                logger.info("微信平台get请求：" + URLUtil.getDataUrl(url, paramMap));
                System.out.println("微信平台get请求：" + URLUtil.getDataUrl(url, paramMap));
                logger.info("微信平台请求用户OpenID：[" + resJson + "]");
                System.out.println("微信平台请求用户OpenID：[" + resJson + "]");
                // 解析Json 获取AppId
                Map<String, Object> resMap = JsonUtil.fromJson(resJson, Map.class);
                String wxId = (String) resMap.get("openid");
                if (StringUtil.isNotEmpty(wxId)){
                    // 判定openId是否已经在表中存在
                    Map<String, Object> customerMap = clientAccountService.selectCustomerByWxid(wxId);
                    if (null != customerMap) {
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
                } else {
                    Integer errcode = (Integer) resMap.get("errcode");
                    String errmsg = (String) resMap.get("errmsg");
                    logger.error("微信平台请求用户OpenID出错[errcode:"+errcode+"][errmsg:"+errmsg+"]");
                }
            } catch (Exception e) {
                logger.error("[微信平台请求用户OpenID][Http请求异常" + e.getMessage() + "]", e);
                System.out.println("[微信平台请求用户OpenID][Http请求异常" + e.getMessage() + "]");
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
     *  1 成功
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
        if (StringUtil.isNotEmpty(mobelPhone) && StringUtil.isNotEmpty(password) && StringUtil.isNotEmpty(wxId)) {
            try {
                Integer loginRes = clientAccountService.accountLogin(mobelPhone,password,wxId);
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
                Customer customer = clientAccountService.getCustomerByMobel(strMobelPhone);
                if (null != customer) {
                    resultMap.put("result", "success");
                    resultMap.put("operationResult", true);
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
                PageInfo<Map<String, Object>> customerPageInfo = clientAccountService.getCustomerPageTurn(param, null, null);
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
