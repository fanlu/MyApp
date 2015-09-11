package com.mmtzj.netty;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class TimeClientHandler extends SimpleChannelHandler {
  
//    @Override
//    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
//        ChannelBuffer buf = (ChannelBuffer) e.getMessage();
//        long currentTimeMillis = buf.readInt() * 1000L;
//        System.out.println(new Date(currentTimeMillis));
//        e.getChannel().close();
//
//    }


//    private final ChannelBuffer buf = dynamicBuffer();
//
//    @Override
//    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
//        ChannelBuffer m = (ChannelBuffer) e.getMessage();
//        buf.writeBytes(m);
//
//        if (buf.readableBytes() >= 4) {
//            long currentTimeMillis = buf.readInt() * 1000L;
//            System.out.println(new Date(currentTimeMillis));
//            e.getChannel().close();
//        }
//    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        UnixTime m = (UnixTime) e.getMessage();
        System.out.println(m);
        e.getChannel().close();
    }

    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        e.getCause().printStackTrace();   
        e.getChannel().close();   
    }   
}  