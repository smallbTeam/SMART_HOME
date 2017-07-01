package com.atat.common.base.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atat.common.prop.PlatformPropertyConfigurer;
import com.atat.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;


/**
 * The <code>BaseController</code> 控制器基类,各个子系统可以根据需要继承该基类
 * 
 * @author sundaolin
 * @version 1.0, Created at 2014-3-25
 */
public abstract class BaseController extends AbstractController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected PlatformPropertyConfigurer props;

    /**
     * 通用成功失败页面
     */
    public final static String COMMON = "/common/jsp/message";

   
    public void setProps(PlatformPropertyConfigurer props) {
        this.props = props;
    }

    protected Properties ebizProperties;

   
    public void setEbizProperties(Properties ebizProperties) {
        this.ebizProperties = ebizProperties;
    }


    /**
     * 打出json结果
     * 
     * @param response
     *            http response
     * @param data
     *            json结果
     * @throws IOException
     *             异常信息
     */
    protected void renderJson(HttpServletResponse response, Object data) throws IOException {
        try {
            String jsonResponse = "";
            if (data instanceof String) {
                jsonResponse = (String) data;
            }
            else {
                jsonResponse = JsonUtil.toJson(data);
            }
            logger.info("Json Result:" + jsonResponse);
            response.setHeader("Access-Control-Allow-Origin", "http://localhost:8020");
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(jsonResponse);
        }
        catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    /**
     * 打出String结果
     * 
     * @param response
     *            http response
     * @param data
     *            json结果
     * @throws IOException
     *             异常信息
     */
    protected void renderString(HttpServletResponse response, String data) throws IOException {
        try {
            logger.info("String Result:" + data);
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print("\"" + data + "\"");
        }
        catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

   
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 变更时间格式
     * 
     * @param request
     * @param binder
     * @throws Exception
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

    /**
     * 获取请求报文
     * 
     * @param request
     * @param charsetName
     * @return
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    protected String getReqXml(HttpServletRequest request, String charsetName)
            throws UnsupportedEncodingException, IOException {
        InputStream is = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = is.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        byte[] bytes = outSteam.toByteArray();
        String xml = new String(bytes, charsetName);
        outSteam.close();
        is.close();
        return xml;
    }

}
