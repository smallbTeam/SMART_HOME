package com.atat.common.util.httpClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/25.
 */
public class TestMain {
     private String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
    private String charset = "utf-8";
    private HttpClientUtil httpClientUtil = null;

    public TestMain(){
        httpClientUtil = new HttpClientUtil();
    }

    public void test(){
//        String httpOrgCreateTest = url + "httpOrg";
        Map<String,String> createMap = new HashMap<String,String>();
        createMap.put("appid","wxe53df1855bab1cc8");
        createMap.put("secret","70f10e2dfd552c39fd4be98c81fd0f0d");
        createMap.put("code","031AuJKt1iE9Na0kUzGt1FZKKt1AuJKq");
        createMap.put("grant_type","authorization_code");
        String httpOrgCreateTestRtn = httpClientUtil.doPost(url,createMap,charset);

        System.out.println("result:"+httpOrgCreateTestRtn);
    }

    public static void main(String[] args){
        TestMain main = new TestMain();
        main.test();
    }
}
