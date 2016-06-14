package uk.co.inhealthcare.smsp.client.examples;

import uk.co.inhealthcare.smsp.client.itk.ITKGateway;
import uk.co.inhealthcare.smsp.client.services.RequestContext;

public class ExampleClientFactory {

	public enum Service {
		verifyNHSNumber, getNHSNumber, getPatientDetailsByNHSNumber, getPatientDetailsBySearch, getPatientDetails;
	}

	private RequestContext context;
	private ITKGateway itkGateway;

	public ExampleClientFactory(ITKGateway itkGateway, RequestContext context) {
		this.itkGateway = itkGateway;
		this.context = context;
	}

	public MiniServiceClient forService(Service service) {
		MiniServiceClient client;
		switch (service) {
		case verifyNHSNumber:
			client = new VerifyNHSNumberMiniClient(itkGateway, context);
			break;
		case getNHSNumber:
			client = new GetNHSNumberMiniServiceClient(itkGateway, context);
			break;
		case getPatientDetailsByNHSNumber:
			client = new GetPatientDetailsByNHSNumberMiniServiceClient(itkGateway, context);
			break;
		case getPatientDetailsBySearch:
			client = new GetPatientDetailsBySearchMiniServiceClient(itkGateway, context);
			break;
		case getPatientDetails:
			client = new GetPatientDetailsMiniServiceClient(itkGateway, context);
			break;
		default:
			throw new IllegalArgumentException("Could not find a client for service " + service);
		}
		return client;
	}

}
