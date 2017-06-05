/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.test.service.impl;

import com.atat.common.util.CollectionUtil;
import com.atat.test.dao.TestSpecMapper;
import com.atat.test.bean.TestDTO;
import com.atat.test.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ligw
 * @version $Id TestServiceImpl.java, v 0.1 2017-05-24 22:42 ligw Exp $$
 */
@Service
public class TestServiceImpl implements TestService {

    @Resource
    private TestSpecMapper testSpecMapper;

    Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);
    @Override
    @net.bull.javamelody.MonitoredWithSpring
    public String stringTest(String name) {
        TestDTO testDTO = new TestDTO();
        testDTO.setAge(12);
        testDTO.setName(name);
        System.out.println(testDTO.toString());
        Integer sql = 0;
        if(CollectionUtil.isNotEmpty(testSpecMapper.testSelectInteger())){
            sql = testSpecMapper.testSelectInteger().get(0);
        }
        logger.info("数据库返回值:" + sql);
        return name + 12;
    }
}
