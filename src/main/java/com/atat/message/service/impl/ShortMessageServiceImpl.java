/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.message.service.impl;

import com.atat.common.util.StringUtil;
import com.atat.common.util.httpClient.URLUtil;
import com.atat.message.service.ShortMessageService;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author ligw
 * @version $Id shortMessageServiceImpl.java, v 0.1 2017-07-15 16:00 ligw Exp $$
 */
@Service
public class ShortMessageServiceImpl implements ShortMessageService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private Properties smsPlatformProperties;

    @Override
    public Integer sendShortMessage(String mobelPhone, String msmContent) {
        try {
            msmContent = URLEncoder.encode(msmContent, "utf-8");
            // String msmContent = randomCode;
            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("Message", msmContent);
            paramMap.put("Phone", mobelPhone);
            paramMap.put("Timestamp", "0");
            paramMap.put("Psw", smsPlatformProperties.getProperty("sms.platform.pwd"));
            paramMap.put("Name", smsPlatformProperties.getProperty("sms.platform.name"));
            paramMap.put("Id", smsPlatformProperties.getProperty("sms.platform.Id"));
            String url = "http://sms.bdt360.com:8180/Service.asmx/SendMessage";
            String resXml = null;
            try {
                resXml = URLUtil.originalGetData(url, paramMap);
                logger.info("短信get请求：" + URLUtil.getDataUrl(url, paramMap));
            }
            catch (Exception e) {
                logger.error("[短信平台发送信息][Http请求异常" + e.getMessage() + "]", e);
            }
            logger.info("短信发送结果：[" + resXml + "]");
            // 读取返回结果
            Map<String, String> resMap = new HashMap<String, String>();
            if (StringUtil.isNotEmpty(resXml)) {
                // 创建一个新的字符串
                StringReader read = new StringReader(resXml);
                // 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
                InputSource source = new InputSource(read);
                // 创建一个新的SAXBuilder
                SAXBuilder sb = new SAXBuilder();
                try {
                    // 通过输入源构造一个Document
                    Document doc = sb.build(source);
                    // 取的根元素
                    Element root = doc.getRootElement();
                    System.out.println(root.getName());// 输出根元素的名称（测试）
                    // 得到根元素所有子元素的集合
                    List jiedian = root.getChildren();
                    // 获得XML中的命名空间（XML中未定义可不写）
                    Namespace ns = root.getNamespace();
                    Element et = null;
                    Object[] mapList = jiedian.toArray();
                    for (int i = 0;i < jiedian.size();i++) {
                        et = (Element) jiedian.get(i);// 循环依次得到子元素
                        String text = (null == et.getText()) ? "" : et.getText();
                        resMap.put(et.getName(), text);
                    }
                }
                catch (JDOMException e) {
                    // TODO 自动生成 catch 块
                    e.printStackTrace();
                }
                catch (IOException e) {
                    // TODO 自动生成 catch 块
                    e.printStackTrace();
                }
            }
            String state = resMap.get("State");
            if ((null != state) && ("1".equals(state))) {
                // 成功-验证码存入session
                return 1;
            }
            else {
                return 0;
            }
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
