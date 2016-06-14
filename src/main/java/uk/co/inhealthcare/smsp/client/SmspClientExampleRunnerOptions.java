package uk.co.inhealthcare.smsp.client;

import org.apache.commons.lang3.StringUtils;

import uk.co.inhealthcare.smsp.client.services.RequestContext;
import uk.co.inhealthcare.smsp.client.soap.http.KeyStore;

public class SmspClientExampleRunnerOptions {

	// command line properties
	private static final String SERVICE_URL_PROPERTY = "serviceUrl";
	private static final String SSL_KEYSTORE_LOCATION_PROPERTY = "sslKeystoreLocation";
	private static final String SSL_KEYSTORE_PASSWORD_PROPERTY = "sslKeystorePassword";
	private static final String SSL_DEBUG_PROPERTY = "sslDebug";
	private static final String USERNAME_PROPERTY = "username";
	private static final String CLIENT_SERVICE_URL_PROPERTY = "clientServiceUrl";
	private static final String AUDIT_IDENTITY_PROPERTY = "auditIdentity";
	private static final String LOG_TRAFFIC_PROPERTY = "logTraffic";
	private static final String SERVICE_PROPERTY = "service";

	// field values
	private Boolean logTraffic;
	private String service;
	private String username;
	private String clientServiceUrl;
	private String auditIdentity;
	private String sslKeystoreLocation;
	private String sslKeystorePassword;
	private Boolean sslDebug;
	private String serviceUrl;

	public SmspClientExampleRunnerOptions(String[] args) {

		serviceUrl = System.getProperty(SERVICE_URL_PROPERTY);
		if (StringUtils.isBlank(serviceUrl))
			throw new IllegalArgumentException("Must define a service url. -DserviceUrl=https://{url}/itk");

		logTraffic = Boolean.valueOf(System.getProperty(LOG_TRAFFIC_PROPERTY));
		service = System.getProperty(SERVICE_PROPERTY);
		username = System.getProperty(USERNAME_PROPERTY);
		clientServiceUrl = System.getProperty(CLIENT_SERVICE_URL_PROPERTY);
		auditIdentity = System.getProperty(AUDIT_IDENTITY_PROPERTY);
		sslKeystoreLocation = System.getProperty(SSL_KEYSTORE_LOCATION_PROPERTY);
		sslKeystorePassword = System.getProperty(SSL_KEYSTORE_PASSWORD_PROPERTY);
		sslDebug = Boolean.valueOf(System.getProperty(SSL_DEBUG_PROPERTY));
		
	}

	public String getService() {
		return service;
	}

	public boolean isLogTraffic() {
		return logTraffic;
	}

	public RequestContext createContext() {
		return new RequestContext(username, auditIdentity, clientServiceUrl, serviceUrl);
	}

	public KeyStore createKeyStore() {
		if (sslConfigured()) {
			return new KeyStore(sslKeystoreLocation, sslKeystorePassword);
		}
		return null;
	}

	public Boolean isSSLDebug() {
		return sslDebug;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public boolean sslConfigured() {
		return StringUtils.isNotBlank(sslKeystoreLocation) && StringUtils.isNotBlank(sslKeystorePassword);
	}

}
