package uk.co.inhealthcare.smsp.client.example;

import uk.co.inhealthcare.smsp.client.Identity;
import uk.co.inhealthcare.smsp.client.itk.ITKGateway;
import uk.co.inhealthcare.smsp.client.services.model.DateOfBirth;
import uk.co.inhealthcare.smsp.client.services.model.Gender;
import uk.co.inhealthcare.smsp.client.services.model.NHSNumber;
import uk.co.inhealthcare.smsp.client.services.model.Name;
import uk.co.inhealthcare.smsp.client.services.model.Gender.Type;
import uk.co.inhealthcare.smsp.client.services.pds.GetPatientDetailsMiniService;
import uk.co.inhealthcare.smsp.client.services.pds.GetPatientDetailsRequest;
import uk.co.inhealthcare.smsp.client.services.pds.GetPatientDetailsResponse;
import uk.co.inhealthcare.smsp.client.services.pds.MiniServiceException;

public class GetPatientDetailsMiniServiceClient extends AbstractGetPatientDetailsMiniServiceClient {

	private static final String EXAMPLE_NHS_NUMBER = "9449310424";	
	private static final String EXAMPLE_DOB = "20070328";
	private static final String EXAMPLE_GIVEN_NAME = "BRITTNY";
	private static final String EXAMPLE_FAMILY_NAME = "HEBRON";

	public GetPatientDetailsMiniServiceClient(ITKGateway itkGateway, Identity identity) {
		super(itkGateway, identity);
	}

	@Override
	protected void runClient(ITKGateway itkGateway, Identity identity) throws MiniServiceException {

		// create the get nhs number service
		GetPatientDetailsMiniService service = new GetPatientDetailsMiniService(itkGateway);

		// create the request
		GetPatientDetailsRequest request = createRequest();

		// invoke the service
		GetPatientDetailsResponse response = service.getPatientDetails(identity, request);

		// handle response
		handleResponse(response);

	}

	private GetPatientDetailsRequest createRequest() {
		GetPatientDetailsRequest request = new GetPatientDetailsRequest.Builder()
				.dateOfBirth(new DateOfBirth(EXAMPLE_DOB))
				.setNhsNumber(new NHSNumber(EXAMPLE_NHS_NUMBER))
				.gender(new Gender(Type.female))
				.name(new Name.Builder().given(EXAMPLE_GIVEN_NAME).family(EXAMPLE_FAMILY_NAME).build())
				.build();
		return request;
	}

}
