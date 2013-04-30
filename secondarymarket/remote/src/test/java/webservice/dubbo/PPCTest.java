package webservice.dubbo;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-3-28
 * Time: 下午2:04
 * To change this template use File | Settings | File Templates.
 */
public class PPCTest {

    @Test
    public void test(){
        String data = HTTPUtil.httpPost2("tel.m400.net", 80, "/ppc/soap", reqSoap("589835"));
        System.out.println(data);
    }

    public String reqSoap(String startid) {
        String val = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"http://tel.m400.net/ppc/soap\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:ns2=\"http://xml.apache.org/xml-soap\" xmlns:ns3=\"http://tel.m400.net/ppc/wsdl\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"><SOAP-ENV:Header><ns3:accesskey>0c79e50cc18dfdce407578ac9d006054</ns3:accesskey></SOAP-ENV:Header><SOAP-ENV:Body><ns1:getCDRRecord400><params xsi:type=\"ns2:Map\"><item><key xsi:type=\"xsd:string\">startid</key><value xsi:type=\"xsd:int\">"
                + startid
                + "</value></item><item><key xsi:type=\"xsd:string\">limit</key><value xsi:type=\"xsd:int\">1</value></item></params></ns1:getCDRRecord400></SOAP-ENV:Body></SOAP-ENV:Envelope>";
        return val;
    }
}
