package com.casi.demo.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * Created with IntelliJ IDEA.
 * User: kevin
 * Date: 13-7-9
 * Time: 下午1:28
 * To change this template use File | Settings | File Templates.
 */
public class RedisClientDemo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("10.224.57.118",6379);
        jedis.publish("pubdemo","message");
        jedis.sadd("set-1","v-1","v-2","v-3","v-4");

        jedis.lpush("list-1","v-1","v-2","v-3","v-4");
    }
}
