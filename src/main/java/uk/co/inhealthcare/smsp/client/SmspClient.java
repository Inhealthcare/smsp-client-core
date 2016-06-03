package uk.co.inhealthcare.smsp.client;

import org.apache.commons.lang3.StringUtils;

import uk.co.inhealthcare.smsp.client.itk.ITKGateway;
import uk.co.inhealthcare.smsp.client.itk.SOAPITKGateway;
import uk.co.inhealthcare.smsp.client.smsp.Identity;
import uk.co.inhealthcare.smsp.client.smsp.pds.DateOfBirth;
import uk.co.inhealthcare.smsp.client.smsp.pds.NHSNumber;
import uk.co.inhealthcare.smsp.client.smsp.pds.Name;
import uk.co.inhealthcare.smsp.client.smsp.pds.VerifyNHSNumberMiniService;
import uk.co.inhealthcare.smsp.client.smsp.pds.VerifyNHSNumberRequest;
import uk.co.inhealthcare.smsp.client.smsp.pds.VerifyNHSNumberResponse;
import uk.co.inhealthcare.smsp.client.soap.SOAPConnection;
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

	public static void main(String[] args) {

		enableSSLDebug();

		System.out.println("SMSP-CLIENT-TEST");

		// create a connection to the smsp soap endpoint
		SOAPConnection soapConnection = new HttpSoapConnection.Builder(getServiceUrl()).useSSL(createKeyStore())
				.build();

		// create the client of the soap connection for sending soap messages
		SimpleSOAPSender soapSender = new SimpleSOAPSender(soapConnection);

		// create a gateway object that takes itk messages and sends them over
		// soap
		ITKGateway itkGateway = new SOAPITKGateway(soapSender);

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

		System.out.println("SMSP-CLIENT-TEST FINISHED");

	}

	private static Identity createIdentity() {
		String username = System.getProperty(USERNAME_PROPERTY);
		String clientServiceUrl = System.getProperty(CLIENT_SERVICE_URL_PROPERTY);
		String auditIdentity = System.getProperty(AUDIT_IDENTITY_PROPERTY);
		return new Identity(username, auditIdentity, clientServiceUrl);
	}

	private static void handleResponse(VerifyNHSNumberResponse response) {
		System.out.println("RESPONSE: " + response);
	}

	private static VerifyNHSNumberRequest createRequest() {
		VerifyNHSNumberRequest request = 
				new VerifyNHSNumberRequest.Builder()
				.dateOfBirth(new DateOfBirth(EXAMPLE_DOB))
				.nhsNumber(new NHSNumber(EXAMPLE_NHS_NUMBER))
				.name(new Name.Builder().given(EXAMPLE_GIVEN_NAME).family(EXAMPLE_FAMILY_NAME).build())
				.build();
		System.out.println("REQUEST: " + request);
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

	private static Boolean getSSLDebug() {
		return Boolean.valueOf(System.getProperty(SSL_DEBUG_PROPERTY));
	}

	private static String getServiceUrl() {
		String serviceUrl = System.getProperty(SERVICE_URL_PROPERTY);
		if (StringUtils.isBlank(serviceUrl))
			throw new IllegalArgumentException("Must define a service url. -DserviceUrl=https://{url}/itk");
		return serviceUrl;
	}

	private static void enableSSLDebug() {
		if (getSSLDebug()) {
			System.setProperty("javax.net.debug", "all");
		}
	}

	private static boolean sslConfigured(String sslKeystoreLocation, String sslKeystorePassword) {
		return StringUtils.isNotBlank(sslKeystoreLocation) && StringUtils.isNotBlank(sslKeystorePassword);
	}

}
