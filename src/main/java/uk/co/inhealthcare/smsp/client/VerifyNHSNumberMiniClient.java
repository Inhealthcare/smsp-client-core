package uk.co.inhealthcare.smsp.client;

import uk.co.inhealthcare.smsp.client.itk.ITKGateway;
import uk.co.inhealthcare.smsp.client.services.pds.DateOfBirth;
import uk.co.inhealthcare.smsp.client.services.pds.MiniServiceException;
import uk.co.inhealthcare.smsp.client.services.pds.NHSNumber;
import uk.co.inhealthcare.smsp.client.services.pds.Name;
import uk.co.inhealthcare.smsp.client.services.pds.VerifyNHSNumberMiniService;
import uk.co.inhealthcare.smsp.client.services.pds.VerifyNHSNumberRequest;
import uk.co.inhealthcare.smsp.client.services.pds.VerifyNHSNumberResponse;

public class VerifyNHSNumberMiniClient extends AbstractMiniServiceClient {

	public VerifyNHSNumberMiniClient(ITKGateway itkGateway, Identity identity) {
		super(itkGateway, identity);
	}

	@Override
	protected void runClient(ITKGateway itkGateway, Identity identity) throws MiniServiceException {

		// create the verify nhs number service
		VerifyNHSNumberMiniService service = new VerifyNHSNumberMiniService(itkGateway);

		// create the request
		VerifyNHSNumberRequest request = createRequest();

		// invoke the service
		VerifyNHSNumberResponse response = service.verifyNhsNumber(identity, request);

		// handle response
		handleResponse(response);

	}

	private static void handleResponse(VerifyNHSNumberResponse response) {

		System.out.println("");
		System.out.println("Verify NHS Number Response");
		System.out.println("");
		System.out.println("Response getMessageId: " + response.getMessageId());
		System.out.println("Response getCode: " + response.getCode().getCode());
		System.out.println("Response getCodeDescription: " + response.getCode().getDescription());
		System.out.println("Response isVerified: " + response.isVerified());
		System.out.println("Response getNhsNumber: " + response.getNhsNumber());

	}

	private static VerifyNHSNumberRequest createRequest() {
		VerifyNHSNumberRequest request = new VerifyNHSNumberRequest.Builder()
				.dateOfBirth(new DateOfBirth(ExampleData.EXAMPLE_DOB))
				.nhsNumber(new NHSNumber(ExampleData.EXAMPLE_NHS_NUMBER)).name(new Name.Builder()
						.given(ExampleData.EXAMPLE_GIVEN_NAME).family(ExampleData.EXAMPLE_FAMILY_NAME).build())
				.build();
		return request;
	}

}
