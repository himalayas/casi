package com.casi.demo;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: hadoop
 * Date: 12-2-20
 * Time: 下午3:17
 */
public class JavaDemo {
    public static void main(String[] args) {
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
