package uk.co.inhealthcare.smsp.client.smsp.pds;

import uk.co.inhealthcare.smsp.client.itk.ITKGateway;
import uk.co.inhealthcare.smsp.client.smsp.AbstractMiniService;
import uk.co.inhealthcare.smsp.client.smsp.User;

public class VerifyNHSNumberMiniService extends AbstractMiniService {

	public VerifyNHSNumberMiniService(ITKGateway itkGateway) {
		super(itkGateway);
	}

	public VerifyNHSNumberResponse verifyNhsNumber(User user, VerifyNHSNumberRequest request) {
		
		// wrap request in itk
		
		
		// send
		
		// unwrap response
		
		// TODO Auto-generated method stub
		return null;
	}

}
