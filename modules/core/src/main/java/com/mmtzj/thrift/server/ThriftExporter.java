package com.mmtzj.thrift.server;

import org.aopalliance.intercept.MethodInterceptor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.remoting.support.RemoteExporter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wangxin
 * Date: 13-2-27
 * Time: 下午9:13
 * To change this template use File | Settings | File Templates.
 */
public class ThriftExporter extends RemoteExporter implements InitializingBean {

    private Object processor;

    public void afterPropertiesSet() throws Exception {
        if (serviceRegistry != null) {
            List<MethodInterceptor> il = new ArrayList<MethodInterceptor>();
            il.add(new ClassLoaderInterceptor(getBeanClassLoader()));
            if (threadResourceManager != null) {
                il.add(new ThreadResourceSupportInterceptor(threadResourceManager));
            }
            serviceRegistry.exportService(getServiceInterface(), getProxyForService0(il));
        }
        super.setInterceptors(ArrayUtils.add(interceptors, new AnyExceptionConvertInterceptor()));
        this.processor = ThriftHelper.buildProcessor(getServiceInterface(), getProxyForService());
    }
}
