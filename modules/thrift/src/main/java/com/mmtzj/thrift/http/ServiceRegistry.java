package com.mmtzj.thrift.http;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-3-7
 * Time: 下午8:44
 * To change this template use File | Settings | File Templates.
 */
public class ServiceRegistry {
    public ServiceReference getService(Class serviceInterface) throws IllegalAccessException, InstantiationException {
        return (ServiceReference) serviceInterface.newInstance();
    }

//    public void exportService(Class serviceInterface, void proxyForService0) {
//        //To change body of created methods use File | Settings | File Templates.
//    }
}
