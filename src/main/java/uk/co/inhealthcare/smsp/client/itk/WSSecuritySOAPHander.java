package uk.co.inhealthcare.smsp.client.itk;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.wss4j.common.ext.WSSecurityException;
import org.apache.wss4j.dom.message.WSSecHeader;
import org.apache.wss4j.dom.message.WSSecTimestamp;
import org.apache.wss4j.dom.message.WSSecUsernameToken;
import org.w3c.dom.Document;

import uk.co.inhealthcare.smsp.client.soap.SOAPMessageProcessor;

public class WSSecuritySOAPHander implements SOAPMessageProcessor {

	private ITKHeaders identity;

	public WSSecuritySOAPHander(ITKHeaders identity) {
		this.identity = identity;
	}

	@Override
	public SOAPMessage process(SOAPMessage message) throws SOAPException {
		
		try {

			// username token
			WSSecUsernameToken usernameToken = getUsernameToken();

			// timestamp
			WSSecTimestamp timestamp = getTimestamp();
			
			// add user header info
			insertSecurityHeader(message, usernameToken, timestamp);

			return message;

		} catch (WSSecurityException e) {
			throw new SOAPException("Could not create a secure SOAP message", e);
		}

	}

	private void insertSecurityHeader(SOAPMessage message, WSSecUsernameToken usernameToken, WSSecTimestamp timestamp)
			throws SOAPException, WSSecurityException {
		Document document = message.getSOAPHeader().getOwnerDocument();

		WSSecHeader header = new WSSecHeader(document);
		header.setMustUnderstand(false);
		header.insertSecurityHeader();

		timestamp.build(document, header);
		usernameToken.build(document, header);
	}

	private WSSecTimestamp getTimestamp() {
		WSSecTimestamp timestamp = new WSSecTimestamp();
		timestamp.setPrecisionInMilliSeconds(false);
		return timestamp;
	}

	private WSSecUsernameToken getUsernameToken() {
		WSSecUsernameToken usernameToken = new WSSecUsernameToken();
		usernameToken.setPasswordType(null);
		usernameToken.setUserInfo(identity.getUsername(), null);
		return usernameToken;
	}

}
