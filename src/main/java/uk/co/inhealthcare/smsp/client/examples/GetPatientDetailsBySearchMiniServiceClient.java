package uk.co.inhealthcare.smsp.client.examples;

import uk.co.inhealthcare.smsp.client.itk.ITKGateway;
import uk.co.inhealthcare.smsp.client.model.DateOfBirth;
import uk.co.inhealthcare.smsp.client.model.Gender;
import uk.co.inhealthcare.smsp.client.model.Name;
import uk.co.inhealthcare.smsp.client.model.Postcode;
import uk.co.inhealthcare.smsp.client.model.Gender.Type;
import uk.co.inhealthcare.smsp.client.services.Identity;
import uk.co.inhealthcare.smsp.client.services.pds.GetPatientDetailsBySearchMiniService;
import uk.co.inhealthcare.smsp.client.services.pds.GetPatientDetailsBySearchRequest;
import uk.co.inhealthcare.smsp.client.services.pds.GetPatientDetailsResponse;
import uk.co.inhealthcare.smsp.client.services.pds.MiniServiceException;

public class GetPatientDetailsBySearchMiniServiceClient extends AbstractGetPatientDetailsMiniServiceClient {

	private static final String EXAMPLE_POSTCODE = "KT20 5BS";
	private static final String EXAMPLE_DOB = "20110402";
	private static final String EXAMPLE_GIVEN_NAME = "Old";
	private static final String EXAMPLE_FAMILY_NAME = "History";

	public GetPatientDetailsBySearchMiniServiceClient(ITKGateway itkGateway, Identity identity) {
		super(itkGateway, identity);
	}

	@Override
	protected void runClient(ITKGateway itkGateway, Identity identity) throws MiniServiceException {

		// create the get nhs number service
		GetPatientDetailsBySearchMiniService service = new GetPatientDetailsBySearchMiniService(itkGateway);

		// create the request
		GetPatientDetailsBySearchRequest request = createRequest();

		// invoke the service
		GetPatientDetailsResponse response = service.getPatientDetailsBySearch(identity, request);

		// handle response
		handleResponse(response);

	}

	private GetPatientDetailsBySearchRequest createRequest() {
		GetPatientDetailsBySearchRequest request = new GetPatientDetailsBySearchRequest.Builder()
				.dateOfBirth(new DateOfBirth(EXAMPLE_DOB)).gender(new Gender(Type.male))
				.name(new Name.Builder().given(EXAMPLE_GIVEN_NAME).family(EXAMPLE_FAMILY_NAME).build())
				.postcode(new Postcode(EXAMPLE_POSTCODE)).build();
		return request;
	}

}
