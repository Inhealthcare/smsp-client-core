package uk.co.inhealthcare.smsp.client;

import uk.co.inhealthcare.smsp.client.example.ExampleClientFactory;
import uk.co.inhealthcare.smsp.client.example.ExampleClientFactory.Service;
import uk.co.inhealthcare.smsp.client.example.MiniServiceClient;
import uk.co.inhealthcare.smsp.client.itk.ITKGateway;
import uk.co.inhealthcare.smsp.client.itk.SOAPITKGateway;
import uk.co.inhealthcare.smsp.client.services.pds.MiniServiceException;
import uk.co.inhealthcare.smsp.client.soap.SOAPConnection;
import uk.co.inhealthcare.smsp.client.soap.SimpleSOAPMessageFactory;
import uk.co.inhealthcare.smsp.client.soap.SimpleSOAPSender;
import uk.co.inhealthcare.smsp.client.soap.http.HttpSoapConnection;

public class SmspClient {

	private SmspClientOptions options;

	public SmspClient(SmspClientOptions options) {
		this.options = options;
	}

	public void run() throws Exception {

		enableSSLDebug();

		System.out.println("SMSP-CLIENT-TEST");

		// create a factory for soap messages
		SimpleSOAPMessageFactory factory = new SimpleSOAPMessageFactory();

		// create a connection to the smsp soap endpoint
		SOAPConnection soapConnection = new HttpSoapConnection.Builder(options.getServiceUrl(), factory)
				.useSSL(options.createKeyStore()).logTraffic(options.isLogTraffic()).build();

		// create the client of the soap connection for sending soap messages
		SimpleSOAPSender soapSender = new SimpleSOAPSender(soapConnection);

		// create a gateway object that takes itk messages and sends them over
		// soap
		ITKGateway itkGateway = new SOAPITKGateway(soapSender, factory);

		// create the identity object
		Identity identity = options.createIdentity();

		// get an example client
		ExampleClientFactory clients = new ExampleClientFactory(itkGateway, identity);
		MiniServiceClient client = clients.forService(Service.valueOf(options.getService()));
		try {
			client.run();
		} catch (MiniServiceException e) {
			throw new Exception("Could not run example client", e);
		}

		System.out.println("");
		System.out.println("SMSP-CLIENT-TEST FINISHED");

	}

	public void enableSSLDebug() {
		if (options.isSSLDebug()) {
			System.setProperty("javax.net.debug", "all");
		}
	}

}