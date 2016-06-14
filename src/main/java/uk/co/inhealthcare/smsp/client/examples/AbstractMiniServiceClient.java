package uk.co.inhealthcare.smsp.client.examples;

import uk.co.inhealthcare.smsp.client.itk.ITKGateway;
import uk.co.inhealthcare.smsp.client.services.Identity;
import uk.co.inhealthcare.smsp.client.services.pds.MiniServiceException;

public abstract class AbstractMiniServiceClient implements MiniServiceClient {

	protected ITKGateway itkGateway;
	protected Identity identity;

	public AbstractMiniServiceClient(ITKGateway itkGateway, Identity identity) {
		this.itkGateway = itkGateway;
		this.identity = identity;
	}

	@Override
	public void run() throws MiniServiceException {
		runClient(itkGateway, identity);
	}

	protected abstract void runClient(ITKGateway itkGateway, Identity identity) throws MiniServiceException;

}
