package uk.co.inhealthcare.smsp.client.services.pds;

import java.io.Serializable;

import javax.xml.bind.JAXBElement;

import org.hl7.v3.COMTMT000016GB01GetPatientDetailsResponseV10;

import uk.co.inhealthcare.smsp.client.itk.ITKGateway;
import uk.co.inhealthcare.smsp.client.itk.ITKGatewayException;
import uk.co.inhealthcare.smsp.client.itk.ITKHeaders;
import uk.co.inhealthcare.smsp.client.itk.ITKMessage;
import uk.co.inhealthcare.smsp.client.itk.PayloadQuery;
import uk.co.inhealthcare.smsp.client.services.BaseITKMiniService;
import uk.co.inhealthcare.smsp.client.services.Identity;

abstract class AbstractGetPatientDetailsMiniService extends BaseITKMiniService {

	public AbstractGetPatientDetailsMiniService(ITKGateway itkGateway) {
		super(itkGateway);
	}

	public GetPatientDetailsResponse getPatientDetails(String serviceName, ServiceRequest serviceRequest,
			Identity identity) throws MiniServiceException {
		try {

			// create the request
			ITKMessage request = createITKMessageBuilder(new ITKHeaders(serviceName, identity))
					.addPayload(serviceRequest).build();

			// send the request
			ITKMessage response = doSend(request);

			// get the payload response
			return buildResponse(response);

		} catch (ITKGatewayException e) {
			throw new MiniServiceException(String.format("Failed mini service operation %s", serviceName), e);
		}
	}

	protected COMTMT000016GB01GetPatientDetailsResponseV10 findPayload(ITKMessage response) {
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

	protected GetPatientDetailsResponse buildResponse(ITKMessage response) {
		return new GetPatientDetailsResponse.Builder().serviceResponse(findPayload(response)).build();
	}

}