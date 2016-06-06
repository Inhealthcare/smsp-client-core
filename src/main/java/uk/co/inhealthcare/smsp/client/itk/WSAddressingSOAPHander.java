package uk.co.inhealthcare.smsp.client.itk;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

import uk.co.inhealthcare.smsp.client.soap.SOAPMessageProcessor;
import uk.co.inhealthcare.smsp.client.utils.DCEUtils;

public class WSAddressingSOAPHander implements SOAPMessageProcessor {

	private static final String XMLNS_WSA = "xmlns:wsa";
	private static final String ADDRESSING_URL = "http://www.w3.org/2005/08/addressing";
	private static final String WS_ADDRESSING_PREFIX = "wsa";
	private ITKHeaders identity;

	public WSAddressingSOAPHander(ITKHeaders identity) {
		this.identity = identity;
	}

	@Override
	public SOAPMessage process(SOAPMessage message) throws SOAPException {

		SOAPHeader header = message.getSOAPHeader();

		header.addAttribute(new QName(XMLNS_WSA), ADDRESSING_URL);

		SOAPElement from = header.addChildElement("From", WS_ADDRESSING_PREFIX);
		SOAPElement address = from.addChildElement("Address", WS_ADDRESSING_PREFIX);
		address.addTextNode(identity.getClientServiceUrl());

		SOAPElement action = header.addChildElement("Action", WS_ADDRESSING_PREFIX);
		action.addTextNode(identity.getService());

		SOAPElement messageId = header.addChildElement("MessageID", WS_ADDRESSING_PREFIX);
		messageId.addTextNode( DCEUtils.createUUID() );

		SOAPElement to = header.addChildElement("To", WS_ADDRESSING_PREFIX);
		to.addTextNode(identity.getServiceUrl());

		return message;
		
	}

}
