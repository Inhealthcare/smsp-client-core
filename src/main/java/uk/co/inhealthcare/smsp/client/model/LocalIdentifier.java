package uk.co.inhealthcare.smsp.client.model;

import org.hl7.v3.II;

public class LocalIdentifier {


	private String identity;

	public LocalIdentifier(String identity) {
		this.identity = identity;
	}

	public LocalIdentifier(II ii) {
		this.identity = ii.getExtension();
	}

	public String getIdentity() {
		return identity;
	}



	@Override
	public String toString() {
		return "LocalIdentifier [identity=" + identity + "]";
	}
	
	

}
