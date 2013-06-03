package com.casi.demo.singleton;

/**
 * User: xiujguo
 * Date: 13-5-19
 * Time: 下午6:34
 */
public class Singleton {
    private static volatile Singleton singleton;

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
