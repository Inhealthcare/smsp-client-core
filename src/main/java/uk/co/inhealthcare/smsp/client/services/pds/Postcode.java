package uk.co.inhealthcare.smsp.client.services.pds;

import javax.xml.bind.JAXBElement;

import org.hl7.v3.AD.PostalCode;
import org.hl7.v3.ADNHSAddressType1;
import org.hl7.v3.QUPAMT000002GB01PersonPostcode;
import org.hl7.v3.QUPAMT000004GB01PersonPostcode;
import org.hl7.v3.QUPAMT000005GB01PersonPostcode;
import org.hl7.v3.ST;

public class Postcode {

	protected final org.hl7.v3.ObjectFactory messageFactory = new org.hl7.v3.ObjectFactory();
	private String postcode;

	public Postcode(String postcode) {
		this.postcode = postcode;
	}

	public JAXBElement<QUPAMT000002GB01PersonPostcode> toPersonPostcode() {

		QUPAMT000002GB01PersonPostcode postCode = new QUPAMT000002GB01PersonPostcode();
		postCode.setValue(createAddress());
		postCode.setSemanticsText(createSemanticsText());
		return messageFactory.createQUPAMT000002GB01GetNHSNumberRequestV10GrouperPersonPostcode(postCode);

	}

	private ST createSemanticsText() {
		ST st = new ST();
		st.getContent().add("Person.Postcode");
		return st;
	}

	private ADNHSAddressType1 createAddress() {
		ADNHSAddressType1 value = new ADNHSAddressType1();
		PostalCode postalCode = new PostalCode();
		postalCode.getContent().add(postcode);
		value.getContent().add(messageFactory.createADPostalCode(postalCode));
		return value;
	}

	public JAXBElement<QUPAMT000004GB01PersonPostcode> toType4PersonPostcode() {

		QUPAMT000004GB01PersonPostcode postCode = new QUPAMT000004GB01PersonPostcode();
		postCode.setValue(createAddress());
		postCode.setSemanticsText(createSemanticsText());
		return messageFactory.createQUPAMT000004GB01GetPatientDetailsBySearchRequestV10GrouperPersonPostcode(postCode);

	}

	public JAXBElement<QUPAMT000005GB01PersonPostcode> toType5PersonPostcode() {

		QUPAMT000005GB01PersonPostcode postCode = new QUPAMT000005GB01PersonPostcode();
		postCode.setValue(createAddress());
		postCode.setSemanticsText(createSemanticsText());
		return messageFactory.createQUPAMT000005GB01GetPatientDetailsRequestV10GrouperPersonPostcode(postCode);

	}

}
