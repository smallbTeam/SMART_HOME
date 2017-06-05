/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.test.bean;

import java.util.Date;

/**
 * @author ligw
 * @version $Id TestDTO.java, v 0.1 2017-05-24 22:39 ligw Exp $$
 */
public class TestDTO {
    private String name;
    private Integer age;
    private Date birthDay;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Override public String toString() {
        return "ExampleClass{" + "name='" + name + '\'' + ", age=" + age + ", birthDay=" + birthDay + '}';
    }
}
