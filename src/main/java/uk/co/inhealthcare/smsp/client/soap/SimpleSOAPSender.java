package uk.co.inhealthcare.smsp.client.soap;

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
			
			SOAPClient client = soapConnection.getClient();
			
			return client.send( requestMessage );
			
		} catch (Exception e) {
			throw new SOAPException("Could not send soap", e);
		}
	}

}
