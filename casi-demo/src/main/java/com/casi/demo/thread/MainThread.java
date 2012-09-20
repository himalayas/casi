package com.casi.demo.thread;

import java.util.concurrent.*;

/**
 * User: Think
 * Date: 12-9-19
 * Time: 下午8:53
 */
public class MainThread {


    public static void main(String[] args) {
        RunConsumer rc= new RunConsumer();
        rc.start();
        RunProduct rp=new RunProduct();
        rp.start();
    }
}
