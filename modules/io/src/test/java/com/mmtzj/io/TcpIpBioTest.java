package com.mmtzj.io;

import org.junit.Test;

import java.io.*;
import java.net.Socket;
/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-5-10
 * Time: 下午10:26
 * To change this template use File | Settings | File Templates.
 */
public class TcpIpBioTest {

    @Test
    public void test() throws IOException {
        Socket socket = new Socket("127.0.0.1",11111);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        dos.writeUTF("我是客户端，请求连接!");
        System.out.println(dis.readUTF());
        socket.close();
    }
}
