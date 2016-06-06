package uk.co.inhealthcare.smsp.client.soap;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public interface SOAPClient {

	SOAPMessage send(SOAPMessage requestMessage) throws SOAPException;

}
