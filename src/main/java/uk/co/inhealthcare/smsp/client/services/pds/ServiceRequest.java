package uk.co.inhealthcare.smsp.client.services.pds;

import javax.xml.bind.JAXBElement;

public class ServiceRequest {

	private String id;
	private JAXBElement<?> content;
	private String profileId;

	public ServiceRequest(String id, String profileId, JAXBElement<?> content) {
		this.id = id;
		this.profileId = profileId;
		this.content = content;
	}

	public JAXBElement<?> getContent() {
		return content;
	}

	public String getId() {
		return id;
	}

	public String getProfileId() {
		return profileId;
	}

}
