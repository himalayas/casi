package com.casi.jdo.dataobject;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import java.util.Date;

@PersistenceCapable(table = "person")
public class StudentDO {
    @PrimaryKey
    @Persistent
    @Column(name="id")
    private long id;
    @Persistent
    @Column(name="name")
    private String  name;
    @Persistent
    @Column(name="age")
    private int age;
    @Persistent
    @Column(name="birthday")
    private Date birthday;
    @Persistent
    @Column(name="address")
    private String address;
    @Persistent
    @Column(name="school")
    private String school;

    public StudentDO(String name, int age, String address, Date birthday, String school) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.birthday = birthday;
        this.school = school;
    }

    public StudentDO() {
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
