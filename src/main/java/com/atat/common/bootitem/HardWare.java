package com.atat.common.bootitem;

import com.sun.deploy.util.SessionState;
import org.apache.mina.core.session.IoSession;

/**
 * Created by Administrator on 2017/6/15.
 */
public class HardWare {

    public HardWare (){};


    private IoSession session;
    private String number;

    public HardWare(IoSession session) {
        this.session = session;
    }

    public HardWare(IoSession session, String number) {
        this.session = session;
        this.number = number;
    }

    public IoSession getSession() {
        return session;
    }

    public void setSession(IoSession session) {
        this.session = session;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }



}
