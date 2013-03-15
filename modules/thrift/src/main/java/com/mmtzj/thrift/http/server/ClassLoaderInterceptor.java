package com.mmtzj.thrift.http.server;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-3-9
 * Time: 下午8:17
 * To change this template use File | Settings | File Templates.
 */
public class ClassLoaderInterceptor implements MethodInterceptor {
    public ClassLoaderInterceptor(ClassLoader beanClassLoader) {
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
