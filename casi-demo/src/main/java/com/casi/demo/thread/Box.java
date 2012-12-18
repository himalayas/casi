package com.casi.demo.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * User: Think
 * Date: 12-9-19
 * Time: 下午8:54
 */
public class Box {
    private static Box box;
    private ArrayBlockingQueue<Bread> blockingQueue;
    private final int boxSize=50;
    private Box(){
        blockingQueue=new ArrayBlockingQueue<Bread>(boxSize);
    }
    public  static Box getInstance(){
        if(box==null){
            box=new Box();
        }
        return box;
    }
    public void set(Bread bread) throws InterruptedException {
        blockingQueue.put(bread);
        System.out.println("PUT: "+bread.toString());
    }
    public Bread get() throws InterruptedException {
         Bread bread= blockingQueue.take();
         System.out.println("GET: "+bread.toString());
         return bread;
    }

}
