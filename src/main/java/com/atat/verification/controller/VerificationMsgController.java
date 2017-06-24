/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.verification.controller;

import com.atat.common.base.controller.BaseController;
import com.atat.common.util.JsonUtil;
import com.atat.common.util.StringUtil;
import com.atat.common.util.httpClient.URLUtil;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.InputSource;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.*;

/**
 * 短信验证码
 * 
 * @author ligw
 * @version $Id VerificationMsgController.java, v 0.1 2017-06-05 21:45 ligw Exp
 *          $$
 */
@Controller
@RequestMapping(value = "verificationMsg")
public class VerificationMsgController extends BaseController {

    @Resource
    private Properties smsPaltformProperties;

    /**
     * 发送短信验证码 并记录时间戳
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=sendMsg")
    public void sendVerificationMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String mobelPhone = request.getParameter("mobelPhone");
        //String timeStamp = request.getParameter("timeStamp");
        if (StringUtil.isNotEmpty(mobelPhone)) {
            // 生成随机六位短信验证码
            String randomCode = ((int) ((Math.random() * 9 + 1) * 100000)) + "";
            // 发送短信
             String msmContent = "感谢注ATAT智能家居，您的验证码为 " + randomCode + "有效时间十分钟";
             msmContent = URLEncoder.encode(msmContent, "utf-8");
            //String msmContent = randomCode;
            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("Message", msmContent);
            paramMap.put("Phone", mobelPhone);
            paramMap.put("Timestamp", "0");
            paramMap.put("Psw", smsPaltformProperties.getProperty("sms.platform.pwd"));
            paramMap.put("Name", smsPaltformProperties.getProperty("sms.paltform.name"));
            paramMap.put("Id", smsPaltformProperties.getProperty("sms.platform.Id"));
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
            if (StringUtil.isNotEmpty(resXml)){
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
                    resMap.put(et.getName(),text);
                }
            }
            catch (JDOMException e) {
                // TODO 自动生成 catch 块
                e.printStackTrace();
            }
            catch (IOException e) {
                // TODO 自动生成 catch 块
                e.printStackTrace();
            }}
            String state = resMap.get("State");
            if ((null != state) && ("1".equals(state))) {
                // 成功-验证码存入session
                String timeStamp = String.valueOf(new Date().getTime());
                request.getSession().setAttribute("msgCodeSsion" + mobelPhone, randomCode + "&&" + timeStamp);
                // 成功-将发送结果返回前台 
                resultMap.put("result", "success");
                resultMap.put("operationResult", randomCode);
            }
            else {
                resultMap.put("result", "failed");
                resultMap.put("operationResult", "短信平台故障");
            }
            // 失败-将发送结果返回前台
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "手机号为空");
        }
        this.renderJson(response, resultMap);
    }

    /**
     * 较验验证码是否有效
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=veridateMsg")
    public void veridateMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String veridateMsg = request.getParameter("veridateMsg");
        String mobelPhone = request.getParameter("mobelPhone");
        if ((StringUtil.isNotEmpty(mobelPhone)) && (StringUtil.isNotEmpty(veridateMsg))) {
            // 验证session
            String msmRandomCode = (String) request.getSession().getAttribute("msgCodeSsion" + mobelPhone);
            String randomCode = "";
            String timeStamp = "";
            long timePath = 600001;
            if (StringUtil.isNotEmpty(msmRandomCode)) {
                String temp[] = msmRandomCode.split("&&");
                randomCode = temp[0];
                timeStamp = temp[1];
            }
            // 计算时间差
            if ((StringUtil.isNotEmpty(randomCode)) && (StringUtil.isNotEmpty(timeStamp))) {
                timePath = (Long) ((new Date()).getTime()) - Long.parseLong(timeStamp);
            }
            if ((StringUtil.isNotEmpty(randomCode)) && (veridateMsg.equals(randomCode)) && (timePath < 600000)) {
                // 结果返回前台
                resultMap.put("result", "success");
                resultMap.put("operationResult", true);
            }
            else {
                resultMap.put("result", "success");
                resultMap.put("operationResult", false);
            }
        }
        else {
            resultMap.put("result", "error");
            resultMap.put("error", "手机号或验证码为空");
        }
        this.renderJson(response, resultMap);
    }
}
