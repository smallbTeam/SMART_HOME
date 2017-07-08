package com.atat.property.action;

import com.atat.property.service.PropertyMapService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2017/7/2.
 */
public class GetSignatureUrl {

    public Map<String, Object> getSignature(String mainurl) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");// 此文件放在SRC目录下
        PropertyMapService propertyMapService = (PropertyMapService) context.getBean("propertyMapService");

        Map<String, Object> jsapiticket_ticket = new HashMap<String, Object>();

        // 校验签名用的参数 参与签名的字段包括noncestr（随机字符串）, 有效的jsapi_ticket,
        // timestamp（时间戳）, url（当前网页的URL，不包含#及其后面部分）
        // 随机字符串

        String signature = "";

        // 随机字符串
        String noncestr = UUID.randomUUID().toString().replaceAll("-", "");
        // 时间戳
        String timestamp = String.valueOf(new Date().getTime());
        //url
        //String mainurl = "http://s-357114.gotocdn.com/smart_home/client/home?action=addGetway&mobelPhone="+phone;
        //获取jsapiticket
        String jsapiticketTicket = (String) propertyMapService.getPropertyMapByKey("jsapiticketTicket").get("propval");
        /////拼接sha1
        String str1 = "jsapi_ticket="+jsapiticketTicket+"&noncestr="+noncestr+"&timestamp="+timestamp+"&url="+mainurl;
        try {
            signature = sha1(str1);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        jsapiticket_ticket.put("noncestr",noncestr);
        jsapiticket_ticket.put("timestamp",timestamp);
        jsapiticket_ticket.put("signaturet",signature);
        return  jsapiticket_ticket;
    }


    // 执行sh1哈希散列运算算法
    public String sha1(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update(data.getBytes());
        StringBuffer buf = new StringBuffer();
        byte[] bits = md.digest();
        for (int i = 0;i < bits.length;i++) {
            int a = bits[i];
            if (a < 0) {
                a += 256;
            }
            if (a < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(a));
        }
        return buf.toString();
    }
}
