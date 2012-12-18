package com.casi.demo.netty.server;

import com.casi.demo.netty.api.IAnimalService;
import com.casi.demo.netty.api.Request;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * User: David Guo
 * Date: 12-2-7
 * Time: 下午1:19
 */
public class Server {
    public static void main(String[] args) throws UnknownHostException {
        //配置监听端口
        final int port = 9990;
        ThreadPoolExecutor bossExecutor =new ThreadPoolExecutor(2,5,1000, TimeUnit.MINUTES,new LinkedBlockingQueue(100));
        ThreadPoolExecutor workerExecutor =new ThreadPoolExecutor(2,5,1000, TimeUnit.MINUTES,new LinkedBlockingQueue(100));

        //server启动引导API
        ServerBootstrap bootstrap = new ServerBootstrap( new NioServerSocketChannelFactory(bossExecutor,workerExecutor));

        bootstrap.setPipelineFactory(new ChannelPipelineFactoryImpl());
        //启动服务并绑定端口
        bootstrap.bind(new InetSocketAddress("127.0.0.1",port));
    }
}
