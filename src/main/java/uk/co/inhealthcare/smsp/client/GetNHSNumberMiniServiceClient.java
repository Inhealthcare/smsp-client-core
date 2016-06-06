package uk.co.inhealthcare.smsp.client;

import uk.co.inhealthcare.smsp.client.itk.ITKGateway;
import uk.co.inhealthcare.smsp.client.services.pds.DateOfBirth;
import uk.co.inhealthcare.smsp.client.services.pds.Gender;
import uk.co.inhealthcare.smsp.client.services.pds.GetNHSNumberMiniService;
import uk.co.inhealthcare.smsp.client.services.pds.GetNHSNumberRequest;
import uk.co.inhealthcare.smsp.client.services.pds.GetNHSNumberResponse;
import uk.co.inhealthcare.smsp.client.services.pds.MiniServiceException;
import uk.co.inhealthcare.smsp.client.services.pds.Name;

public class GetNHSNumberMiniServiceClient extends AbstractMiniServiceClient {

	public GetNHSNumberMiniServiceClient(ITKGateway itkGateway, Identity identity) {
		super(itkGateway, identity);
	}

	@Override
	protected void runClient(ITKGateway itkGateway, Identity identity) throws MiniServiceException {
		
		// create the get nhs number service
		GetNHSNumberMiniService service = new GetNHSNumberMiniService(itkGateway);

		// create the request
		GetNHSNumberRequest request = createRequest();

		// invoke the service
		GetNHSNumberResponse response = service.getNhsNumber(identity, request);

		// handle response
		handleResponse(response);

	}

	private void handleResponse(GetNHSNumberResponse response) {

		System.out.println("");
		System.out.println("Get NHS Number Response");
		System.out.println("");
		System.out.println("Response getMessageId: " + response.getMessageId());
		System.out.println("Response getCode: " + response.getCode().getCode());
		System.out.println("Response getCodeDescription: " + response.getCode().getDescription());
		System.out.println("Response getLocalIdentifier: " + response.getLocalIdentifier());
		System.out.println("Response getNhsNumber: " + response.getNhsNumber());
	}

	private GetNHSNumberRequest createRequest() {
		GetNHSNumberRequest request = new GetNHSNumberRequest.Builder()
				.dateOfBirth(new DateOfBirth(ExampleData.EXAMPLE_DOB)).gender(new Gender(Gender.Type.female))
				.name(new Name.Builder().given(ExampleData.EXAMPLE_GIVEN_NAME).family(ExampleData.EXAMPLE_FAMILY_NAME)
						.build())
				.build();
		return request;
	}

}
