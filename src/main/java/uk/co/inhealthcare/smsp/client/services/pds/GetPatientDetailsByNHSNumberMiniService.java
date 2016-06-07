package uk.co.inhealthcare.smsp.client.services.pds;

import java.io.Serializable;

import javax.xml.bind.JAXBElement;

import org.hl7.v3.COMTMT000016GB01GetPatientDetailsResponseV10;

import uk.co.inhealthcare.smsp.client.Identity;
import uk.co.inhealthcare.smsp.client.itk.ITKGateway;
import uk.co.inhealthcare.smsp.client.itk.ITKGatewayException;
import uk.co.inhealthcare.smsp.client.itk.ITKHeaders;
import uk.co.inhealthcare.smsp.client.itk.ITKMessage;
import uk.co.inhealthcare.smsp.client.itk.PayloadQuery;
import uk.co.inhealthcare.smsp.client.services.AbstractMiniService;

public class GetPatientDetailsByNHSNumberMiniService extends AbstractMiniService {

	private static final String GET_PATIENT_DETAILS_BY_NHSNUMBER_SERVICE = "urn:nhs-itk:services:201005:getPatientDetailsByNHSNumber-v1-0";

	public GetPatientDetailsByNHSNumberMiniService(ITKGateway itkGateway) {
		super(itkGateway);
	}

	public GetPatientDetailsResponse getPatientDetailsByNHSNumber(Identity identity, GetPatientDetailsByNHSNumberRequest getPatientDetailsByNHSNumberRequest)
			throws MiniServiceException {

		try {

			// create the request
			ITKMessage request = createITKMessageBuilder(new ITKHeaders(GET_PATIENT_DETAILS_BY_NHSNUMBER_SERVICE, identity))
					.addPayload(getPatientDetailsByNHSNumberRequest.toServiceRequest()).build();

			// send the request
			ITKMessage response = doSend(request);

			// get the payload response
			return new GetPatientDetailsResponse.Builder().serviceResponse(findPayload(response)).build();

		} catch (ITKGatewayException e) {
			throw new MiniServiceException(String.format("Failed mini service operation %s", GET_PATIENT_DETAILS_BY_NHSNUMBER_SERVICE),
					e);
		}

	}

	private COMTMT000016GB01GetPatientDetailsResponseV10 findPayload(ITKMessage response) {
		return response.findPayload(new PayloadQuery<COMTMT000016GB01GetPatientDetailsResponseV10>() {
			@Override
			public COMTMT000016GB01GetPatientDetailsResponseV10 match(Serializable payloadContent) {
				if (payloadContent instanceof JAXBElement<?>) {
					return ((JAXBElement<COMTMT000016GB01GetPatientDetailsResponseV10>) payloadContent).getValue();
				}
				return null;
			}

		});
	}

}
