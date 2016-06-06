package uk.co.inhealthcare.smsp.client.services.pds;

public enum PDSMiniServiceResponseCode {

	NO_MATCH("DEMOG-0001","No Match"),
	MULTIPLE_MATCHES("DEMOG-0007","Multiple matches found"),
	SUPERCEDED("DEMOG-0017","Superceding NHS number returned"),
	INVALIDATED("DEMOG-0022","NHS Number of response record has been invalidated"),
	NOT_VERIFIED("DEMOG-0040","NHS number not verified"),
	OLD_NUMBER("DEMOG-0042","NHS Number is not a new style number"),
	GENERIC("DEMOG-9999","Generic Spine Service Error"),
	SUCCESS("SMSP-0000","Success"),
	INPUT_INVALID("SMSP-0001","Input message validation error"),
	RESPONSE_INVALID("SMSP-0002","Response message validation error"),
	GENERIC_MINI_SERVICE("SMSP-9999","Generic Spine Mini Service Provider software failure"),
	SPINE_UNAVAILABLE("SMSP-0003","Data returned from local store, Spine unavailable");
	
	private String code;
	private String description;

	private PDSMiniServiceResponseCode(String code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;		
	}
	
	public static PDSMiniServiceResponseCode valueOfCode(String code) {
		PDSMiniServiceResponseCode[] values = PDSMiniServiceResponseCode.values();
		for (PDSMiniServiceResponseCode pdsMiniServiceResponseCode : values) {
			if( pdsMiniServiceResponseCode.matches(code) ) {
				return pdsMiniServiceResponseCode;
			}
		}
		throw new IllegalArgumentException("Could not find a matching response code for " + code);
	}

	private boolean matches(String code) {
		return this.code.equals(code);
	}
	
}
