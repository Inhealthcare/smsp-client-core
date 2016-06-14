package uk.co.inhealthcare.smsp.client.examples;

import uk.co.inhealthcare.smsp.client.itk.ITKGateway;
import uk.co.inhealthcare.smsp.client.services.RequestContext;
import uk.co.inhealthcare.smsp.client.services.pds.MiniServiceException;

public abstract class AbstractMiniServiceClient implements MiniServiceClient {

	protected ITKGateway itkGateway;
	protected RequestContext context;

	public AbstractMiniServiceClient(ITKGateway itkGateway, RequestContext context) {
		this.itkGateway = itkGateway;
		this.context = context;
	}

	@Override
	public void run() throws MiniServiceException {
		runClient(itkGateway, context);
	}

	protected abstract void runClient(ITKGateway itkGateway, RequestContext context) throws MiniServiceException;

}
