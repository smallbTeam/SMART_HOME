
/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.test.service;

import com.atat.test.service.impl.TestServiceImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author ligw
 * @version $Id TestServiceTest.java, v 0.1 2017-05-24 23:20 ligw Exp $$
 */
public class TestServiceTest {

    @Test
    public void testStringTest(){
        TestServiceImpl testService = new TestServiceImpl();
        String ret = testService.stringTest("Lili");
        Assert.assertEquals("Lili12",ret);
    }
}
