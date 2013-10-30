package com.casi.demo;


import com.casi.commons.util.StringUtil;

import java.lang.reflect.InvocationTargetException;

/**
 * User: Think
 * Date: 13-10-30
 * Time: 下午7:46
 */
public class BeanUtilDemo {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        OneDO one=new OneDO();
        one.setAddress("address-1");
        one.setAge(1);
        one.setName("one");
        TwoDO two=new TwoDO();
        String str="";
        long start=System.nanoTime();
//        org.apache.commons.beanutils.BeanUtils.copyProperties(two,one);
//        org.springframework.beans.BeanUtils.copyProperties(one,two);
        if (str.isEmpty()){
        }
        System.out.println(System.nanoTime()-start);
        TwoDO two2=new TwoDO();
        start=System.nanoTime();
        if ( str==""){

        }
//        two2.setName(one.getName());
//        two2.setAge(one.getAge());
        System.out.println(System.nanoTime()-start);


    }
}
