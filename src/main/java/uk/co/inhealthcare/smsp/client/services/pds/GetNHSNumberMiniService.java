package uk.co.inhealthcare.smsp.client.services.pds;

import java.io.Serializable;

import javax.xml.bind.JAXBElement;

import org.hl7.v3.COMTMT000014GB01GetNHSNumberResponseV10;

import uk.co.inhealthcare.smsp.client.Identity;
import uk.co.inhealthcare.smsp.client.itk.ITKGateway;
import uk.co.inhealthcare.smsp.client.itk.ITKGatewayException;
import uk.co.inhealthcare.smsp.client.itk.ITKHeaders;
import uk.co.inhealthcare.smsp.client.itk.ITKMessage;
import uk.co.inhealthcare.smsp.client.itk.PayloadQuery;
import uk.co.inhealthcare.smsp.client.services.AbstractMiniService;

public class GetNHSNumberMiniService extends AbstractMiniService {

	private static final String GET_NHS_NUMBER_SERVICE = "urn:nhs-itk:services:201005:getNHSNumber-v1-0";

	public GetNHSNumberMiniService(ITKGateway itkGateway) {
		super(itkGateway);
	}

	public GetNHSNumberResponse getNhsNumber(Identity identity, GetNHSNumberRequest getNHSNumberRequest)
			throws MiniServiceException {

		try {

			// create the request
			ITKMessage request = createITKMessageBuilder(new ITKHeaders(GET_NHS_NUMBER_SERVICE, identity))
					.addPayload(getNHSNumberRequest.toServiceRequest()).build();

			// send the request
			ITKMessage response = doSend(request);

			// get the payload response
			return new GetNHSNumberResponse.Builder().serviceResponse(findPayload(response)).build();

		} catch (ITKGatewayException e) {
			throw new MiniServiceException(String.format("Failed mini service operation %s", GET_NHS_NUMBER_SERVICE),
					e);
		}

	}

	private COMTMT000014GB01GetNHSNumberResponseV10 findPayload(ITKMessage response) {
		return response.findPayload(new PayloadQuery<COMTMT000014GB01GetNHSNumberResponseV10>() {
			@Override
			public COMTMT000014GB01GetNHSNumberResponseV10 match(Serializable payloadContent) {
				if (payloadContent instanceof JAXBElement<?>) {
					return ((JAXBElement<COMTMT000014GB01GetNHSNumberResponseV10>) payloadContent).getValue();
				}
				return null;
			}

		});
	}

}
