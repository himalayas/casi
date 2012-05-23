package com.casi.demo.netty.client;

import com.casi.demo.netty.api.IAnimalService;
import com.casi.demo.netty.api.Request;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * User: David Guo
 * Date: 12-2-7
 * Time: 下午1:25
 */
public class Client {
    public static void main(String[] args) {
        ClientBootstrap client = new ClientBootstrap(
                        new NioClientSocketChannelFactory(Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));
        client.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeLine = Channels.pipeline(new SimpleChannelUpstreamHandler() {
                    @Override
                    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
                        //创建连接发送请求
                        Request r = new Request();
                        //设置版本
                        r.setVersion("1.0.0");
                        //设置服务类型
                        r.setService(IAnimalService.class);
                        //调用服务方法名称
                        r.setMethod("getMoneyName");
                        //参数
                        r.setParas(null);
                        e.getChannel().write(r);
                    }
                    @Override
                    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception{
                        //监听消息到达
                        System.out.println(e.getMessage().toString());
                    }
                });
                //对象编码器
                pipeLine.addFirst("encoder", new ObjectEncoder());
                //对象解码器
                pipeLine.addFirst("decoder", new ObjectDecoder());
                return pipeLine;
            }
        });
        client.setOption("tcpNoDelay", true);
        client.setOption("keepAlive", true);
        ChannelFuture future = client.connect(new InetSocketAddress("127.0.0.1", 9990));
        future.getChannel().getCloseFuture().awaitUninterruptibly();
        //释放外部资源
        client.releaseExternalResources();
    }
}
