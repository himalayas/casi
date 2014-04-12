package com.casi.demo;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: hadoop
 * Date: 12-2-20
 * Time: 下午3:17
 */
public class JavaDemo {
    public static void main(String[] args) {

        String str="i love china";
        int ii=8;
        Integer j=new Integer(8);
        if(ii==j)
            System.out.println("1 true");
        if(j==ii)
            System.out.println("2 true");

        System.out.println(Integer.toBinaryString(10));
        System.out.println(16>>2);



        Map map=new HashMap();
        Integer data1 =1;
        Integer data2 = 1;
        //whatever m_isInValue is, MeetingCount alert cares only m_threshold[MEETINGTOTAL]
        Integer data3 = new Integer(2);

        //int threshold = data3 * data1 / data2;
        System.out.println((data3/100.0));





        AtomicInteger i=new AtomicInteger(1);
        test(i);
        System.out.println(i.get());

        Calendar c=Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        System.out.println(c.get(Calendar.HOUR_OF_DAY));

    }
    public static void test(AtomicInteger i){
        i.incrementAndGet();
        System.out.println(i.get());
    }

}
