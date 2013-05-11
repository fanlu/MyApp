package com.mmtzj.netty;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class TimeServer {
  
    public static void main(String[] args) throws Exception {   
        ChannelFactory factory =
            new NioServerSocketChannelFactory(
                    Executors.newCachedThreadPool(),   
                    Executors.newCachedThreadPool());   
  
        ServerBootstrap bootstrap = new ServerBootstrap (factory);
  
        TimeServerHandler handler = new TimeServerHandler();
        TimeEncoder encoder = new TimeEncoder();
        ChannelPipeline pipeline = bootstrap.getPipeline();
//        pipeline.addLast("handler", handler);
        pipeline.addLast("handler", encoder);
  
        bootstrap.setOption("child.tcpNoDelay", true);   
        bootstrap.setOption("child.keepAlive", true);   
  
        bootstrap.bind(new InetSocketAddress(8000));
    }   
}