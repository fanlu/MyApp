package com.mmtzj.thrift.http;

import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TProtocol;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-3-7
 * Time: 下午8:43
 * To change this template use File | Settings | File Templates.
 */
public class ClientConstructor {

    public Object newInstance(TProtocol protocol, Class serviceInterface) throws Exception {
        Class c = Class.forName(serviceInterface.getName().replace("Iface", "Client"));
        TServiceClient client = (TServiceClient) c.getConstructor(TProtocol.class).newInstance(protocol);
//        TServiceClient client1 = new UserService.Client(protocol);
        return client;
    }
}
