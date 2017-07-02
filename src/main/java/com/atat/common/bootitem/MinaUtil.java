package com.atat.common.bootitem;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static Map<String,Object> InPutMessageToString(String [] msg){
        Map map = new HashMap<String, Object>();
        map.put("devicenumber",msg[0]);
        map.put("wendu",StringToFloat(msg[2],"wendu"));
        map.put("shidu",StringToFloat(msg[3],"shidu"));
        map.put("pm2.5",StringToFloat(msg[4],"pm"));
        return map;
    }

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

}
