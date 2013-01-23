package com.casi.demo;

import org.omg.CORBA.FloatSeqHelper;

/**
 * Created with IntelliJ IDEA.
 * User: kevin
 * Date: 12-10-15
 * Time: 上午10:33
 * To change this template use File | Settings | File Templates.
 */
public class ChangeBoolean extends Thread {
    BooleanDO  bo;
    public ChangeBoolean(BooleanDO bo) {
        this.bo=bo;
    }

    @Override
    public void run() {
        try {
            System.out.println("sleep");
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(bo.bo)
       bo.bo= false;
        else
        bo.bo=true;
        System.out.println(bo.bo);
    }
}
