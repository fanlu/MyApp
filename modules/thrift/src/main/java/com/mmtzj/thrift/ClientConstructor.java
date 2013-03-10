package com.mmtzj.thrift;

import com.mmtzj.thrift.gen.UserService;
import org.apache.thrift.protocol.TProtocol;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-3-7
 * Time: 下午8:43
 * To change this template use File | Settings | File Templates.
 */
public class ClientConstructor {

    public Object newInstance(TProtocol protocol) {
        UserService.Client client = new UserService.Client(protocol);
        return client;
    }
}
