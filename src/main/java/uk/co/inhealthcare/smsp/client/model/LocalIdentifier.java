package uk.co.inhealthcare.smsp.client.model;

public class LocalIdentifier {

	private String identity;

	public LocalIdentifier(String identity) {
		this.identity = identity;
	}

	public String getIdentity() {
		return identity;
	}

	@Override
	public String toString() {
		return "LocalIdentifier [identity=" + identity + "]";
	}

}
