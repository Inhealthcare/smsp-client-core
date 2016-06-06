package uk.co.inhealthcare.smsp.client.services.pds;

import uk.co.inhealthcare.smsp.client.Identity;
import uk.co.inhealthcare.smsp.client.itk.ITKGateway;
import uk.co.inhealthcare.smsp.client.itk.ITKGatewayException;
import uk.co.inhealthcare.smsp.client.itk.ITKHeaders;
import uk.co.inhealthcare.smsp.client.itk.ITKMessage;
import uk.co.inhealthcare.smsp.client.services.AbstractMiniService;

public class VerifyNHSNumberMiniService extends AbstractMiniService {

	private static final String VERIFY_NHS_NUMBER_SERVICE = "urn:nhs-itk:services:201005:verifyNHSNumber-v1-0";

	public VerifyNHSNumberMiniService(ITKGateway itkGateway) {
		super(itkGateway);
	}

	public VerifyNHSNumberResponse verifyNhsNumber(Identity identity, VerifyNHSNumberRequest verifyNHSNumberRequest)
			throws MiniServiceException {

		try {

			// create the request
			ITKMessage request = createITKMessageBuilder(new ITKHeaders(VERIFY_NHS_NUMBER_SERVICE, identity))
					.addPayload(verifyNHSNumberRequest.toServiceRequest()).build();

			// send the request
			ITKMessage response = doSend(request);

			// unwrap response

			// TODO Auto-generated method stub
			return null;

		} catch (ITKGatewayException e) {
			throw new MiniServiceException(String.format("Failed mini service operation %s", VERIFY_NHS_NUMBER_SERVICE),
					e);
		}
	}

}
