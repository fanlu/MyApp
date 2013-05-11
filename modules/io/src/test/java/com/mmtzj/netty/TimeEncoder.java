package com.mmtzj.netty;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.*;

import static org.jboss.netty.buffer.ChannelBuffers.*;
  
@ChannelPipelineCoverage("all")
public class TimeEncoder extends SimpleChannelHandler {
  
    public void writeRequested(ChannelHandlerContext ctx, MessageEvent e) {
        UnixTime time = (UnixTime) e.getMessage();   
          
        ChannelBuffer buf = buffer(4);
        buf.writeInt(time.getValue());   
          
        Channels.write(ctx, e.getFuture(), buf);
    }   
}