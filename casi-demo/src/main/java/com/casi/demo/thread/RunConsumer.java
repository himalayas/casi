package com.casi.demo.thread;

import java.util.concurrent.*;

/**
 * User: Think
 * Date: 12-9-19
 * Time: 下午10:49
 */
public class RunConsumer extends Thread {
    private static final int corePoolSize = 10;
    private static final int maximumPoolSize = 15;
    private static final long keepAliveTime = 1000 * 10;
    private static final TimeUnit timeUnit = TimeUnit.SECONDS;
    private static final BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(20);

    @Override
    public void run() {
        ThreadPoolExecutor consumePool = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                timeUnit,
                workQueue,
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 10000; i++) {
            consumePool.execute(new ConsumerThread());
        }
    }
}
