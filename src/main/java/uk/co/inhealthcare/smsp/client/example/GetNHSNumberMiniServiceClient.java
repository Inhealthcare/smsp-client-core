package uk.co.inhealthcare.smsp.client.example;

import uk.co.inhealthcare.smsp.client.Identity;
import uk.co.inhealthcare.smsp.client.itk.ITKGateway;
import uk.co.inhealthcare.smsp.client.services.model.DateOfBirth;
import uk.co.inhealthcare.smsp.client.services.model.Gender;
import uk.co.inhealthcare.smsp.client.services.model.LocalIdentifier;
import uk.co.inhealthcare.smsp.client.services.model.Name;
import uk.co.inhealthcare.smsp.client.services.model.Postcode;
import uk.co.inhealthcare.smsp.client.services.pds.GetNHSNumberMiniService;
import uk.co.inhealthcare.smsp.client.services.pds.GetNHSNumberRequest;
import uk.co.inhealthcare.smsp.client.services.pds.GetNHSNumberResponse;
import uk.co.inhealthcare.smsp.client.services.pds.MiniServiceException;

public class GetNHSNumberMiniServiceClient extends AbstractMiniServiceClient {


	private static final String EXAMPLE_POSTCODE = "KT19 9QB";
	private static final String EXAMPLE_LOCAL_IDENTIFIER = "LIXREF19";
	private static final String EXAMPLE_DOB = "19991219";
	private static final String EXAMPLE_GIVEN_NAME = "Dave";
	private static final String EXAMPLE_FAMILY_NAME = "Smith";

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
		GetNHSNumberRequest request = new GetNHSNumberRequest.Builder().dateOfBirth(new DateOfBirth(EXAMPLE_DOB))
				.gender(new Gender(Gender.Type.male))
				.local(new LocalIdentifier(EXAMPLE_LOCAL_IDENTIFIER))
				.name(new Name.Builder().given(EXAMPLE_GIVEN_NAME).family(EXAMPLE_FAMILY_NAME)
						.build()).postCode(new Postcode(EXAMPLE_POSTCODE)).build();
		return request;
	}

}
