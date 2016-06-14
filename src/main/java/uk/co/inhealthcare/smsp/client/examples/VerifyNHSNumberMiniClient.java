package uk.co.inhealthcare.smsp.client.examples;

import uk.co.inhealthcare.smsp.client.itk.ITKGateway;
import uk.co.inhealthcare.smsp.client.model.DateOfBirth;
import uk.co.inhealthcare.smsp.client.model.NHSNumber;
import uk.co.inhealthcare.smsp.client.model.Name;
import uk.co.inhealthcare.smsp.client.services.RequestContext;
import uk.co.inhealthcare.smsp.client.services.pds.MiniServiceException;
import uk.co.inhealthcare.smsp.client.services.pds.VerifyNHSNumberMiniService;
import uk.co.inhealthcare.smsp.client.services.pds.VerifyNHSNumberRequest;
import uk.co.inhealthcare.smsp.client.services.pds.VerifyNHSNumberResponse;

public class VerifyNHSNumberMiniClient extends AbstractMiniServiceClient {

	private final static String EXAMPLE_FAMILY_NAME = "Hedgewicke";
	private final static String EXAMPLE_GIVEN_NAME = "Hyacinth";
	private final static String EXAMPLE_NHS_NUMBER = "5552448715";
	private final static String EXAMPLE_DOB = "19881123";

	public VerifyNHSNumberMiniClient(ITKGateway itkGateway, RequestContext context) {
		super(itkGateway, context);
	}

	@Override
	protected void runClient(ITKGateway itkGateway, RequestContext context) throws MiniServiceException {

		// create the verify nhs number service
		VerifyNHSNumberMiniService service = new VerifyNHSNumberMiniService(itkGateway);

		// create the request
		VerifyNHSNumberRequest request = createRequest();

		// invoke the service
		VerifyNHSNumberResponse response = service.verifyNhsNumber(context, request);

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
		VerifyNHSNumberRequest request = new VerifyNHSNumberRequest.Builder().dateOfBirth(new DateOfBirth(EXAMPLE_DOB))
				.nhsNumber(new NHSNumber(EXAMPLE_NHS_NUMBER))
				.name(new Name.Builder().given(EXAMPLE_GIVEN_NAME).family(EXAMPLE_FAMILY_NAME).build()).build();
		return request;
	}

}
