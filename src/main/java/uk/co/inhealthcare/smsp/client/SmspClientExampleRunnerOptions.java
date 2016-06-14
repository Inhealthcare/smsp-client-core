package uk.co.inhealthcare.smsp.client;

import org.apache.commons.lang3.StringUtils;

import uk.co.inhealthcare.smsp.client.services.RequestContext;
import uk.co.inhealthcare.smsp.client.soap.http.KeyStore;

public class SmspClientExampleRunnerOptions {

	// command line properties
	static final String SERVICE_URL_PROPERTY = "serviceUrl";
	static final String SSL_KEYSTORE_LOCATION_PROPERTY = "sslKeystoreLocation";
	static final String SSL_KEYSTORE_PASSWORD_PROPERTY = "sslKeystorePassword";
	static final String SSL_DEBUG_PROPERTY = "sslDebug";
	static final String USERNAME_PROPERTY = "username";
	static final String CLIENT_SERVICE_URL_PROPERTY = "clientServiceUrl";
	static final String AUDIT_IDENTITY_PROPERTY = "auditIdentity";
	static final String LOG_TRAFFIC_PROPERTY = "logTraffic";
	static final String SERVICE_PROPERTY = "service";

	// field values
	private boolean logTraffic;
	private String service;
	private String username;
	private String clientServiceUrl;
	private String auditIdentity;
	private String sslKeystoreLocation;
	private String sslKeystorePassword;
	private boolean sslDebug;
	private String serviceUrl;

	public SmspClientExampleRunnerOptions(String[] args) {

		serviceUrl = getRequiredProperty(SERVICE_URL_PROPERTY);
		service = getRequiredProperty(SERVICE_PROPERTY);
		username = getRequiredProperty(USERNAME_PROPERTY);
		clientServiceUrl = getRequiredProperty(CLIENT_SERVICE_URL_PROPERTY);
		auditIdentity = getRequiredProperty(AUDIT_IDENTITY_PROPERTY);

		sslKeystoreLocation = getOptionalProperty(SSL_KEYSTORE_LOCATION_PROPERTY);
		sslKeystorePassword = getOptionalProperty(SSL_KEYSTORE_PASSWORD_PROPERTY);

		logTraffic = getFlag(LOG_TRAFFIC_PROPERTY);
		sslDebug = getFlag(SSL_DEBUG_PROPERTY);

	}

	private String getOptionalProperty(String propertyName) {
		return System.getProperty(propertyName);
	}

	private Boolean getFlag(String flagName) {
		return Boolean.valueOf(System.getProperty(flagName));
	}

	private String getRequiredProperty(String propertyName) {
		String property = System.getProperty(propertyName);
		if (StringUtils.isBlank(property))
			throw new IllegalArgumentException(createUsageMessage(propertyName, property));
		return property;
	}

	private String createUsageMessage(String propertyName, String property) {
		return "A required field is missing. Must define -D" + propertyName + "=[value]";
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
