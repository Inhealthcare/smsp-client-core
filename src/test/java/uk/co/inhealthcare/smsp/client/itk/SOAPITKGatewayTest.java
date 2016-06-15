package uk.co.inhealthcare.smsp.client.itk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

import org.hl7.v3.COMTMT000013GB01VerifyNHSNumberResponseV10;
import org.junit.Test;

import itk.nhs.ns._201005.DistributionEnvelopeType;
import itk.nhs.ns._201005.DistributionHeaderType;
import uk.co.inhealthcare.smsp.client.services.RequestContext;
import uk.co.inhealthcare.smsp.client.soap.SOAPSender;
import uk.co.inhealthcare.smsp.client.soap.SimpleSOAPMessageFactory;

public class SOAPITKGatewayTest {

	private static final String RESPONSE_SERVICE_ID = "7C81FCC1-E13A-4615-8DC6-B1E435BA0472";
	private static final String RESPONSE_TRACKING_ID = "0F8228B0-E251-40E2-82B5-88DF393FB0C5";
	private static final String REQUEST_TRACKING_ID = "TRACKINGID";
	private static final String REQUEST_CONTEXT_USERNAME = "username";
	private static final String REQUEST_CONTEXT_SERVICE_NAME = "serviceName";
	private static final String REQUEST_CONTEXT_CLIENT_SERVICE_URL = "clientServiceUrl";
	private static final String REQUEST_CONTEXT_SERVICE_URL = "serviceUrl";

	private SimpleSOAPMessageFactory soapMessageFactory = new SimpleSOAPMessageFactory();

	@Test
	public void shouldSendAndReceiveITKMessageViaSOAP() throws ITKGatewayException, SOAPException {

		SOAPSenderStub soapSenderStub = new SOAPSenderStub(soapMessageFactory);
		ITKMessage itkMessage = createRequest();

		ITKMessage response = new SOAPITKGateway(soapSenderStub, soapMessageFactory).invoke(itkMessage);

		assertRequestITKMessageDistributionEnvelopeAddedToSOAPBody(soapSenderStub.getCapturedMessage());
		assertWSAddressingHeadersAddedToSOAPHeader(soapSenderStub.getCapturedMessage());
		assertWSSecurityHeadersAddedToSOAPHeader(soapSenderStub.getCapturedMessage());

		assertResponseITKMessageParsedFromSOAPResponseBody(response);

	}

	private void assertWSSecurityHeadersAddedToSOAPHeader(SOAPMessage message) throws SOAPException {

		QName messageIdQName = new QName("http://www.w3.org/2005/08/addressing", "MessageID", "wsa");
		QName actionQName = new QName("http://www.w3.org/2005/08/addressing", "Action", "wsa");
		QName toQName = new QName("http://www.w3.org/2005/08/addressing", "To", "wsa");
		QName fromName = new QName("http://www.w3.org/2005/08/addressing", "From", "wsa");
		QName fromAddressName = new QName("http://www.w3.org/2005/08/addressing", "Address", "wsa");

		SOAPHeader header = message.getSOAPHeader();
		SOAPElement messageId = (SOAPElement) header.getChildElements(messageIdQName).next();
		SOAPElement action = (SOAPElement) header.getChildElements(actionQName).next();
		SOAPElement to = (SOAPElement) header.getChildElements(toQName).next();
		SOAPElement fromAddress = (SOAPElement) ((SOAPElement) header.getChildElements(fromName).next())
				.getChildElements(fromAddressName).next();

		assertTrue(messageId.getTextContent().length() > 0);
		assertEquals(REQUEST_CONTEXT_SERVICE_NAME, action.getTextContent());
		assertEquals(REQUEST_CONTEXT_SERVICE_URL, to.getTextContent());
		assertEquals(REQUEST_CONTEXT_CLIENT_SERVICE_URL, fromAddress.getTextContent());

	}

	private void assertWSAddressingHeadersAddedToSOAPHeader(SOAPMessage message) throws SOAPException {

		QName securityQName = new QName(
				"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Security",
				"wsse");
		QName timeStampQName = new QName(
				"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd", "Timestamp",
				"wsu");
		QName createdQName = new QName(
				"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd", "Created", "wsu");
		QName expiredQName = new QName(
				"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd", "Expires", "wsu");
		QName usernameTokenQName = new QName(
				"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "UsernameToken",
				"wsse");
		QName usernameQName = new QName(
				"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Username",
				"wsse");

		SOAPHeader header = message.getSOAPHeader();
		SOAPElement security = (SOAPElement) header.getChildElements(securityQName).next();
		SOAPElement created = (SOAPElement) ((SOAPElement) security.getChildElements(timeStampQName).next())
				.getChildElements(createdQName).next();
		SOAPElement expires = (SOAPElement) ((SOAPElement) security.getChildElements(timeStampQName).next())
				.getChildElements(expiredQName).next();
		SOAPElement username = (SOAPElement) ((SOAPElement) security.getChildElements(usernameTokenQName).next())
				.getChildElements(usernameQName).next();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		try {
			dateFormat.parse(created.getTextContent());
		} catch (Exception e) {
			fail("Could not parse created date");
		}
		try {
			dateFormat.parse(expires.getTextContent());
		} catch (Exception e) {
			fail("Could not parse expires date");
		}
		assertEquals(REQUEST_CONTEXT_USERNAME, username.getTextContent());

	}

