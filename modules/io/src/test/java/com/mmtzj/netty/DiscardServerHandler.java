package com.mmtzj.netty;

import org.jboss.netty.channel.*;

public class DiscardServerHandler extends SimpleChannelHandler {
  
    @Override  
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
//        ChannelBuffer  buf = (ChannelBuffer) e.getMessage();
//        while(buf.readable()) {
//            System.out.println((char) buf.readByte());
//        }

        Channel  ch = e.getChannel();
        ch.write(e.getMessage());
    }   
  
    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        e.getCause().printStackTrace();   
          
        Channel ch = e.getChannel();   
        ch.close();   
    }   
}  