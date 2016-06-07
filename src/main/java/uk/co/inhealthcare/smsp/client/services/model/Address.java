package uk.co.inhealthcare.smsp.client.services.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.hl7.v3.ADNHSAddressType2;
import org.hl7.v3.ADXP;
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
				QName name = ((JAXBElement<?>) serializable).getName();
				
				if(name.getLocalPart().equals("postalCode")) {
					if (value instanceof ADXP) {
						postalCode = MessageUtils.stringValueOf(((ADXP) value).getContent());
					}					
				}
				if( name.getLocalPart().equals("streetAddressLine") ) {
					if (value instanceof ADXP) {
						addressLines.add(MessageUtils.stringValueOf(((ADXP) value).getContent()));
					}					
				}
				
			}
		}

	}

	@Override
	public String toString() {
		return "Address [use=" + use + ", addressLines=" + addressLines + ", postalCode=" + postalCode + "]";
	}

}
