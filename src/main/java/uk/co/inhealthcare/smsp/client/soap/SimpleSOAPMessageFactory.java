package uk.co.inhealthcare.smsp.client.soap;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class SimpleSOAPMessageFactory implements SOAPMessageFactory {

	private MessageFactory messageFactory;

	public SimpleSOAPMessageFactory() {
		try {
			messageFactory = MessageFactory.newInstance();
		} catch (SOAPException e) {
			throw new IllegalArgumentException("Coud not create a SOAP message factory", e);
		}
	}

	@Override
	public SOAPMessage createNew() throws SOAPException {
		return messageFactory.createMessage();
	}

	@Override
	public SOAPMessage createFrom(InputStream soapXml) throws SOAPException {
		try {
			return messageFactory.createMessage(null, soapXml);
		} catch (IOException e) {
			throw new SOAPException("Could not read a SOAP message", e);
		}
	}

}
