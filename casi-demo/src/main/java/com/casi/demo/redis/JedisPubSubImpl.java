package com.casi.demo.redis;

import redis.clients.jedis.JedisPubSub;

/**
 * Created with IntelliJ IDEA.
 * User: kevin
 * Date: 13-7-9
 * Time: 下午1:25
 * To change this template use File | Settings | File Templates.
 */
public class JedisPubSubImpl extends JedisPubSub {
    @Override
    public void onMessage(String channel, String message) {
        System.out.println(message);
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
