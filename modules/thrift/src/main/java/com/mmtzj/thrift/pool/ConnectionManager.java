package com.mmtzj.thrift.pool;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.thrift.transport.TSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("connectionManager")
public class ConnectionManager implements MethodInterceptor
{
    /** 日志记录器 */
    public Logger logger = LoggerFactory.getLogger(ConnectionManager.class);
    /** 保存local对象 */
    ThreadLocal<TSocket> socketThreadSafe = new ThreadLocal<TSocket>();
    /** 连接提供池 */
    @Resource
    public ConnectionProvider connectionProvider;
    @Override
    public Object invoke(MethodInvocation arg0) throws Throwable
    {
        TSocket socket = null;
        try
        {
            socket = connectionProvider.getConnection();
            socketThreadSafe.set(socket);
            Object ret = arg0.proceed();
            return ret;
        }
        catch (Exception e)
        {
            logger.error("error ConnectionManager.invoke()", e);
            throw new Exception(e);
        }
        finally
        {
            connectionProvider.returnCon(socket);
            socketThreadSafe.remove();
        }
    }
    /**
     * 取socket
     * 
     * @return
     */
    public TSocket getSocket()
    {
        return socketThreadSafe.get();
    }
    public ConnectionProvider getConnectionProvider()
    {
        return connectionProvider;
    }
    public void setConnectionProvider(ConnectionProvider connectionProvider)
    {
        this.connectionProvider = connectionProvider;
    }
}