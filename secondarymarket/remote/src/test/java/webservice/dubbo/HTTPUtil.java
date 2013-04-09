package webservice.dubbo;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpClientConnection;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.params.SyncBasicHttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.ImmutableHttpProcessor;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestExpectContinue;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.Socket;

public class HTTPUtil {

    protected static Logger log = Logger.getLogger(HTTPUtil.class.getName());

    private static final Integer TIMEOUT = 60;

    public static String httpPost2(String host, int port, String uri, String body) {
        // String urlStr = "http://localhost:9080/flashsvr/flashsvr";
        String rtn = null;
        HttpParams params = new SyncBasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, "UTF-8");
        HttpProtocolParams.setUserAgent(params, "MyClient");
        HttpProtocolParams.setUseExpectContinue(params, true);
        HttpProcessor httpproc = new ImmutableHttpProcessor(new HttpRequestInterceptor[] {
                new RequestContent(), new RequestTargetHost(), new RequestConnControl(),
                new RequestUserAgent(), new RequestExpectContinue() });
        HttpRequestExecutor httpexc = new HttpRequestExecutor();
        HttpContext context = new BasicHttpContext(null);
        HttpHost httpHost = new HttpHost(host, port);
        DefaultHttpClientConnection conn = new DefaultHttpClientConnection();
        ConnectionReuseStrategy crs = new DefaultConnectionReuseStrategy();
        context.setAttribute(ExecutionContext.HTTP_CONNECTION, conn);
        context.setAttribute(ExecutionContext.HTTP_TARGET_HOST, httpHost);
        try {
            HttpEntity[] requestBodies = { new StringEntity(body, "UTF-8") };
            if (!conn.isOpen()) {
                Socket socket = new Socket(httpHost.getHostName(), httpHost.getPort());
                conn.bind(socket, params);
            }
            BasicHttpEntityEnclosingRequest request = new BasicHttpEntityEnclosingRequest("POST",
                    uri);
            request.setEntity(requestBodies[0]);

            request.setParams(params);
            httpexc.preProcess(request, httpproc, context);
            HttpResponse response = httpexc.execute(request, conn, context);
            response.setParams(params);
            httpexc.postProcess(response, httpproc, context);
            rtn = EntityUtils.toString(response.getEntity());
            if (!crs.keepAlive(response, context)) {
                conn.close();
            } else {
                System.out.println("Connection kept alive...");
            }
        } catch (Exception e) {
            log.error("POST ERROR: " + host + uri + "  " + body, e);
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return rtn;

    }



}
