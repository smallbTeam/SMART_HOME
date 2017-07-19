package com.atat.common.bootitem;

import com.alibaba.fastjson.JSONObject;
import com.atat.account.service.CustomerService;
import com.atat.common.prop.BasePropertyDate;
import com.atat.common.util.JsonUtil;
import com.atat.common.util.StaticContext;
import com.atat.common.util.StringUtil;
import com.atat.device.service.DeviceDataNowService;
import com.atat.device.service.DeviceService;
import com.atat.device.service.RelCustomerGatewayService;
import com.atat.message.service.WeixinMessageService;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/7/2.
 */
public class MinaUtil {

    public void OutPutMessage(String msg, IoSession session) {
        byte[] bar = msg.getBytes();
        for (int z = 0; z < bar.length; z++) {
            bar[z] = (byte) Integer.parseInt(String.valueOf(bar[z]), 16);
        }
        IoBuffer buffers = IoBuffer.allocate(bar.length);
        buffers.put(bar, 0, bar.length);
        buffers.flip();
        session.write(buffers);
    }

    //	设备号       功能位     温度值       湿度       PM2.5       co2       tvoc
    //	TJAT00000  |  KQSJ   |  000000A0  |  0000A0  |  0000A0  | 000000A0 | 000000A0 |
    //	分隔符A后边的 0为正常  1 为中高   2 为最高
    public static List<Map<String, Object>> InPutMessageToMap(String[] msg) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map map = new HashMap<String, Object>();
        Map map2 = new HashMap<String, Object>();

        map.put("gatewaySerialNumber", msg[0]);
        map2.put("gatewaySerialNumber", msg[0]);

        map.put("serialNumber", msg[0]);
        map2.put("serialNumber", msg[0]);

        map.put("wendu", StringToFloat(msg[2].substring(0, msg[2].length() - 2), "wendu"));
        map2.put("wendu", StringToPoint(msg[2], "wendu"));
        map.put("shidu", StringToFloat(msg[3].substring(0, msg[3].length() - 2), "shidu"));
        map2.put("shidu", StringToPoint(msg[3], "shidu"));
        map.put("pm", StringToFloat(msg[4].substring(0, msg[4].length() - 2), "pm"));
        map2.put("pm", StringToPoint(msg[4], "pm"));
        map.put("voc", StringToFloat(msg[5].substring(0, msg[5].length() - 2), "voc"));
        map2.put("voc", StringToPoint(msg[5], "voc"));
        map.put("co2", StringToFloat(msg[6].substring(0, msg[6].length() - 2), "co2"));
        map2.put("co2", StringToPoint(msg[6], "co2"));
        list.add(map);
        list.add(map2);
        return list;
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


    private static float StringToFloat(String s, String type) {
        int number = Integer.parseInt(s);
        return (float) (number / Math.pow(10, TypeToPoint(type)));
    }


    private static String StringToPoint(String s, String type) {
        StringBuilder sb = new StringBuilder(s);//构造一个StringBuilder对象
        if(TypeToPoint(type)!=0){
            sb.insert(sb.length()-TypeToPoint(type)-2, ".");//在指定的位置1，插入指定的字符串
        }
        return sb.toString();
    }


    private static int TypeToPoint(String type) {
        switch (type) {
            case "wendu": {
                return 2;
            }
            case "shidu": {
                return 2;
            }
            case "pm": {
                return 0;
            }
        }
        return 0;
    }

