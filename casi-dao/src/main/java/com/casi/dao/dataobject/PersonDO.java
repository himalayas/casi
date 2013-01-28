package com.casi.dao.dataobject;

import com.casi.commons.util.Page;

import java.util.Date;

/**
 * User: hadoop
 * Date: 12-1-11
 * Time: 下午4:58
 */
public class PersonDO extends Page {
    private int id;
    private String name;
    private int age;
    private String address;
    private Date birthday;
    private String school;


    public PersonDO(String name, int age, String address, Date birthday, String school) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.birthday = birthday;
        this.school = school;
    }

    public PersonDO() {
    }

    //mysql分页
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonDO indexDO = (PersonDO) o;

        if (age != indexDO.age) return false;
        if (address != null ? !address.equals(indexDO.address) : indexDO.address != null) return false;
        if (name != null ? !name.equals(indexDO.name) : indexDO.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PersonDO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
