package com.mmtzj.interceptor;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import java.util.List;

public class HeaderIntercepter extends AbstractSoapInterceptor {
	private String qname;

	public HeaderIntercepter() {
		super(Phase.WRITE);
	}

	public void handleMessage(SoapMessage soapMessage) throws Fault {

		System.out.println("come in ClientHeaderIntercepter");
		String spPassword = "0c79e50cc18dfdce407578ac9d006054";

		QName name = new QName("RequestSOAPHeader");
		Document doc = DOMUtils.createDocument();

		Element root =doc.createElement("SOAP-ENV:Header");

        Element accesskey = doc.createElementNS("http://tel.m400.net/ppc/wsdl", "auth:accesskey");
        accesskey.setTextContent(spPassword);
		root.appendChild(accesskey);

		SoapHeader head = new SoapHeader(name, root);
		List<Header> headers = soapMessage.getHeaders();
		headers.add(head);

	}

	private Object getHeader() {
		QName qName = new QName("", "", "");
		Document document = DOMUtils.createDocument();
		Element element = document.createElementNS(qname, "RequestSOAPHeader");
		Element token = document.createElement("token");
		token.setTextContent("kkkkk");
		// element.appendChild(token);
		SoapHeader header = new SoapHeader(qName, token);
		return (header);
	}

	public String getQname() {
		return qname;
	}

	public void setQname(String qname) {
		this.qname = qname;
	}
}