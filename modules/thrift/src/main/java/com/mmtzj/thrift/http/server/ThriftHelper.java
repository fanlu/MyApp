package com.mmtzj.thrift.http.server;

import com.mmtzj.thrift.http.AopTargetUtils;
import org.apache.thrift.TProcessor;


/**
 * Created with IntelliJ IDEA.
 * User: wangxin
 * Date: 13-2-27
 * Time: 下午9:20
 * To change this template use File | Settings | File Templates.
 */
public class ThriftHelper {
    public static TProcessor buildProcessor(Class serviceInterface, Object proxyForService) throws Exception {
        Object target = AopTargetUtils.getTarget(proxyForService);
        Class c = Class.forName(target.getClass().getInterfaces()[0].getName().replace("Iface", "Processor"));
        TProcessor processor = (TProcessor) c.getConstructor(serviceInterface).newInstance(proxyForService);
//        TProcessor processor1 = new UserService.Processor((UserService.Iface) proxyForService);
        return processor;
    }
}
