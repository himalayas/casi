package com.casi.demo;

import org.apache.solr.handler.MoreLikeThisHandler;

/**
 * Created with IntelliJ IDEA.
 * User: kevin
 * Date: 12-10-15
 * Time: 上午11:16
 * To change this template use File | Settings | File Templates.
 */
public class ChangeInteger extends Thread {
    Integer integer;

    public ChangeInteger(Integer integer) {
        this.integer = integer;
    }

    @Override
    public void run() {
       for(int i=0;i<5;i++){
          integer=integer++;
       }
    }
}
