package uk.co.inhealthcare.smsp.client.services.pds;

import uk.co.inhealthcare.smsp.client.itk.ITKGateway;
import uk.co.inhealthcare.smsp.client.services.RequestContext;

public class GetPatientDetailsByNHSNumberMiniService extends AbstractGetPatientDetailsMiniService {

	private static final String GET_PATIENT_DETAILS_BY_NHSNUMBER_SERVICE = "urn:nhs-itk:services:201005:getPatientDetailsByNHSNumber-v1-0";

	public GetPatientDetailsByNHSNumberMiniService(ITKGateway itkGateway) {
		super(itkGateway);
	}

	public GetPatientDetailsResponse getPatientDetailsByNHSNumber(RequestContext context,
			GetPatientDetailsByNHSNumberRequest getPatientDetailsByNHSNumberRequest) throws MiniServiceException {

		return getPatientDetails(GET_PATIENT_DETAILS_BY_NHSNUMBER_SERVICE,
				getPatientDetailsByNHSNumberRequest.toServiceRequest(), context);

	}

}
