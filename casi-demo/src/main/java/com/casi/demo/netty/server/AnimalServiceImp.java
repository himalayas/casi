package com.casi.demo.netty.server;

import com.casi.demo.netty.api.IAnimalService;
/**
 * User: David Guo
 * Date: 12-2-7
 * Time: 下午1:18
 */
public class AnimalServiceImp implements IAnimalService{
    @Override
    public String getMoneyName() {
        return "I'am Jackey";
    }
}
