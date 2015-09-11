package com.mmtzj.netty;

import org.jboss.netty.channel.*;

public class TimeServerHandler extends SimpleChannelHandler {
  
//    @Override
//    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
//        Channel ch = e.getChannel();
//
//        ChannelBuffer time = ChannelBuffers.buffer(4);
//        Long l = System.currentTimeMillis();
//        System.out.println(l);
//        time.writeInt((int) (l/ 1000));
//
//        ChannelFuture f = ch.write(time);
//
//        f.addListener(new ChannelFutureListener() {
//            public void operationComplete(ChannelFuture future) {
//                Channel ch = future.getChannel();
//                ch.close();
//            }
//        });
////        f.addListener(ChannelFutureListener.CLOSE);
//    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        Long l = System.currentTimeMillis();
        System.out.println(l);
        UnixTime time = new UnixTime((int) (l / 1000));
        ChannelFuture f = e.getChannel().write(time);
        f.addListener(ChannelFutureListener.CLOSE);
    }

    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {   
        e.getCause().printStackTrace();   
        e.getChannel().close();   
    }

    @Override
    public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) {
        TimeServer.allChannels.add(e.getChannel());
    }
}