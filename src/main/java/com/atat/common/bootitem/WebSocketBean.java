package com.atat.common.bootitem;

import org.springframework.web.socket.WebSocketSession;

/**
 * Created by Administrator on 2017/7/5.
 */
public class WebSocketBean {

    private WebSocketSession session;
    private String getway;

    @Override
    public String toString() {
        return "WebSocketBean{" +
                "session=" + session +
                ", getway='" + getway + '\'' +
                '}';
    }


    public WebSocketBean(WebSocketSession session) {
        this.session = session;
    }


    public WebSocketBean(WebSocketSession session, String getway) {
        this.session = session;
        this.getway = getway;
    }



    public WebSocketSession getSession() {
        return session;
    }

    public void setSession(WebSocketSession session) {
        this.session = session;
    }

    public String getGetway() {
        return getway;
    }

    public void setGetway(String getway) {
        this.getway = getway;
    }


}
