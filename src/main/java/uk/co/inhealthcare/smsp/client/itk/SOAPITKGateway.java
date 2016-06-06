package uk.co.inhealthcare.smsp.client.itk;

import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import uk.co.inhealthcare.smsp.client.soap.SOAPMessageProcessor;
import uk.co.inhealthcare.smsp.client.soap.SOAPSender;

public class SOAPITKGateway implements ITKGateway {

	private SOAPSender soapSender;
	private MessageFactory messageFactory;

	public SOAPITKGateway(SOAPSender soapSender) {
		assert soapSender != null;
		this.soapSender = soapSender;
		try {
			messageFactory = MessageFactory.newInstance();
		} catch (SOAPException e) {
			throw new IllegalArgumentException("Coud not create a SOAP message factory", e);
		}
	}

	@Override
	public ITKMessage invoke(ITKMessage itkMessage) throws ITKGatewayException {

		SOAPMessage requestMessage = null;
		try {
			requestMessage = messageFactory.createMessage();

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

		try {
			messageFactory.createMessage();
			return null;
		} catch (SOAPException e) {
			throw new ITKGatewayException("Failed to senda SOAP message", e);
		}
	}

	protected SOAPMessage doSend(SOAPMessage soapMessage) throws SOAPException {
		return soapSender.send(soapMessage);
	}

}
