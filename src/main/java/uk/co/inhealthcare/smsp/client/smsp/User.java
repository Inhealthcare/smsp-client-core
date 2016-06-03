package uk.co.inhealthcare.smsp.client.smsp;

import org.apache.commons.lang3.StringUtils;

public class User {

	private String username;
	private String auditIdentity;
	private String clientServiceUrl;

	public User(String username, String auditIdentity, String clientServiceUrl) {
		if (StringUtils.isBlank(username))
			throw new IllegalArgumentException("Cannot have a blank username");
		if (StringUtils.isBlank(auditIdentity))
			throw new IllegalArgumentException("Cannot have a blank auditIdentity");
		if (StringUtils.isBlank(clientServiceUrl))
			throw new IllegalArgumentException("Cannot have a blank clientServiceUrl");
		this.username = username;
		this.auditIdentity = auditIdentity;
		this.clientServiceUrl = clientServiceUrl;
	}

}
