package uk.co.inhealthcare.smsp.client.itk;

import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import uk.co.inhealthcare.smsp.client.soap.SOAPMessageFactory;
import uk.co.inhealthcare.smsp.client.soap.SOAPMessageProcessor;
import uk.co.inhealthcare.smsp.client.soap.SOAPSender;

public class SOAPITKGateway implements ITKGateway {

	private SOAPSender soapSender;
	private SOAPMessageFactory soapMessageFactory;

	public SOAPITKGateway(SOAPSender soapSender, SOAPMessageFactory soapMessageFactory) {
		assert soapSender != null;
		assert soapMessageFactory != null;
		this.soapSender = soapSender;
		this.soapMessageFactory = soapMessageFactory;
	}

	@Override
	public ITKMessage invoke(ITKMessage itkMessage) throws ITKGatewayException {

		SOAPMessage requestMessage = null;
		try {
			requestMessage = soapMessageFactory.createNew();

			List<SOAPMessageProcessor> list = new ArrayList<>();
			list.add(new SOAPBodyHandler(itkMessage.getDistributionEnvelope()));
			list.add(new WSAddressingSOAPHander(itkMessage.getHeaders()));
			list.add(new WSSecuritySOAPHander(itkMessage.getHeaders()));

			for (SOAPMessageProcessor soapHandler : list) {
				requestMessage = soapHandler.process(requestMessage);
			}

		} catch (SOAPException e) {
			throw new ITKGatewayException("Could not create a SOAP message", e);
		}

		SOAPMessage response = null;
		try {
			response = doSend(requestMessage);
		} catch (SOAPException e) {
			throw new ITKGatewayException("Failed to senda SOAP message", e);
		}

		if (response == null) {
			throw new ITKGatewayException("Could not handle SOAP response as there was none");
		}

		try {
			// get the body here
			DistributionEnvelopeExtractor extractor = new DistributionEnvelopeExtractor();
			ITKResponseMessageBuilder builder = new ITKResponseMessageBuilder()
					.distributionEnvelope(extractor.extract(response));

			return builder.build();
		} catch (SOAPException e) {
			throw new ITKGatewayException("Failed to read SOAP resposne", e);
		}
	}

	protected SOAPMessage doSend(SOAPMessage soapMessage) throws SOAPException {
		return soapSender.send(soapMessage);
	}

}
