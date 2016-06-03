package uk.co.inhealthcare.smsp.client.smsp;

import uk.co.inhealthcare.smsp.client.itk.ITKGateway;
import uk.co.inhealthcare.smsp.client.itk.ITKMessage;
import uk.co.inhealthcare.smsp.client.itk.ITKResponse;

public class AbstractMiniService {

	private ITKGateway itkGateway;

	public AbstractMiniService(ITKGateway itkGateway) {
		this.itkGateway = itkGateway;
	}

	protected ITKResponse doSend(ITKMessage itkMessage) {
		return itkGateway.send(itkMessage);
	}

}
