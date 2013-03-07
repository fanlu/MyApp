package com.mmtzj.thrift;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created with IntelliJ IDEA.
 * User: wangxin
 * Date: 13-2-27
 * Time: 下午8:35
 * To change this template use File | Settings | File Templates.
 */
public class ThriftHttpProxyFactoryBean<T> extends ThriftClientInterceptor implements FactoryBean<T> {

    private static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 100;
    private static final int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 10;
    private static final int DEFAULT_READ_TIMEOUT_MILLISECONDS = 60;
    private HttpClient httpClient; // FactoryBean初始化httpClient&nbsp;

    public ThriftHttpProxyFactoryBean() {
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
        PoolingClientConnectionManager connectionManager = new PoolingClientConnectionManager(schemeRegistry);
        connectionManager.setMaxTotal(DEFAULT_MAX_TOTAL_CONNECTIONS);
        connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_CONNECTIONS_PER_ROUTE);
        this.httpClient = new DefaultHttpClient(connectionManager);
        setReadTimeout(DEFAULT_READ_TIMEOUT_MILLISECONDS);
    }

    private void setReadTimeout(int defaultReadTimeoutMilliseconds) {
    }

    @Override
    public T getObject() throws Exception {
        return (T) getServiceProxy(); // 服务对象不在本地，使用代理
    }

    @Override
    public Class<?> getObjectType() {
        return getServiceInterface(); // 服务对象类型，就是配置里面的serviceInterface
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }
}
