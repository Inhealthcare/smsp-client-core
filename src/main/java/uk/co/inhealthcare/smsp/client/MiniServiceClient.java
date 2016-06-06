package uk.co.inhealthcare.smsp.client;

import uk.co.inhealthcare.smsp.client.services.pds.MiniServiceException;

public interface MiniServiceClient {

	public void run() throws MiniServiceException;

}
