package com.mmtzj.netty;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.ChannelGroupFuture;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class TimeServer {

    static final ChannelGroup allChannels = new DefaultChannelGroup("time-server");

    public static void main(String[] args) throws Exception {
        ChannelFactory factory =
                new NioServerSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool());

        ServerBootstrap bootstrap = new ServerBootstrap(factory);

        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new TimeEncoder(),
                        new TimeServerHandler());
            }
        });

        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);

        Channel channel = bootstrap.bind(new InetSocketAddress(8000));

        allChannels.add(channel);
//        waitForShutdownCommand();
//        ChannelGroupFuture future = allChannels.close();
//        future.awaitUninterruptibly();
//        factory.releaseExternalResources();
    }

    private static void waitForShutdownCommand() throws IOException {
//        System.in.read();
    }
}