package com.mmtzj.netty;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

public class TimeDecoder extends FrameDecoder {

    //    FrameDecoder
//    @Override
//    protected Object decode(
//            ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer)  {
//
//        if (buffer.readableBytes() < 4) {
//            return null;
//        }
//
//        return buffer.readBytes(4);
//    }

//    ReplayingDecoder<VoidEnum>
//    @Override
//    protected Object decode(
//            ChannelHandlerContext ctx, Channel channel,
//            ChannelBuffer buffer, VoidEnum state) {
//
//        return buffer.readBytes(4);
//    }

    @Override
    protected Object decode(
            ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) {
        if (buffer.readableBytes() < 4) {
            return null;
        }

        return new UnixTime(buffer.readInt());
    }
}  