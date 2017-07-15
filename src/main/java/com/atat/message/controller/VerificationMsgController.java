/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.message.controller;

import com.atat.account.service.CustomerService;
import com.atat.common.base.controller.BaseController;
import com.atat.common.util.CollectionUtil;
import com.atat.common.util.JsonUtil;
import com.atat.common.util.StringUtil;
import com.atat.common.util.httpClient.URLUtil;
import com.atat.message.service.ShortMessageService;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.InputSource;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.*;

/**
 * 短信验证码
 * 
 * @author ligw
 * @version $Id VerificationMsgController.java, v 0.1 2017-06-05 21:45 ligw Exp
 *          $$
 */
@Controller
@RequestMapping(value = "verificationMsg")
public class VerificationMsgController extends BaseController {

    @Resource
    private Properties smsPlatformProperties;

    @Resource
    private ShortMessageService shortMessageService;

    @Resource
    private CustomerService customerService;

    /**
     * 发送短信验证码 并记录时间戳
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=sendMsg")
    public void sendVerificationMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String mobelPhone = request.getParameter("mobelPhone");
        // String timeStamp = request.getParameter("timeStamp");
        if (StringUtil.isNotEmpty(mobelPhone)) {
            // 依据手机号查询用户是否已存在
            Map<String, Object> customer = customerService.getCustomerByMobelPhone(mobelPhone);
            if (CollectionUtil.isEmpty(customer)) {
                // 生成随机六位短信验证码
                String randomCode = ((int) ((Math.random() * 9 + 1) * 100000)) + "";
                // 发送短信
                String msgContent = "感谢注ATAT智能家居，您的验证码为 " + randomCode + "有效时间十分钟";
                String state = shortMessageService.sendShortMessage(mobelPhone, msgContent).toString();
                if ((null != state) && ("1".equals(state))) {
                    // 成功-验证码存入session
                    String timeStamp = String.valueOf(new Date().getTime());
                    request.getSession().setAttribute("msgCodeSsion" + mobelPhone, randomCode + "&&" + timeStamp);
                    // 成功-将发送结果返回前台
                    resultMap.put("result", "success");
                    resultMap.put("operationResult", randomCode);
                }
                else {
                    resultMap.put("result", "failed");
                    resultMap.put("operationResult", "短信平台故障");
                }
                // 失败-将发送结果返回前台
            }
            else {
                resultMap.put("result", "success");
                resultMap.put("operationResult", "用户已存在");
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "手机号为空");
        }
        this.renderJson(response, resultMap);
    }

    /**
     * 较验验证码是否有效
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=veridateMsg")
    public void veridateMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String veridateMsg = request.getParameter("veridateMsg");
        String mobelPhone = request.getParameter("mobelPhone");
        if ((StringUtil.isNotEmpty(mobelPhone)) && (StringUtil.isNotEmpty(veridateMsg))) {
            // 验证session
            String msmRandomCode = (String) request.getSession().getAttribute("msgCodeSsion" + mobelPhone);
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
                resultMap.put("result", "success");
                resultMap.put("operationResult", true);
            }
            else {
                resultMap.put("result", "success");
                resultMap.put("operationResult", false);
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "手机号或验证码为空");
        }
        this.renderJson(response, resultMap);
    }
}
