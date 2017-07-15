package com.atat.common.util.httpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * Created by Administrator on 2017/6/25.
 */
public class HttpClientUtil {
    public static String doPost(String url, Map<String,String> map, String charset){
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try{
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Entry<String,String> elem = (Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
            }
            if(list.size() > 0){
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity((List<? extends org.apache.http.NameValuePair>) list,charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public static String post(String jsonStr,String URL) {

                 HttpClient client = new DefaultHttpClient();
                 HttpPost post = new HttpPost(URL);

                 post.setHeader("Content-Type", "application/json");
                 post.addHeader("Authorization", "Basic YWRtaW46");
                 String result = "";
                 try {
                     StringEntity s = new StringEntity(jsonStr, "utf-8");
                     s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
                             "application/json"));
                        post.setEntity(s);

                        // 发送请求
                         HttpResponse httpResponse = client.execute(post);

                         // 获取响应输入流
                         InputStream inStream = httpResponse.getEntity().getContent();
                         BufferedReader reader = new BufferedReader(new InputStreamReader(
                                         inStream, "utf-8"));
                         StringBuilder strber = new StringBuilder();
                         String line = null;
                         while ((line = reader.readLine()) != null)
                                 strber.append(line + "\n");
                        inStream.close();

                         result = strber.toString();
                         System.out.println(result);

                         if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                                     System.out.println("请求服务器成功，做相应处理");

                             } else {

                                 System.out.println("请求服务端失败");
                             }


                     } catch (Exception e) {
                         System.out.println("请求异常");
                         throw new RuntimeException(e);
                     }

                return result;
            }
}
