package uk.co.inhealthcare.smsp.client;

import uk.co.inhealthcare.smsp.client.itk.ITKGateway;
import uk.co.inhealthcare.smsp.client.services.pds.DateOfBirth;
import uk.co.inhealthcare.smsp.client.services.pds.GetPatientDetailsByNHSNumberMiniService;
import uk.co.inhealthcare.smsp.client.services.pds.GetPatientDetailsByNHSNumberRequest;
import uk.co.inhealthcare.smsp.client.services.pds.GetPatientDetailsResponse;
import uk.co.inhealthcare.smsp.client.services.pds.MiniServiceException;
import uk.co.inhealthcare.smsp.client.services.pds.NHSNumber;
import uk.co.inhealthcare.smsp.client.services.pds.Name;

public class GetPatientDetailsByNHSNumberMiniServiceClient extends AbstractGetPatientDetailsMiniServiceClient {

	private static final String EXAMPLE_NHS_NUMBER = "9449310467";
	private static final String EXAMPLE_DOB = "20090310";
	private static final String EXAMPLE_GIVEN_NAME = "LAURENCE";
	private static final String EXAMPLE_FAMILY_NAME = "LANGAN";

	public GetPatientDetailsByNHSNumberMiniServiceClient(ITKGateway itkGateway, Identity identity) {
		super(itkGateway, identity);
	}

	@Override
	protected void runClient(ITKGateway itkGateway, Identity identity) throws MiniServiceException {

		// create the get nhs number service
		GetPatientDetailsByNHSNumberMiniService service = new GetPatientDetailsByNHSNumberMiniService(itkGateway);

		// create the request
		GetPatientDetailsByNHSNumberRequest request = createRequest();

		// invoke the service
		GetPatientDetailsResponse response = service.getPatientDetailsByNHSNumber(identity, request);

		// handle response
		handleResponse(response);

	}

	private GetPatientDetailsByNHSNumberRequest createRequest() {
		GetPatientDetailsByNHSNumberRequest request = new GetPatientDetailsByNHSNumberRequest.Builder()
				.dateOfBirth(new DateOfBirth(EXAMPLE_DOB)).nhsNumber(new NHSNumber(EXAMPLE_NHS_NUMBER))
				.name(new Name.Builder().given(EXAMPLE_GIVEN_NAME).family(EXAMPLE_FAMILY_NAME).build()).build();
		return request;
	}

}
