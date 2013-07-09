package com.casi.demo.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.net.ConnectException;

/**
 * User: hadoop
 * Date: 12-8-4
 * Time: 下午9:17
 */
public class RedisDemo {
    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis("10.224.57.118", 6379);

        JedisPubSub jedisPubSub = new JedisPubSubImpl();
        while (true)
            try {
                jedis.subscribe(jedisPubSub, "pubdemo");
            } catch (JedisConnectionException e) {
                System.out.println("connect is " + jedis.isConnected() + " " + e.getLocalizedMessage());
                Thread.sleep(1000);
                jedis.disconnect();
            }
    }
}