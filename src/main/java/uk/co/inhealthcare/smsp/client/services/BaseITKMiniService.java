package uk.co.inhealthcare.smsp.client.services;

import uk.co.inhealthcare.smsp.client.itk.ITKGateway;
import uk.co.inhealthcare.smsp.client.itk.ITKGatewayException;
import uk.co.inhealthcare.smsp.client.itk.ITKHeaders;
import uk.co.inhealthcare.smsp.client.itk.ITKMessage;
import uk.co.inhealthcare.smsp.client.itk.ITKRequestMessageBuilder;

public class BaseITKMiniService {

	private ITKGateway itkGateway;

	public BaseITKMiniService(ITKGateway itkGateway) {
		this.itkGateway = itkGateway;
	}

	protected ITKMessage doSend(ITKMessage itkMessage) throws ITKGatewayException {
		return itkGateway.invoke(itkMessage);
	}

	protected ITKRequestMessageBuilder createITKMessageBuilder(ITKHeaders headers) {
		return new ITKRequestMessageBuilder(headers);
	}

}