	private ITKMessage createRequest() {
		return new ITKTestMessage();
	}

	private void assertRequestITKMessageDistributionEnvelopeAddedToSOAPBody(SOAPMessage message) throws SOAPException {

		SOAPBody soapBody = message.getSOAPBody();

		Iterator bodyElements = soapBody.getChildElements();
		assertTrue(bodyElements.hasNext());

		SOAPElement distributionEnvelope = (SOAPElement) bodyElements.next();
		assertFalse(bodyElements.hasNext());
		assertEquals("DistributionEnvelope", distributionEnvelope.getNodeName());

		assertEnvelopeContainsRequestHeader(distributionEnvelope);

	}

	private void assertEnvelopeContainsRequestHeader(SOAPElement distributionEnvelope) {
		Iterator envelopeElements = distributionEnvelope.getChildElements();
		SOAPElement header = (SOAPElement) envelopeElements.next();
		assertEquals(REQUEST_TRACKING_ID, header.getAttribute("trackingid"));
	}

	private void assertResponseITKMessageParsedFromSOAPResponseBody(ITKMessage response) {

		DistributionEnvelopeType value = response.getDistributionEnvelope().getValue();

		assertEquals(RESPONSE_TRACKING_ID, value.getHeader().getTrackingid());
		assertNull(response.getHeaders());
		assertEquals(RESPONSE_SERVICE_ID, response.findPayload(new TestPayloadQuery()).getId().getRoot());

	}

	private static class SOAPSenderStub implements SOAPSender {

		private SOAPMessage capturedMessage;
		private SimpleSOAPMessageFactory soapMessageFactory;

		public SOAPSenderStub(SimpleSOAPMessageFactory soapMessageFactory) {
			this.soapMessageFactory = soapMessageFactory;
		}

		@Override
		public SOAPMessage send(SOAPMessage soapMessage) throws SOAPException {

			// capture and load response
			this.capturedMessage = soapMessage;

			return createResponse();
		}

		private SOAPMessage createResponse() throws SOAPException {
			try {
				InputStream resourceAsStream = SOAPITKGateway.class
						.getResourceAsStream("/uk/co/inhealthcare/smsp/client/itk/soap.xml");
				if (resourceAsStream == null) {
					throw new Exception("Null response resource");
				}
				return soapMessageFactory.createFrom(resourceAsStream);
			} catch (Exception e) {
				throw new SOAPException("Could not find response file");
			}
		}

		public SOAPMessage getCapturedMessage() {
			return capturedMessage;
		}

	}

	private static class TestPayloadQuery implements PayloadQuery<COMTMT000013GB01VerifyNHSNumberResponseV10> {

		@Override
		public COMTMT000013GB01VerifyNHSNumberResponseV10 match(Serializable payloadContent) {
			if (payloadContent instanceof JAXBElement<?>) {
				return ((JAXBElement<COMTMT000013GB01VerifyNHSNumberResponseV10>) payloadContent).getValue();
			}
			return null;
		}

	}

	private static class ITKTestMessage extends AbstractITKMessage {
		private static final itk.nhs.ns._201005.ObjectFactory itkFactory = new itk.nhs.ns._201005.ObjectFactory();

		public ITKTestMessage() {
			setHeaders(new ITKHeaders(REQUEST_CONTEXT_SERVICE_NAME, new RequestContext(REQUEST_CONTEXT_USERNAME,
					"auditIdentity", REQUEST_CONTEXT_CLIENT_SERVICE_URL, REQUEST_CONTEXT_SERVICE_URL)));
			DistributionEnvelopeType envelopeType = new DistributionEnvelopeType();
			DistributionHeaderType value = new DistributionHeaderType();
			value.setTrackingid(REQUEST_TRACKING_ID);
			envelopeType.setHeader(value);
			setDistributionEnvelope(itkFactory.createDistributionEnvelope(envelopeType));
		}

	}

}
