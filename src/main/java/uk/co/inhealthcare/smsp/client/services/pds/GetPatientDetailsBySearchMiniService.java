package uk.co.inhealthcare.smsp.client.services.pds;

import uk.co.inhealthcare.smsp.client.itk.ITKGateway;
import uk.co.inhealthcare.smsp.client.services.Identity;

public class GetPatientDetailsBySearchMiniService extends AbstractGetPatientDetailsMiniService {

	private static final String GET_PATIENT_DETAILS_BY_SEARCH_SERVICE = "urn:nhs-itk:services:201005:getPatientDetailsBySearch-v1-0";

	public GetPatientDetailsBySearchMiniService(ITKGateway itkGateway) {
		super(itkGateway);
	}

	public GetPatientDetailsResponse getPatientDetailsBySearch(Identity identity,
			GetPatientDetailsBySearchRequest getPatientDetailsBySearchRequest) throws MiniServiceException {

		return getPatientDetails(GET_PATIENT_DETAILS_BY_SEARCH_SERVICE,
				getPatientDetailsBySearchRequest.toServiceRequest(), identity);

	}

}
