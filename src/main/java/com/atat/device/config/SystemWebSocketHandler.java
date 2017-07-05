package com.atat.device.config;

import com.atat.common.bootitem.WebSocketBean;
import com.atat.common.util.JsonUtil;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.json.Json;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Map;

public class SystemWebSocketHandler extends TextWebSocketHandler {


    public static final ArrayList<WebSocketBean> users = new ArrayList<WebSocketBean>();;


    public static  void sendMessage(Map<String,Object> map) {
        if (users.size() > 0) {
            String devicenumber= (String) map.get("devicenumber");

            for(int i=0;i<users.size();i++){
                if(devicenumber.equals(users.get(i).getGetway())){
                    WebSocketSession session = users.get(i).getSession();
                    String str = JsonUtil.toJson(map);
                    //发出去内容
                    CharBuffer toMsg = CharBuffer.wrap(str.toCharArray());
                    TextMessage message = new TextMessage(toMsg);
                    try {
                        session.sendMessage(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	System.out.println("ConnectionEstablished");
        users.add(new WebSocketBean(session));
        System.out.println("当前用户"+users.size());

//        Demo1Server demo1Server = new Demo1Server();
//        demo1Server.execute();

    }
    /**
     * 在UI在用js调用websocket.send()时候，会调用该方法
     * @Author	张志朋
     * @param session
     * @param message
     * @throws Exception
     * @Date	2016年3月4日
     * 更新日志
     * 2016年3月4日 张志朋  首次创建
     *
     */
	@Override
	protected void handleTextMessage(WebSocketSession session,
			TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
//		sendMessageToUsers(session,message);

        System.out.println("收到来自websocket的消息:"+message.toString());
        String msg = message.getPayload().toString();
        for(int i=0;i<users.size();i++){
            if(users.get(i).getSession().equals(session)){
                users.get(i).setGetway(msg);
            }
        }

    }
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws IOException {
        if(session.isOpen()){
        	session.close();
        }
        System.out.println("close");
        users.remove(session);
    }
 
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        users.remove(session);
        System.out.println("close222");
    }
 
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
    /**
     * 给所有在线用户发送消息
     * @Author	张志朋
     * @param message  void
     * @Date	2016年3月4日
     * 更新日志
     * 2016年3月4日 张志朋  首次创建
     *
     */
//    public void sendMessageToUsers(WebSocketSession session, TextMessage message) {
//        for (WebSocketSession user : users) {
//            try {
//                if (user.isOpen()) {
//                	user.sendMessage(message);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }



}