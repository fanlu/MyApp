package com.mmtzj.action;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-3-8
 * Time: 上午10:07
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class BaseThriftController {
    protected TProcessor processor_ = null;
    protected TTransportFactory inputTransportFactory_ = new TTransportFactory();
    protected TTransportFactory outputTransportFactory_ = new TTransportFactory();
    protected TProtocolFactory inputProtocolFactory_ = new TBinaryProtocol.Factory();
    protected TProtocolFactory outputProtocolFactory_ = new TBinaryProtocol.Factory();
    public BaseThriftController(TProcessor processor) {
        processor_ = processor;
    }

    public BaseThriftController() {
    }

    @RequestMapping("/service")
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/x-thrift");
        InputStream in = request.getInputStream();
        OutputStream out = response.getOutputStream();
        TTransport client = new TIOStreamTransport(in, out);
        TProcessor processor = null;
        TTransport inputTransport = null;
        TTransport outputTransport = null;
        TProtocol inputProtocol = null;
        TProtocol outputProtocol = null;
        try {
            processor = processor_;
            inputTransport = inputTransportFactory_.getTransport(client);
            outputTransport = outputTransportFactory_.getTransport(client);
            inputProtocol = inputProtocolFactory_.getProtocol(inputTransport);
            outputProtocol = outputProtocolFactory_
                    .getProtocol(outputTransport);
            while (processor.process(inputProtocol, outputProtocol)) {
            }
        } catch (TTransportException ttx) {
            // Client died, just move on
        } catch (TException tx) {
            tx.printStackTrace();
        } catch (Exception x) {
            x.printStackTrace();
        }
        if (inputTransport != null) {
            inputTransport.close();
        }
        if (outputTransport != null) {
            outputTransport.close();
        }
    }
}
