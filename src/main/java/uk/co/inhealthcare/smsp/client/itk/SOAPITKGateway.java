package uk.co.inhealthcare.smsp.client.itk;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import uk.co.inhealthcare.smsp.client.soap.SOAPSender;

public class SOAPITKGateway implements ITKGateway {

	private static final itk.nhs.ns._201005.ObjectFactory itkFactory = new itk.nhs.ns._201005.ObjectFactory();

	private SOAPSender soapSender;

	public SOAPITKGateway(SOAPSender soapSender) {
		assert soapSender != null;
		this.soapSender = soapSender;
	}

	@Override
	public ITKResponse send(ITKMessage itkMessage) {

		try {

			MessageFactory messageFactory = MessageFactory.newInstance();

		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return null;
	}

	protected SOAPMessage doSend(SOAPMessage soapMessage) {
		return soapSender.send(soapMessage);
	}

}
