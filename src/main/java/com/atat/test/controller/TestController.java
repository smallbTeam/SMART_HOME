/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.test.controller;

import com.atat.common.base.controller.BaseController;
import com.atat.test.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author ligw
 * @version $Id TestController.java, v 0.1 2017-05-31 18:39 ligw Exp $$
 */
@Controller
@RequestMapping(value="/test")
public class TestController extends BaseController{

    @Resource
    private TestService testService;

    @RequestMapping(params = "action=stringTest")
    public String stringTest() {
        logger.info("进入登录页面");
        String str = testService.stringTest("小敏");
        str = "text";
        return str;
    }


}
