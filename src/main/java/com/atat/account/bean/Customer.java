/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.account.bean;

import java.util.Date;

/**
 * @author ligw
 * @version $Id Customer.java, v 0.1 2017-06-06 4:34 ligw Exp $$
 */
public class Customer {
    private Integer MobelPhone;
    private String password;
    private String wxId;
    private String nickName;
    private Integer sex;
    private Date birthday;

    public Integer getMobelPhone() {
        return MobelPhone;
    }

    public void setMobelPhone(Integer mobelPhone) {
        MobelPhone = mobelPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
