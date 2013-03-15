package com.mmtzj.thrift.http.server;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: wangxin
 * Date: 13-2-27
 * Time: 下午9:14
 * To change this template use File | Settings | File Templates.
 */
public class ThriftHttpServiceExporter extends com.mmtzj.thrift.http.server.ThriftExporter implements HttpRequestHandler {

    Logger logger = LoggerFactory.getLogger(ThriftHttpServiceExporter.class);
    private URL metadataXml;
    private TProtocolFactory jsonProtocolFactory = new TJSONProtocol.Factory();
    private TProtocolFactory protocolFactory = new TBinaryProtocol.Factory();

    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!"POST".equals(request.getMethod()) && metadataXml != null) {
            response.setContentType("text/xml; charset=UTF-8");
            FileCopyUtils.copy(metadataXml.openStream(), response.getOutputStream());
            return;
        }
        InputStream in = request.getInputStream();
        OutputStream out = response.getOutputStream();
        try {
            ThriftContextHolder.init();
            response.setContentType("application/x-thrift");
            TTransport transport = new TIOStreamTransport(in, out);
            TProtocol protocol = request.getParameter("_json") != null ? jsonProtocolFactory.getProtocol(transport) : getProtocolFactory().getProtocol(transport);
            doInvoke(protocol, protocol);
        } catch (Throwable e) {
            response.setContentType("text/plain; charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace(new PrintWriter(out, true));
            if (logger.isErrorEnabled()) {
                logger.error("Thrift server direct error", e);
            }
        } finally {
            ThriftContextHolder.reset();
        }
    }

    private TProtocolFactory getProtocolFactory() {
        return this.protocolFactory;
    }

    protected void doInvoke(TProtocol in, TProtocol out) throws Throwable {
        processor.process(in, out);
    }


}
