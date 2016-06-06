package uk.co.inhealthcare.smsp.client.soap;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.wss4j.common.util.XMLUtils;

public class SimpleSOAPSender implements SOAPSender {

	private SOAPConnection soapConnection;

	public SimpleSOAPSender(SOAPConnection soapConnection) {
		this.soapConnection = soapConnection;
	}

	@Override
	public SOAPMessage send(SOAPMessage requestMessage) throws SOAPException {

		try {

			System.out.println(XMLUtils.prettyDocumentToString(requestMessage.getSOAPBody().getOwnerDocument()));

			return null;
		} catch (Exception e) {
			throw new SOAPException("Could not send soap", e);
		}
	}

}
