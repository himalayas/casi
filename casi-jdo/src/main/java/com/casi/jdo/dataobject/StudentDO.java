package com.casi.jdo.dataobject;

import com.casi.jdo.base.BaseDO;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import java.util.Date;

@PersistenceCapable
@Inheritance(strategy= InheritanceStrategy.NEW_TABLE)
public class StudentDO extends BaseDO {
    private static final long serialVersionUID = 3959377496669050427L;
    public StudentDO() {
    }
    @Persistent
    private String  name;
    @Persistent
    private int age;
    @Persistent
    private Date birthday;
    @Persistent
    private String address;
    @Persistent
    private String school;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentDO)) return false;

        StudentDO studentDO = (StudentDO) o;

        if (age != studentDO.age) return false;
        if (address != null ? !address.equals(studentDO.address) : studentDO.address != null) return false;
        if (birthday != null ? !birthday.equals(studentDO.birthday) : studentDO.birthday != null) return false;
        if (name != null ? !name.equals(studentDO.name) : studentDO.name != null) return false;
        if (school != null ? !school.equals(studentDO.school) : studentDO.school != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (school != null ? school.hashCode() : 0);
        return result;
    }
}
