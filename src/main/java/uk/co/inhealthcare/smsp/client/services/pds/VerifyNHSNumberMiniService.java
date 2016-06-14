package uk.co.inhealthcare.smsp.client.services.pds;

import java.io.Serializable;

import javax.xml.bind.JAXBElement;

import org.hl7.v3.COMTMT000013GB01VerifyNHSNumberResponseV10;

import uk.co.inhealthcare.smsp.client.itk.ITKGateway;
import uk.co.inhealthcare.smsp.client.itk.ITKGatewayException;
import uk.co.inhealthcare.smsp.client.itk.ITKHeaders;
import uk.co.inhealthcare.smsp.client.itk.ITKMessage;
import uk.co.inhealthcare.smsp.client.itk.PayloadQuery;
import uk.co.inhealthcare.smsp.client.services.BaseITKMiniService;
import uk.co.inhealthcare.smsp.client.services.Identity;

public class VerifyNHSNumberMiniService extends BaseITKMiniService {

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

			// get the payload response
			return new VerifyNHSNumberResponse.Builder().serviceResponse(findPayload(response)).build();

		} catch (ITKGatewayException e) {
			throw new MiniServiceException(String.format("Failed mini service operation %s", VERIFY_NHS_NUMBER_SERVICE),
					e);
		}
		
	}

	private COMTMT000013GB01VerifyNHSNumberResponseV10 findPayload(ITKMessage response) {
		return response.findPayload(new PayloadQuery<COMTMT000013GB01VerifyNHSNumberResponseV10>() {
			@Override
			public COMTMT000013GB01VerifyNHSNumberResponseV10 match(Serializable payloadContent) {
				if (payloadContent instanceof JAXBElement<?>) {
					return ((JAXBElement<COMTMT000013GB01VerifyNHSNumberResponseV10>) payloadContent).getValue();
				}
				return null;
			}

		});
	}

}
