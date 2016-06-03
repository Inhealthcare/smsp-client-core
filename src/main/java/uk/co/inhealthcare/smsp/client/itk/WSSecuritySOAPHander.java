package uk.co.inhealthcare.smsp.client.itk;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.message.WSSecHeader;
import org.apache.ws.security.message.WSSecTimestamp;
import org.apache.ws.security.message.WSSecUsernameToken;
import org.w3c.dom.Document;

import uk.co.inhealthcare.smsp.client.soap.SOAPMessageProcessor;

public class WSSecuritySOAPHander implements SOAPMessageProcessor {

	private ITKIdentity identity;

	public WSSecuritySOAPHander(ITKIdentity identity) {
		this.identity = identity;
	}

	@Override
	public SOAPMessage process(SOAPMessage message) throws SOAPException {
		try {

			// username token
			WSSecUsernameToken usernameToken = new WSSecUsernameToken();
			usernameToken.setPasswordType(null);
			usernameToken.setUserInfo(identity.getUsername(), null);

			// timestamp
			WSSecTimestamp timestamp = new WSSecTimestamp();

			// add user header info
			Document document = message.getSOAPHeader().getOwnerDocument();

			WSSecHeader header = new WSSecHeader();
			header.setMustUnderstand(false);
			header.insertSecurityHeader(document);

			timestamp.build(document, header);
			usernameToken.build(document, header);

			return message;

		} catch (WSSecurityException e) {
			throw new SOAPException("Could not create a secure SOAP message", e);
		}

	}
}
