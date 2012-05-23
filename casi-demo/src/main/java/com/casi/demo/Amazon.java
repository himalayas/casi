package com.casi.demo;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * User: David
 * Date: 12-3-11
 * Time: 下午3:34
 */
public class Amazon {
    static ConcurrentHashMap<Integer, Max> map;
    public final static int processors = Runtime.getRuntime().availableProcessors();
    static int array[];
    static CountDownLatch countDownLatch = new CountDownLatch(processors);

    static int key;
    static int count;
    static int sum;

    public static void main(String[] args) throws InterruptedException {

        System.out.println("processors is:" + processors);
        array = new int[100000000];

        int length = array.length;
        for(int i=0;i<length;i++){
            array[i]= (int)(Math.random()*1000000);
        }
        System.out.println("length is :" + length);
        map = new ConcurrentHashMap<Integer, Max>(length / 2);
        int step = length / processors;
        System.out.println("step is :"+step);
        int start=0;
        int end=start+step;
        long s=System.currentTimeMillis();
        for (int i = 0; i < processors; i++) {
            new Sum(start, end-1,"Name"+i).start();
            start = end;
            end=start+step;
            if (end>= length)
                end = length;
        }
        countDownLatch.await();
        System.out.println((System.currentTimeMillis()-s)/1000);
        System.out.println(key + ":" + count + ":" + sum);
    }

    private static class Sum extends Thread {
        private int start;
        private int end;
        private String name;

        Sum(int start, int end,String name) {
            this.start = start;
            this.end = end;
            this.name=name;
        }

        @Override
        public void run() {
            for (; start <= end; start++) {
                if (map.get(array[start]) == null) {
                    map.put(array[start], new Max(array[start], 1));
                } else {
                    synchronized (map.get(array[start])) {
                        Max temp = map.get(array[start]);
                        temp.setSum(temp.getSum() + array[start]);
                        temp.setCount(temp.getCount()+1);
                        if (temp.getCount() > count || (temp.getCount() == count && temp.getSum() > sum)) {
                            key = array[start];
                            count = temp.getCount();
                            sum = temp.getSum();
                        }
                    }
                }
            }
            countDownLatch.countDown();
        }
    }

    static class Max {
        private int sum;
        private int count;

        Max(int sum, int count) {
            this.sum = sum;
            this.count = count;
        }

        synchronized public int getSum() {
            return sum;
        }

        synchronized public void setSum(int sum) {
            this.sum = sum;
        }

        synchronized public int getCount() {
            return count;
        }

        synchronized public void setCount(int count) {
            this.count = count;
        }
    }
}