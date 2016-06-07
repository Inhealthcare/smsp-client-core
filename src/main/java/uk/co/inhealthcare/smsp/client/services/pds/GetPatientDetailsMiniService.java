package uk.co.inhealthcare.smsp.client.services.pds;

import uk.co.inhealthcare.smsp.client.Identity;
import uk.co.inhealthcare.smsp.client.itk.ITKGateway;

public class GetPatientDetailsMiniService extends AbstractGetPatientDetailsMiniService {

	private static final String GET_PATIENT_DETAILS_SERVICE = "urn:nhs-itk:services:201005:getPatientDetails-v1-0";

	public GetPatientDetailsMiniService(ITKGateway itkGateway) {
		super(itkGateway);
	}

	public GetPatientDetailsResponse getPatientDetails(Identity identity,
			GetPatientDetailsRequest getPatientDetailsRequest) throws MiniServiceException {

		return getPatientDetails(GET_PATIENT_DETAILS_SERVICE, getPatientDetailsRequest.toServiceRequest(), identity);

	}

}
