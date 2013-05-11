package com.mmtzj.io;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-5-10
 * Time: 下午10:49
 * To change this template use File | Settings | File Templates.
 */
public class TcpIpNioTest {

    @Test
    public void test() throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress(11112));

        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_CONNECT);

        int nKeys = selector.select(3000);
        if(nKeys > 0){
            Set<SelectionKey> keys = selector.selectedKeys();
            for(SelectionKey key: keys){
                if(key.isConnectable()){
                    SocketChannel sc = (SocketChannel) key.channel();
                    sc.configureBlocking(false);
                    SelectionKey sKey = sc.register(selector, SelectionKey.OP_READ);
                    sc.finishConnect();
                }else if(key.isReadable()){
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    SocketChannel sc = (SocketChannel) key.channel();
                    int readBytes = 0;
                    try{
                        int ret = 0;
                        try{
                            while ((ret = sc.read(buffer)) > 0){
                                readBytes += ret;
                            }
                        }finally {
                            buffer.flip();
                        }
                    }finally {
                        if(buffer != null){
                            buffer.clear();
                        }
                    }
                }else if(key.isWritable()){
                    key.interestOps(key.interestOps() & (~SelectionKey.OP_WRITE));
                    SocketChannel sc = (SocketChannel) key.channel();
                    int writtenedSize = sc.write(ByteBuffer.allocate(1024));
                    if(writtenedSize == 0){
                        key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
                    }

//                    int wSize = channel.write(ByteBuffer.allocate(1024));
//                    if(wSize == 0){
//                        key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
//                    }
                }
            }
            selector.selectedKeys().clear();
        }

    }
}
