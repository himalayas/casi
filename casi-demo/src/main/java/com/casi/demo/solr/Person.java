package com.casi.demo.solr;

import org.apache.solr.client.solrj.beans.Field;

/**
 * User: David
 * Date: 12-4-3
 * Time: 下午2:52
 */
public class Person {
    @Field("name")
    String name;
    @Field("address")
    String address;
    @Field("school")
    String school;
    @Field("age")
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
