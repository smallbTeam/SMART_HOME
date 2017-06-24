/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.common.listener;

import com.atat.common.bootitem.MinaServer;
import com.atat.property.action.WeixinAction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author ligw
 * @version $Id BootitemListener.java, v 0.1 2017-06-24 23:21 ligw Exp $$
 */
public class BootitemListener implements ServletContextListener {

    //初始启动
    @Override public void contextInitialized(ServletContextEvent servletContextEvent) {
        //更新accessToken 和jsapiTacket
        WeixinAction weixinAction = new WeixinAction();
        weixinAction.refreshWxaccessToken();
        //执行mina
        MinaServer minaServer = new MinaServer();
        minaServer.execute();
    }

    @Override public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
