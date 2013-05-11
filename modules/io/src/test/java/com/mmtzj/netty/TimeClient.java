package com.mmtzj.netty;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;   
  
public class TimeClient {   
  
    public static void main(String[] args) throws Exception {   
        String host = args[0];   
        int port = Integer.parseInt(args[1]);   
  
        ChannelFactory factory =
            new NioClientSocketChannelFactory(
                    Executors.newCachedThreadPool(),   
                    Executors.newCachedThreadPool());   
  
        ClientBootstrap bootstrap = new ClientBootstrap (factory);
  
//        TimeClientHandler handler = new TimeClientHandler();
//        bootstrap.getPipeline().addLast("handler", handler);
        bootstrap.setPipelineFactory(new TimeClientPipelineFactory());

        bootstrap.setOption("tcpNoDelay" , true);   
        bootstrap.setOption("keepAlive", true);

        ChannelFuture future  =bootstrap.connect (new InetSocketAddress(host, port));
        future.awaitUninterruptibly();
        if (!future.isSuccess()) {
            future.getCause().printStackTrace();
        }
        future.getChannel().getCloseFuture().awaitUninterruptibly();
        factory.releaseExternalResources();
    }   
} 