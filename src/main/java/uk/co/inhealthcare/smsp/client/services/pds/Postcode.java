package uk.co.inhealthcare.smsp.client.services.pds;

import javax.xml.bind.JAXBElement;

import org.hl7.v3.AD.PostalCode;
import org.hl7.v3.ADNHSAddressType1;
import org.hl7.v3.QUPAMT000002GB01PersonPostcode;
import org.hl7.v3.ST;

public class Postcode {
	
	protected final org.hl7.v3.ObjectFactory messageFactory = new org.hl7.v3.ObjectFactory();
	private String postcode;
	
	public Postcode(String postcode) {
		this.postcode = postcode;
	}

	public JAXBElement<QUPAMT000002GB01PersonPostcode> toPersonPostcode() {
		QUPAMT000002GB01PersonPostcode postCode = new QUPAMT000002GB01PersonPostcode();
		ADNHSAddressType1 value = new ADNHSAddressType1();
		
		PostalCode postalCode = new PostalCode();
		postalCode.getContent().add(postcode);
		value.getContent().add(messageFactory.createADPostalCode(postalCode));						
		postCode.setValue(value);
		
		ST st = new ST();
		st.getContent().add("Person.Postcode");
		postCode.setSemanticsText(st);
		return 
				messageFactory.createQUPAMT000002GB01GetNHSNumberRequestV10GrouperPersonPostcode(postCode);
	}

}
