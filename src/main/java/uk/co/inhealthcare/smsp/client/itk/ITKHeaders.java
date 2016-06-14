package uk.co.inhealthcare.smsp.client.itk;

import uk.co.inhealthcare.smsp.client.services.RequestContext;
import uk.co.inhealthcare.smsp.client.utils.DCEUtils;

public class ITKHeaders {

	private String service;
	private RequestContext context;
	private String trackingId;

	public ITKHeaders(String service, RequestContext context) {
		this.service = service;
		this.context = context;
		this.trackingId = DCEUtils.createUUID();
	}

	public String getAuditIdentity() {
		return context.getAuditIdentity();
	}

	public String getClientServiceUrl() {
		return context.getClientServiceUrl();
	}

	public String getUsername() {
		return context.getUsername();
	}

	public String getService() {
		return service;
	}

	public String getTrackingId() {
		return trackingId;
	}

	public String getServiceUrl() {
		return context.getServiceUrl();
	}

}
