package com.mmtzj.thrift.server;

import com.mmtzj.thrift.gen.UserService;
import org.apache.thrift.TProcessor;

/**
 * Created with IntelliJ IDEA.
 * User: wangxin
 * Date: 13-2-27
 * Time: 下午9:20
 * To change this template use File | Settings | File Templates.
 */
public class ThriftHelper {
    public static TProcessor buildProcessor(Class serviceInterface, Object proxyForService) {
        UserService.Processor processor = new UserService.Processor((UserService.Iface) proxyForService);
        return processor;
    }
}
