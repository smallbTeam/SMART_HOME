package com.atat.common.bootitem;

import com.sun.deploy.util.SessionState;
import org.apache.mina.core.session.IoSession;

/**
 * Created by Administrator on 2017/6/15.
 */
public class HardWare {

    public HardWare (){};


    private IoSession session;
    private int number;

    public HardWare(IoSession session, int number) {
        this.session = session;
        this.number = number;
    }

    public IoSession getSession() {
        return session;
    }

    public void setSession(IoSession session) {
        this.session = session;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }



}
