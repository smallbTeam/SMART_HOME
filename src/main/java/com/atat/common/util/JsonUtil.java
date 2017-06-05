package com.atat.common.util;

import com.alibaba.fastjson.JSON;

/**
 * 
 * 【JSON转换工具类】
 * 配合com.alibaba.fastjson
 * @author huangxs 
 * @date 2017年4月4日 
 * @version 1.0
 */
public class JsonUtil {
    /**
     * 将任何对象转化成Json字符串
     * @param object    需要转化的对象
     * @return
     */
    public static String toJson(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * 将任何对象转化为JSON字符串（可以按照json格式格式化字符串）
     * @param object    需要转化的对象
     * @param isFormat  是否需要格式化
     * @return
     */
    public static String toJson(Object object, boolean isFormat) {
        return JSON.toJSONString(object, isFormat);
    }

    /**
     * 将json字符串反序列化为对象
     * @param jsonString    json字符串
     * @param clazz         对象的class对象
     * @param <T>           泛型
     * @return
     */
    public static <T>T fromJson(String jsonString, Class<T> clazz) {
        return JSON.parseObject(jsonString, clazz);
    }

}
