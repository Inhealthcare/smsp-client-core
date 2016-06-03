package uk.co.inhealthcare.smsp.client.services.pds;

import javax.xml.bind.JAXBElement;

public class ServiceRequest {

	private String id;
	private JAXBElement<?> content;

	public ServiceRequest(String id, JAXBElement<?> content) {
		this.id = id;
		this.content = content;
	}

	public JAXBElement<?> getContent() {
		return content;
	}

	public String getId() {
		return id;
	}

}
