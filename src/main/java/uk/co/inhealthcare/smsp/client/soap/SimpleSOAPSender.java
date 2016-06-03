package uk.co.inhealthcare.smsp.client.soap;

import java.io.IOException;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class SimpleSOAPSender implements SOAPSender {

	private SOAPConnection soapConnection;

	public SimpleSOAPSender(SOAPConnection soapConnection) {
		this.soapConnection = soapConnection;
	}

	@Override
	public SOAPMessage send(SOAPMessage requestMessage) throws SOAPException {

		try {
			requestMessage.writeTo(System.out);
			return null;
		} catch (IOException e) {
			throw new SOAPException("Could not send soap", e);
		}
	}

}
