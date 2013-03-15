package com.mmtzj.thrift.http;

import com.mmtzj.thrift.http.server.ThriftHelper;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.http.client.HttpClient;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.remoting.support.UrlBasedRemoteAccessor;
import org.springframework.util.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: wangxin
 * Date: 13-2-27
 * Time: 下午8:41
 * To change this template use File | Settings | File Templates.
 */
public abstract class ThriftClientInterceptor extends UrlBasedRemoteAccessor implements MethodInterceptor {

    private ServiceRegistry serviceRegistry;

    private ClientConstructor clientConstructor = new ClientConstructor();

    private TProtocolFactory protocolFactory = new TBinaryProtocol.Factory();

    public void afterPropertiesSet(){
        super.afterPropertiesSet();
        prepare();
    }

    private void prepare() {
        //To change body of created methods use File | Settings | File Templates.
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        Object[] args = invocation.getArguments();
        if (serviceRegistry != null) {
            ServiceReference sr = serviceRegistry.getService(getServiceInterface());
            if (sr != null) {
                try {
                    return method.invoke(sr.getService(), args);
                } catch (InvocationTargetException e) {
                    throw e.getTargetException();
                }
            }
        }
        String name = method.getName();
        if (args.length == 0) {
            if ("toString".equals(name)) {
                return "Thrift proxy for service URL [" + getServiceUrl() + "]";
            } else if ("hashCode".equals(name)) {
                return getServiceUrl().hashCode();
            }
        } else if (args.length == 1 && "equals".equals(name)) {
            return getServiceUrl().equals(args[0]);
        }
//        Object client = clientConstructor.newInstance(protocolFactory.getProtocol(getTransport()),getServiceInterface());
        Object client = ThriftHelper.buildClient(getServiceInterface(), protocolFactory.getProtocol(getTransport()));
        Assert.notNull(client, "the Thrift RPC client was not correctly created. Aborting.");
        ClassLoader originalClassLoader = overrideThreadContextClassLoader();
        try {
            return method.invoke(client, args);
        } catch (InvocationTargetException e) {
            Throwable target = e.getTargetException();
            if (target instanceof TApplicationException && ((TApplicationException) target).getType() == TApplicationException.MISSING_RESULT) {
                return null;
            }
            throw convertException(target);
        } catch (Throwable ex) {
            throw convertException(ex);
        } finally {
            resetThreadContextClassLoader(originalClassLoader);
        }
    }

    private Throwable convertException(Throwable target) {
        return new RuntimeException(target);
    }

    protected TTransport getTransport() throws TTransportException {
        return new THttpClient(getServiceUrl(), getHttpClient());
    }

    public abstract HttpClient getHttpClient();
}
