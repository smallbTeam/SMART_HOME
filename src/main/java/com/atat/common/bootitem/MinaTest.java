/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.common.bootitem;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.atat.common.bootitem.MinaUtil.InPutMessageToString;
import static com.atat.common.bootitem.MinaUtil.sendEnviTosql;

/**
 * @author ligw
 * @version $Id MinaTest.java, v 0.1 2017-07-09 12:51 ligw Exp $$
 */
public class MinaTest {
    public static int i = 1;

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            public void run() {
                // task to run goes here
                System.out.println("["+i+"次：耗时："+sendTest(i)+"]");
                i++;
            }
        };
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(runnable, 10, 100, TimeUnit.MICROSECONDS);
    }

    public static int sendTest(int i){
Long start = new Date().getTime();
        String wendu = "11"+i;
        String shidu = "22"+i;
        String pm = "33"+i;
        String state = "数据00";
        String [] str = {"TJ000",state,wendu,shidu,pm};
        Map<String,Object> map =  MinaUtil.InPutMessageToString(str);
        MinaUtil.sendEnviTosql(map);
       Long cost = (new Date().getTime()) - start;
       return cost.intValue();
    }
}
