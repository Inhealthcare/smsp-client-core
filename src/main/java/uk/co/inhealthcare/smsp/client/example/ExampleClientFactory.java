package uk.co.inhealthcare.smsp.client.example;

import uk.co.inhealthcare.smsp.client.Identity;
import uk.co.inhealthcare.smsp.client.itk.ITKGateway;

public class ExampleClientFactory {

	public enum Service {
		verifyNHSNumber, getNHSNumber, getPatientDetailsByNHSNumber, getPatientDetailsBySearch, getPatientDetails;
	}

	private Identity identity;
	private ITKGateway itkGateway;

	public ExampleClientFactory(ITKGateway itkGateway, Identity identity) {
		this.itkGateway = itkGateway;
		this.identity = identity;
	}

	public MiniServiceClient forService(Service service) {
		MiniServiceClient client;
		switch (service) {
		case verifyNHSNumber:
			client = new VerifyNHSNumberMiniClient(itkGateway, identity);
			break;
		case getNHSNumber:
			client = new GetNHSNumberMiniServiceClient(itkGateway, identity);
			break;
		case getPatientDetailsByNHSNumber:
			client = new GetPatientDetailsByNHSNumberMiniServiceClient(itkGateway, identity);
			break;
		case getPatientDetailsBySearch:
			client = new GetPatientDetailsBySearchMiniServiceClient(itkGateway, identity);
			break;
		case getPatientDetails:
			client = new GetPatientDetailsMiniServiceClient(itkGateway, identity);
			break;
		default:
			throw new IllegalArgumentException("Could not find a client for service " + service);
		}
		return client;
	}

}
