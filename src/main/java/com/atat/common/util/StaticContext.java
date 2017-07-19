/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ligw
 * @version $Id StaticContext.java, v 0.1 2017-07-19 17:56 ligw Exp $$
 */
public class StaticContext {

    private static ApplicationContext context;// 此文件放在SRC目录下

    public static ApplicationContext getContext(){
        if (null == context){
            context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
    }
    return context;
    }
}
