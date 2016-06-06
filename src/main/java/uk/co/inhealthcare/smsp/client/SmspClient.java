package uk.co.inhealthcare.smsp.client;

import org.apache.commons.lang3.StringUtils;

import uk.co.inhealthcare.smsp.client.itk.ITKGateway;
import uk.co.inhealthcare.smsp.client.itk.SOAPITKGateway;
import uk.co.inhealthcare.smsp.client.services.pds.DateOfBirth;
import uk.co.inhealthcare.smsp.client.services.pds.MiniServiceException;
import uk.co.inhealthcare.smsp.client.services.pds.NHSNumber;
import uk.co.inhealthcare.smsp.client.services.pds.Name;
import uk.co.inhealthcare.smsp.client.services.pds.VerifyNHSNumberMiniService;
import uk.co.inhealthcare.smsp.client.services.pds.VerifyNHSNumberRequest;
import uk.co.inhealthcare.smsp.client.services.pds.VerifyNHSNumberResponse;
import uk.co.inhealthcare.smsp.client.soap.SOAPConnection;
import uk.co.inhealthcare.smsp.client.soap.SimpleSOAPMessageFactory;
import uk.co.inhealthcare.smsp.client.soap.SimpleSOAPSender;
import uk.co.inhealthcare.smsp.client.soap.http.HttpSoapConnection;
import uk.co.inhealthcare.smsp.client.soap.http.KeyStore;

public class SmspClient {

	// example values
	private static final String EXAMPLE_FAMILY_NAME = "Hedgewicke";
	private static final String EXAMPLE_GIVEN_NAME = "Hyacinth";
	private static final String EXAMPLE_NHS_NUMBER = "5552448715";
	private static final String EXAMPLE_DOB = "19881123";

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

		// create the verify nhs number service
		VerifyNHSNumberMiniService service = new VerifyNHSNumberMiniService(itkGateway);

		// create the identity object
		Identity identity = createIdentity();

		// create the request
		VerifyNHSNumberRequest request = createRequest();

		// invoke the service
		VerifyNHSNumberResponse response = service.verifyNhsNumber(identity, request);

		// handle response
		handleResponse(response);

		System.out.println("");
		System.out.println("SMSP-CLIENT-TEST FINISHED");

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

	private static void handleResponse(VerifyNHSNumberResponse response) {

		System.out.println("");
		System.out.println("Verify NHS Number Response");
		System.out.println("");
		System.out.println( "Response getMessageId: " + response.getMessageId() );
		System.out.println( "Response getCode: " + response.getCode().getCode() );
		System.out.println( "Response getCodeDescription: " + response.getCode().getDescription() );
		System.out.println( "Response isVerified: " + response.isVerified() );
		System.out.println( "Response getNhsNumber: " + response.getNhsNumber() );
		
	}

	private static VerifyNHSNumberRequest createRequest() {
		VerifyNHSNumberRequest request = new VerifyNHSNumberRequest.Builder().dateOfBirth(new DateOfBirth(EXAMPLE_DOB))
				.nhsNumber(new NHSNumber(EXAMPLE_NHS_NUMBER))
				.name(new Name.Builder().given(EXAMPLE_GIVEN_NAME).family(EXAMPLE_FAMILY_NAME).build()).build();
		return request;
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