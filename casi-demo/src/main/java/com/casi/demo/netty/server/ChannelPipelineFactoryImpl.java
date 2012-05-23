package com.casi.demo.netty.server;

import com.casi.demo.netty.api.IAnimalService;
import com.casi.demo.netty.api.Request;
import org.jboss.netty.channel.*;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.Method;

/**
 * User: David Guo
 * Date: 12-2-13
 * Time: 上午10:10
 */
public class ChannelPipelineFactoryImpl implements ChannelPipelineFactory {
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
}
