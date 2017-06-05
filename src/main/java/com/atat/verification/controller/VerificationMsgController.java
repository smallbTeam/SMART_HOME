/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.verification.controller;

import com.atat.common.base.controller.BaseController;
import com.atat.common.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping(params = "action=sendMsg")
    public void sendVerificationMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String strMobelPhone = request.getParameter("mobelPhone");
        String password = request.getParameter("password");
        if ((StringUtil.isNotEmpty(strMobelPhone)) && (StringUtil.isNotEmpty(password))) {
            resultMap.put("result", "success");
            resultMap.put("operationResult", strMobelPhone);
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "手机号或密码为空");
        }
        this.renderJson(response, resultMap);
        // 生成随机短信验证码
        // 发送短信
        // 成功-验证码存入session
        // 成功-将发送结果返回前台
        // 失败-将发送结果返回前台
    }

    @RequestMapping(params = "action=veridateMsg")
    public void veridateMsg() {
        // 验证session
        // 结果返回前台
    }
}
