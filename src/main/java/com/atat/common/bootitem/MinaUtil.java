package com.atat.common.bootitem;

import com.atat.common.util.JsonUtil;
import com.atat.device.service.DeviceService;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

/**
 * Created by Administrator on 2017/7/2.
 */
public class MinaUtil {

    public void OutPutMessage(String msg,IoSession session ){
        byte[] bar = msg.getBytes();
        for (int z = 0; z < bar.length; z++) {
            bar[z] = (byte) Integer.parseInt(String.valueOf(bar[z]), 16);
        }
        IoBuffer buffers = IoBuffer.allocate(bar.length);
        buffers.put(bar, 0, bar.length);
        buffers.flip();
        session.write(buffers);
    }

    //////将数据转换为map
    public static Map<String,Object> InPutMessageToString(String [] msg){
        Map map = new HashMap<String, Object>();
        map.put("devicenumber",msg[0]);
        map.put("state",msg[1]);
        map.put("wendu",StringToFloat(msg[2],"wendu"));
        map.put("shidu",StringToFloat(msg[3],"shidu"));
        map.put("pm",StringToFloat(msg[4],"pm"));
        return map;
    }
//
//    //////根据数据类型判断执行什么操作
//    public static int InPutType(String msg){
//        if(msg.equals("shuju00")){
//            return 0;
//        }else{
//            return 1;
//        }
//    }


    private static float StringToFloat(String s, String type){
        int number = Integer.parseInt(s);
        return (float) (number/Math.pow(10,TypeToPoint(type)));
    }

    private static int TypeToPoint(String type){
       switch (type){
           case "wendu":{
               return 2;
           }
           case "shidu":{
               return 2;
           }
           case "pm":{
               return 0;
           }
       }
       return 0;
    }

    /////向数据库写入环境监测结果
    public static void sendEnviTosql(Map<String, Object>  map){

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");// 此文件放在SRC目录下
        DeviceService deviceService = (DeviceService) context.getBean("deviceService");

        //////向数据库传送数据
				String gatewayDeviceID = (String) map.get("devicenumber");
				Iterator it = map.keySet().iterator();
				while (it.hasNext()) {
					String key = it.next().toString();//设备类型名称
					Object value = map.get(key);//设备值
					String valuestr = JsonUtil.toJson(value);
					if (!"devicenumber".equals(key)){
						//将设备信息存储到数据库
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("gatewayDeviceID",gatewayDeviceID);
						param.put("Name",key);
						param.put("DeviceData",valuestr);
						deviceService.addOrUpdateDeviceDataBydeviceTypeAndgateway(param);
					}
				}
    }

}
