package uk.co.inhealthcare.smsp.client.soap;

import java.io.InputStream;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public interface SOAPMessageFactory {

	SOAPMessage createNew() throws SOAPException;

	SOAPMessage createFrom(InputStream soapXml) throws SOAPException;

}
