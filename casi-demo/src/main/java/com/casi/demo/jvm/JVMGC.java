package com.casi.demo.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Think
 * Date: 12-8-22
 * Time: 下午10:26
 */
public class JVMGC {
//-Xms30m -Xmx30m -Xmn10m -XX:+UseParallelGC -XX:+PrintGCDetails
    public static void main(String[] args) {
        List<Object> caches=new ArrayList<Object>();
        for(int i=0;i<7;i++){
            caches.add(new byte[1024*1024*3]);
            }
        caches.clear();
        for(int i=0;i<2;i++){
            caches.add(new byte[1024*1024*3]);
            }
        }
}
