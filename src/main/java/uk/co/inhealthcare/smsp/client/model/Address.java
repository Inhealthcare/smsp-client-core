package uk.co.inhealthcare.smsp.client.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hl7.v3.CsPostalAddressUse;

public class Address {

	// should only be one
	private List<CsPostalAddressUse> use = new ArrayList<>();
	private List<String> addressLines = new ArrayList<>();
	private String postalCode;

	public Address(List<CsPostalAddressUse> use, List<String> addressLines, String postalCode) {
		this.use.addAll(use);
		this.addressLines.addAll(addressLines);
		this.postalCode = postalCode;
	}

	public Collection<String> getAddressLines() {
		return addressLines;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public Collection<CsPostalAddressUse> getUse() {
		return use;
	}

	@Override
	public String toString() {
		return "Address [use=" + use + ", addressLines=" + addressLines + ", postalCode=" + postalCode + "]";
	}

}
