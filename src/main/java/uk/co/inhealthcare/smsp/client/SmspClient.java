package uk.co.inhealthcare.smsp.client;

import org.apache.commons.lang3.StringUtils;

import uk.co.inhealthcare.smsp.client.itk.ITKGateway;
import uk.co.inhealthcare.smsp.client.itk.SOAPITKGateway;
import uk.co.inhealthcare.smsp.client.services.pds.MiniServiceException;
import uk.co.inhealthcare.smsp.client.soap.SOAPConnection;
import uk.co.inhealthcare.smsp.client.soap.SimpleSOAPMessageFactory;
import uk.co.inhealthcare.smsp.client.soap.SimpleSOAPSender;
import uk.co.inhealthcare.smsp.client.soap.http.HttpSoapConnection;
import uk.co.inhealthcare.smsp.client.soap.http.KeyStore;

public class SmspClient {

	// command line properties
	private static final String SERVICE_URL_PROPERTY = "serviceUrl";
	private static final String SSL_KEYSTORE_LOCATION_PROPERTY = "sslKeystoreLocation";
	private static final String SSL_KEYSTORE_PASSWORD_PROPERTY = "sslKeystorePassword";
	private static final String SSL_DEBUG_PROPERTY = "sslDebug";
	private static final String USERNAME_PROPERTY = "username";
	private static final String CLIENT_SERVICE_URL_PROPERTY = "clientServiceUrl";
	private static final String AUDIT_IDENTITY_PROPERTY = "auditIdentity";
	private static final String LOG_TRAFFIC_PROPERTY = "logTraffic";
	private static final String DISABLE_COMPRESSION_PROPERTY = "disableCompression";
	private static final String SERVICE_PROPERTY = "service";

	public static void main(String[] args) throws MiniServiceException {

		enableSSLDebug();

		System.out.println("SMSP-CLIENT-TEST");

		// create a factory for soap messages
		SimpleSOAPMessageFactory factory = new SimpleSOAPMessageFactory();

		// create a connection to the smsp soap endpoint
		SOAPConnection soapConnection = new HttpSoapConnection.Builder(getServiceUrl(), factory)
				.useSSL(createKeyStore()).logTraffic(isLogTraffic()).disableCompression(isDisableCompression()).build();

		// create the client of the soap connection for sending soap messages
		SimpleSOAPSender soapSender = new SimpleSOAPSender(soapConnection);

		// create a gateway object that takes itk messages and sends them over
		// soap
		ITKGateway itkGateway = new SOAPITKGateway(soapSender, factory);

		// create the identity object
		Identity identity = createIdentity();
		
		// verify nhs number
		MiniServiceClient client =null;
		switch (getService()) {
		case "verifyNHSNumber":
			client = new VerifyNHSNumberMiniClient(itkGateway, identity);
			break;
		case "getNHSNumber":
			client = new GetNHSNumberMiniServiceClient(itkGateway, identity);
			break;
		default:
			System.exit(1);
			break;
		}
		client.run();		

		System.out.println("");
		System.out.println("SMSP-CLIENT-TEST FINISHED");

	}

	private static String getService() {
		return System.getProperty(SERVICE_PROPERTY);
	}

	private static boolean isDisableCompression() {
		return Boolean.valueOf(System.getProperty(DISABLE_COMPRESSION_PROPERTY));
	}

	private static boolean isLogTraffic() {
		return Boolean.valueOf(System.getProperty(LOG_TRAFFIC_PROPERTY));
	}

	private static Identity createIdentity() {
		String username = System.getProperty(USERNAME_PROPERTY);
		String clientServiceUrl = System.getProperty(CLIENT_SERVICE_URL_PROPERTY);
		String auditIdentity = System.getProperty(AUDIT_IDENTITY_PROPERTY);
		return new Identity(username, auditIdentity, clientServiceUrl, getServiceUrl());
	}


	private static KeyStore createKeyStore() {
		String sslKeystoreLocation = System.getProperty(SSL_KEYSTORE_LOCATION_PROPERTY);
		String sslKeystorePassword = System.getProperty(SSL_KEYSTORE_PASSWORD_PROPERTY);
		if (sslConfigured(sslKeystoreLocation, sslKeystorePassword)) {
			return new KeyStore(sslKeystoreLocation, sslKeystorePassword);
		}
		return null;
	}

	private static Boolean isSSLDebug() {
		return Boolean.valueOf(System.getProperty(SSL_DEBUG_PROPERTY));
	}

	private static String getServiceUrl() {
		String serviceUrl = System.getProperty(SERVICE_URL_PROPERTY);
		if (StringUtils.isBlank(serviceUrl))
			throw new IllegalArgumentException("Must define a service url. -DserviceUrl=https://{url}/itk");
		return serviceUrl;
	}

	private static void enableSSLDebug() {
		if (isSSLDebug()) {
			System.setProperty("javax.net.debug", "all");
		}
	}

	private static boolean sslConfigured(String sslKeystoreLocation, String sslKeystorePassword) {
		return StringUtils.isNotBlank(sslKeystoreLocation) && StringUtils.isNotBlank(sslKeystorePassword);
	}

}