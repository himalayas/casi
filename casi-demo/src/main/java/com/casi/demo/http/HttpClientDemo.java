package com.casi.demo.http;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * User: hadoop
 * Date: 12-1-18
 * Time: 上午9:15
 */
public class HttpClientDemo {
    public static void main(String[] args) {
        int corePoolSize=100;
        int maximumPoolSize=120;
        long keepAliveTime=1000;
        TimeUnit unit=TimeUnit.MILLISECONDS;
        BlockingQueue<Runnable> workQueue=new LinkedBlockingDeque<Runnable>();
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(corePoolSize,
                                                                    maximumPoolSize,
                                                                    keepAliveTime,
                                                                    unit,
                                                                    workQueue);

        for(int i=0;i<10000;i++){
            threadPoolExecutor.execute(new Thread(new ClientThread()));
        }
    }
}
