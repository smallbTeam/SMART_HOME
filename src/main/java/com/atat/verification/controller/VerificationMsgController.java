/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.verification.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 短信验证码
 * @author ligw
 * @version $Id VerificationMsgController.java, v 0.1 2017-06-05 21:45 ligw Exp $$
 */
@Controller
@RequestMapping(value = "verificationMsg")
public class VerificationMsgController {

    @RequestMapping(params = "action=sendMsg")
    public void sendVerificationMsg(){

        //生成随机短信验证码
        //发送短信
        //成功-验证码存入session
        //成功-将发送结果返回前台
        //失败-将发送结果返回前台
    }

    @RequestMapping(params = "action=veridateMsg")
    public void veridateMsg(){
        //验证session
        //结果返回前台
    }
}
