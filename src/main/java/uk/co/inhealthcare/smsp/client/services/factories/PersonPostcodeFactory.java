package uk.co.inhealthcare.smsp.client.services.factories;

import javax.xml.bind.JAXBElement;

import org.hl7.v3.AD.PostalCode;

import uk.co.inhealthcare.smsp.client.model.Postcode;

import org.hl7.v3.ADNHSAddressType1;
import org.hl7.v3.QUPAMT000002GB01PersonPostcode;
import org.hl7.v3.QUPAMT000004GB01PersonPostcode;
import org.hl7.v3.QUPAMT000005GB01PersonPostcode;
import org.hl7.v3.ST;

public class PersonPostcodeFactory {

	private static final org.hl7.v3.ObjectFactory messageFactory = new org.hl7.v3.ObjectFactory();

	public static JAXBElement<QUPAMT000002GB01PersonPostcode> toPersonPostcode(Postcode postcode) {

		QUPAMT000002GB01PersonPostcode personPostcode = new QUPAMT000002GB01PersonPostcode();
		personPostcode.setValue(createAddress(postcode));
		personPostcode.setSemanticsText(createSemanticsText());
		return messageFactory.createQUPAMT000002GB01GetNHSNumberRequestV10GrouperPersonPostcode(personPostcode);

	}

	private static ST createSemanticsText() {
		ST st = new ST();
		st.getContent().add("Person.Postcode");
		return st;
	}

	private static ADNHSAddressType1 createAddress(Postcode postcode) {
		ADNHSAddressType1 value = new ADNHSAddressType1();
		PostalCode postalCode = new PostalCode();
		postalCode.getContent().add(postcode.getPostcode());
		value.getContent().add(messageFactory.createADPostalCode(postalCode));
		return value;
	}

	public static JAXBElement<QUPAMT000004GB01PersonPostcode> toType4PersonPostcode(Postcode postcode) {

		QUPAMT000004GB01PersonPostcode personPostcode = new QUPAMT000004GB01PersonPostcode();
		personPostcode.setValue(createAddress(postcode));
		personPostcode.setSemanticsText(createSemanticsText());
		return messageFactory
				.createQUPAMT000004GB01GetPatientDetailsBySearchRequestV10GrouperPersonPostcode(personPostcode);

	}

	public static JAXBElement<QUPAMT000005GB01PersonPostcode> toType5PersonPostcode(Postcode postcode) {

		QUPAMT000005GB01PersonPostcode personPostcode = new QUPAMT000005GB01PersonPostcode();
		personPostcode.setValue(createAddress(postcode));
		personPostcode.setSemanticsText(createSemanticsText());
		return messageFactory.createQUPAMT000005GB01GetPatientDetailsRequestV10GrouperPersonPostcode(personPostcode);

	}

}
