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

        //
        ThreadPoolExecutor bossExecutor =new ThreadPoolExecutor(2,5,1000, TimeUnit.MINUTES,new LinkedBlockingQueue(100));
        ThreadPoolExecutor workerExecutor =new ThreadPoolExecutor(2,5,1000, TimeUnit.MINUTES,new LinkedBlockingQueue(100));

        //server启动引导API
        ServerBootstrap bootstrap = new ServerBootstrap( new NioServerSocketChannelFactory(bossExecutor,workerExecutor));

        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeLine = Channels.pipeline(new SimpleChannelUpstreamHandler() {
                    @Override
                    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
                        //监听消息到达
                        Request obj = (Request) e.getMessage();
                        if (obj.getService().equals(IAnimalService.class)) {
                            Method targetMethod = obj.getService().getMethod(obj.getMethod(), new Class[0]);
                            Object result = targetMethod.invoke(new AnimalServiceImp(), obj.getParas());
                            e.getChannel().write(result);
                        }
                    }
                });
                //对象编码器
                pipeLine.addFirst("encoder", new ObjectEncoder());
                //对象解码器
                pipeLine.addFirst("decoder", new ObjectDecoder());

                return pipeLine;
            }
        });
        //启动服务并绑定端口
        bootstrap.bind(new InetSocketAddress("127.0.0.1",port));
    }
}
