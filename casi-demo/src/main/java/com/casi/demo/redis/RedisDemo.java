package com.casi.demo.redis;

import redis.clients.jedis.Jedis;

/**
 * User: hadoop
 * Date: 12-8-4
 * Time: 下午9:17
 */
public class RedisDemo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        jedis.set("foo", "bar");

        String value = jedis.get("foo");
        System.out.println(jedis.get("foo"));
    }
}