package com.casi.demo.thread;

/**
 * User: Think
 * Date: 12-9-19
 * Time: 下午8:45
 */
public class ConsumerThread extends Thread {
    @Override
    public void run() {
        try {
            Box.getInstance().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
