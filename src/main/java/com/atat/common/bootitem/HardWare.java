package com.atat.common.bootitem;

import com.sun.deploy.util.SessionState;
import org.apache.mina.core.session.IoSession;

/**
 * Created by Administrator on 2017/6/15.
 */
public class HardWare {

    public HardWare (){};

    private String number;
    private int count;

    public HardWare(int count) {
        this.count = count;
    }


    public HardWare(String number,int count) {
        this.number = number;
        this.count = count;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}
