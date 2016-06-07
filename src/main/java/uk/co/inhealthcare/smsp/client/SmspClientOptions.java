package uk.co.inhealthcare.smsp.client;

import org.apache.commons.lang3.StringUtils;

import uk.co.inhealthcare.smsp.client.soap.http.KeyStore;

public class SmspClientOptions {

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

	public SmspClientOptions(String[] args) {
		// TODO Auto-generated constructor stub
	}

	public String getService() {
		return System.getProperty(SERVICE_PROPERTY);
	}

	public boolean isDisableCompression() {
		return Boolean.valueOf(System.getProperty(DISABLE_COMPRESSION_PROPERTY));
	}

	public boolean isLogTraffic() {
		return Boolean.valueOf(System.getProperty(LOG_TRAFFIC_PROPERTY));
	}

	public Identity createIdentity() {
		String username = System.getProperty(USERNAME_PROPERTY);
		String clientServiceUrl = System.getProperty(CLIENT_SERVICE_URL_PROPERTY);
		String auditIdentity = System.getProperty(AUDIT_IDENTITY_PROPERTY);
		return new Identity(username, auditIdentity, clientServiceUrl, getServiceUrl());
	}

	public KeyStore createKeyStore() {
		String sslKeystoreLocation = System.getProperty(SSL_KEYSTORE_LOCATION_PROPERTY);
		String sslKeystorePassword = System.getProperty(SSL_KEYSTORE_PASSWORD_PROPERTY);
		if (sslConfigured(sslKeystoreLocation, sslKeystorePassword)) {
			return new KeyStore(sslKeystoreLocation, sslKeystorePassword);
		}
		return null;
	}

	public Boolean isSSLDebug() {
		return Boolean.valueOf(System.getProperty(SSL_DEBUG_PROPERTY));
	}

	public String getServiceUrl() {
		String serviceUrl = System.getProperty(SERVICE_URL_PROPERTY);
		if (StringUtils.isBlank(serviceUrl))
			throw new IllegalArgumentException("Must define a service url. -DserviceUrl=https://{url}/itk");
		return serviceUrl;
	}


	public boolean sslConfigured(String sslKeystoreLocation, String sslKeystorePassword) {
		return StringUtils.isNotBlank(sslKeystoreLocation) && StringUtils.isNotBlank(sslKeystorePassword);
	}

}
