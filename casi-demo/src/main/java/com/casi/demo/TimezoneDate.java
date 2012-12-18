package com.casi.demo;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created with IntelliJ IDEA.
 * User: kevin
 * Date: 12-8-14
 * Time: 上午9:50
 * To change this template use File | Settings | File Templates.
 */
public class TimezoneDate {
    public static void main(String[] args) {
        Calendar  calendar=Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT-8"));
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        System.out.println(calendar.getTime());
    }
}
