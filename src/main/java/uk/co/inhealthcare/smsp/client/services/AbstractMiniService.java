package uk.co.inhealthcare.smsp.client.services;

import uk.co.inhealthcare.smsp.client.itk.ITKGateway;
import uk.co.inhealthcare.smsp.client.itk.ITKGatewayException;
import uk.co.inhealthcare.smsp.client.itk.ITKIdentity;
import uk.co.inhealthcare.smsp.client.itk.ITKMessage;
import uk.co.inhealthcare.smsp.client.itk.ITKMessageBuilder;

public class AbstractMiniService {

	private ITKGateway itkGateway;

	public AbstractMiniService(ITKGateway itkGateway) {
		this.itkGateway = itkGateway;
	}

	protected ITKMessage doSend(ITKMessage itkMessage) throws ITKGatewayException {
		return itkGateway.invoke(itkMessage);
	}

	protected ITKMessageBuilder createITKMessageBuilder(ITKIdentity itkIdentity) {
		return new ITKMessageBuilder(itkIdentity);
	}

}
