package com.casi.demo.thread;
import java.util.concurrent.atomic.AtomicLong;

/**
 * User: Think
 * Date: 12-9-19
 * Time: 下午8:39
 */
public class ProductThread extends Thread {


    private static AtomicLong index = new AtomicLong(0);

    @Override
    public void run() {
        long index = this.index.incrementAndGet();
        Bread bread = new Bread();
        bread.setIndex(index);
        bread.setName("bread--" + index);
        bread.setType("red");
        bread.setSize(10);
        bread.setWeight(12);
        try {
            Box.getInstance().set(bread);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