    /////向数据库写入环境监测结果
    public static void sendEnviTosql(Map<String, Object> map) {

        DeviceDataNowService deviceDataNowService = (DeviceDataNowService) StaticContext.getContext().getBean("deviceDataNowService");
        //////向数据库传送数据
        String gatewaySerialNumber = (String) map.get("gatewaySerialNumber");
        map.remove("gatewaySerialNumber");
        String serialNumber = (String) map.get("serialNumber");
        map.remove("serialNumber");
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();//设备类型名称
            String value = JsonUtil.toJson((Object) map.get(key));//设备值
            if (!"devicenumber".equals(key)) {
                //将设备信息存储到数据库
                Map<String, Object> param = new HashMap<String, Object>();
                // 网关设备序号
                param.put("gatewaySerialNumber", gatewaySerialNumber);
                // 设备序号
                param.put("seriaNumber", serialNumber);
                // 属性code
                param.put("code", key);
                // 值
                param.put("value", value);
                deviceDataNowService.addDeviceData(param);
            }
        }
    }

    /*
    提醒推送
    设备号       功能位    温度值       湿度       PM2.5       co2       tvoc
	TJAT00000  |  KQTS   |  000000A0  |  0000A0  |  0000A0  | 000000A0 | 000000A0 |
     */
    public static void AlarmPush(String[] msg) {
        for (int i = 2; i < 7; i++) {
            String[] str = msg[i].split("A");
            if (str.length < 2) {
                System.out.println("数据错误");
                return;
            } else {
                if(str[1].equals("1")||str[1].equals("2")){
                RelCustomerGatewayService relCustomerGatewayService = (RelCustomerGatewayService) StaticContext.getContext().getBean("relCustomerGatewayService");
                WeixinMessageService weixinMessageService = (WeixinMessageService) StaticContext.getContext().getBean("weixinMessageService");

                Map<String, Object> relCustomerGatewayinfo = new HashMap<String, Object>();
                Map<String, Object> rs = new HashMap<String, Object>();
                rs.put("gatewaySerialNumber", msg[0]);
                rs.put("isSendMsg",1);
                List<Map<String, Object>> relCustomerGatewayList = relCustomerGatewayService.selectRelCustomerGatewayList(rs);
                List<String> customerWxIdList = new ArrayList<String>();
                for (Map<String, Object> relcustomerGatewayInfo : relCustomerGatewayList) {
                    String customerWxId = (String) relcustomerGatewayInfo.get("wxId");
                    if (StringUtil.isNotEmpty(customerWxId)) {
                        customerWxIdList.add(customerWxId);
                    }
                }
                ///获取时间
                Date date=new Date();
                DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    JSONObject putData = new JSONObject();

                    putData.put("first", ContentToJsonObj("EHello，Attention:","#173177"));
                    putData.put("system", ContentToJsonObj(ContentToMap(i,str[1]),"#173177"));
                    putData.put("time", ContentToJsonObj(format.format(date),"#173177"));
                    putData.put("account", ContentToJsonObj("1","#173177"));
                    putData.put("remark", ContentToJsonObj("AttentionPlace","#173177"));
                //result 发送失败条数
                    Integer result = weixinMessageService.sendWeixinMessage(customerWxIdList,"",BasePropertyDate.WX_WARN_MODEL,putData);

                }
            }
        }
    }

    /**
     * 将数据以及颜色放入map中
     * @param content
     * @param color
     * @return
     */
    private static JSONObject ContentToJsonObj(String content, String color){
        JSONObject dd = new JSONObject();
        dd.put("value", content);//消息提示
        dd.put("color", color);
        return dd;
    }

    /**
     * 根据数据类型显示提示文字
     * @param
     * @return
     */
    private static String ContentToMap(int type, String style){
        String typeContent="";
        String styleContent="";
        if(style.equals("1")){
            styleContent="较高";
        }else if(style.equals("2")){
            styleContent="很高";
        }else{
            styleContent="";
        }
        if(type==2){
            typeContent="温度";
        }else if(type==3){
            typeContent="湿度";
        }else if(type==4){
            typeContent="PM2.5";
        }else if(type==5){
            typeContent="VOC";
        }else if(type==6){
            typeContent="CO2";
        }
            return "您家中"+typeContent+"值"+styleContent;
    }


    //	设备号       功能位     温度值       湿度       PM2.5       co2       tvoc
    //	TJAT00000  |  KQSJ   |  000000A0  |  0000A0  |  0000A0  | 000000A0 | 000000A0 |
    //	分隔符A后边的 0为正常  1 为中高   2 为最高
    public static Map<String, Object> InPutMessageToString(String[] msg) {
        Map map = new HashMap<String, Object>();

        map.put("gatewaySerialNumber", msg[0]);

        map.put("serialNumber", msg[0]);


        map.put("wendu", StringToFloat(msg[2].substring(0, msg[2].length() - 2), "wendu"));
        map.put("shidu", StringToFloat(msg[3].substring(0, msg[3].length() - 2), "shidu"));
        map.put("pm", StringToFloat(msg[4].substring(0, msg[4].length() - 2), "pm"));
        map.put("voc", StringToFloat(msg[5].substring(0, msg[5].length() - 2), "voc"));
        map.put("co2", StringToFloat(msg[6].substring(0, msg[6].length() - 2), "co2"));
        return map;
    }

}
