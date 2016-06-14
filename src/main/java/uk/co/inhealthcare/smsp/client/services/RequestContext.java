package uk.co.inhealthcare.smsp.client.services;

import org.apache.commons.lang3.StringUtils;

public class RequestContext {

	private String username;
	private String auditIdentity;
	private String clientServiceUrl;
	private String serviceUrl;

	public RequestContext(String username, String auditIdentity, String clientServiceUrl, String serviceUrl) {
		if (StringUtils.isBlank(username))
			throw new IllegalArgumentException("Cannot have a blank username");
		if (StringUtils.isBlank(auditIdentity))
			throw new IllegalArgumentException("Cannot have a blank auditIdentity");
		if (StringUtils.isBlank(clientServiceUrl))
			throw new IllegalArgumentException("Cannot have a blank clientServiceUrl");
		if (StringUtils.isBlank(serviceUrl))
			throw new IllegalArgumentException("Cannot have a blank serviceUrl");
		this.username = username;
		this.auditIdentity = auditIdentity;
		this.clientServiceUrl = clientServiceUrl;
		this.serviceUrl = serviceUrl;
	}

	public String getAuditIdentity() {
		return auditIdentity;
	}

	public String getClientServiceUrl() {
		return clientServiceUrl;
	}

	public String getUsername() {
		return username;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

}
