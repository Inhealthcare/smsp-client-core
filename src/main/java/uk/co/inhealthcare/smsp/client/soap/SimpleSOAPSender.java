package uk.co.inhealthcare.smsp.client.soap;

import javax.xml.soap.SOAPMessage;

public class SimpleSOAPSender implements SOAPSender {

	private SOAPConnection soapConnection;

	public SimpleSOAPSender(SOAPConnection soapConnection) {
		this.soapConnection = soapConnection;
	}
	
	@Override
	public SOAPMessage send(SOAPMessage soapMessage) {		
		return null;
	}
	
}
