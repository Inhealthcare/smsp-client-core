package uk.co.inhealthcare.smsp.client.services.pds;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.hl7.v3.AD.PostalCode;
import org.hl7.v3.AD.StreetAddressLine;
import org.hl7.v3.ADNHSAddressType2;
import org.hl7.v3.CsPostalAddressUse;

public class Address {

	// should only be one
	private List<CsPostalAddressUse> use = new ArrayList<>();
	
	private List<String> addressLines = new ArrayList<>();
	private String postalCode;

	public Address(ADNHSAddressType2 adnhsAddressType2) {

		List<CsPostalAddressUse> uses = adnhsAddressType2.getUse();
		if (uses != null) {
			this.use.addAll(uses);
		}

		List<Serializable> content = adnhsAddressType2.getContent();
		for (Serializable serializable : content) {
			if (serializable instanceof JAXBElement<?>) {
				Object value = ((JAXBElement) serializable).getValue();
				if (value instanceof StreetAddressLine) {
					addressLines.add(MessageUtils.stringValueOf(((StreetAddressLine) value).getContent()));
				}
				if (value instanceof PostalCode) {
					postalCode = MessageUtils.stringValueOf(((StreetAddressLine) value).getContent());
				}
			}
		}

	}

}
