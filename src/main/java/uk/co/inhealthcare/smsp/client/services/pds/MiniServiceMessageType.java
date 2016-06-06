package uk.co.inhealthcare.smsp.client.services.pds;

public enum MiniServiceMessageType {

	getNHSNumberResponse, getNHSNumberRequest, getPatientDetailsResponse, verifyNHSNumberResponse, verifyNHSNumberRequest, getPatientDetailsByNHSNumberRequest, getPatientDetailsBySearchRequest, getPatientDetailsRequest;

	public String getProfileId() {
		return "urn:nhs-en:profile:" + getType();
	}

	public String getType() {
		return name() + "-v1-0";
	}

}
