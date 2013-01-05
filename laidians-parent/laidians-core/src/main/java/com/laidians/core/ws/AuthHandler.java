package com.laidians.core.ws;

import java.io.IOException;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class AuthHandler implements SOAPHandler<SOAPMessageContext> {

	private final AuthHeader authHeader;

	public AuthHandler(AuthHeader authHeader) {
		this.authHeader = authHeader;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		SOAPMessage message = context.getMessage();
		if (outboundProperty.booleanValue()) {
			try {
				SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();

				SOAPHeader header = envelope.getHeader();
				if (header == null) {
					header = envelope.addHeader();
				}

				QName qName = new QName(authHeader.getqName(), authHeader.getKey(), "");

				SOAPHeaderElement he = header.addHeaderElement(qName);
				SOAPElement tokenElm = he.addChildElement(authHeader.getToken());
				tokenElm.setValue(authHeader.getTokenValue());

			} catch (SOAPException e) {
				throw new RuntimeException(e);
			}

		}
		// 把SOAP消息输出到System.out，即控制台
		try {
			message.writeTo(System.out);
		} catch (SOAPException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return false;
	}

	@Override
	public void close(MessageContext context) {
	}

	@Override
	public Set<QName> getHeaders() {
		return null;
	}
}