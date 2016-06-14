package uk.co.inhealthcare.smsp.client.model;

public class Postcode {

	private String postcode;

	public Postcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPostcode() {
		return postcode;
	}

	@Override
	public String toString() {
		return "Postcode [postcode=" + postcode + "]";
	}

}
