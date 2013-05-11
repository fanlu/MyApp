package com.mmtzj.netty;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;

public class TimeClientPipelineFactory implements ChannelPipelineFactory {
  
    public ChannelPipeline getPipeline() {
        ChannelPipeline pipeline = Channels.pipeline();
        pipeline.addLast("handler", new TimeClientHandler());
        return pipeline;
    }
}