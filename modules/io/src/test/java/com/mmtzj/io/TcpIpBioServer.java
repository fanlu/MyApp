package com.mmtzj.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-5-10
 * Time: 下午10:36
 * To change this template use File | Settings | File Templates.
 */
public class TcpIpBioServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(11111);
        Socket s  = ss.accept();
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        DataInputStream dis = new DataInputStream(s.getInputStream());
        System.out.println("服务器接收到客户端的连接请求：" + dis.readUTF());

        dos.writeUTF("接受连接请求，连接成功!");

        s.close();
        ss.close();
    }
}
