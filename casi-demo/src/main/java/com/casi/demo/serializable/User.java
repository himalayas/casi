package com.casi.demo.serializable;

import java.io.Serializable;

/**
 * User: Think
 * Date: 12-10-22
 * Time: 下午8:19
 */
public class User implements Serializable {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
