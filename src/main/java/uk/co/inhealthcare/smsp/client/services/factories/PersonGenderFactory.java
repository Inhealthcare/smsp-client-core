package uk.co.inhealthcare.smsp.client.services.factories;

import javax.xml.bind.JAXBElement;

import org.hl7.v3.QUPAMT000002GB01PersonGender;
import org.hl7.v3.QUPAMT000004GB01PersonGender;
import org.hl7.v3.QUPAMT000005GB01PersonGender;
import org.hl7.v3.ST;

import uk.co.inhealthcare.smsp.client.model.Gender;

public class PersonGenderFactory {

	private static  final org.hl7.v3.ObjectFactory messageFactory = new org.hl7.v3.ObjectFactory();
	private static final String GENDER_CODE = "2.16.840.1.113883.2.1.3.2.4.16.25";

	public static  QUPAMT000002GB01PersonGender toType2PersonGender(Gender gender) {
		QUPAMT000002GB01PersonGender personGender = new QUPAMT000002GB01PersonGender();
		org.hl7.v3.QUPAMT000002GB01PersonGender.Value value = new org.hl7.v3.QUPAMT000002GB01PersonGender.Value();
		value.setCode(gender.getType().getCode());
		value.setCodeSystem(GENDER_CODE);
		personGender.setValue(value);
		personGender.setSemanticsText( createSemanticsText());
		return personGender;
	}

	private static  ST createSemanticsText() {
		ST st = new ST();
		st.getContent().add("Person.Gender");
		return st;
	}

	public static  QUPAMT000004GB01PersonGender toType4PersonGender(Gender gender) {
		
		QUPAMT000004GB01PersonGender personGender = new QUPAMT000004GB01PersonGender();
		org.hl7.v3.QUPAMT000004GB01PersonGender.Value value = new org.hl7.v3.QUPAMT000004GB01PersonGender.Value();
		value.setCode(gender.getType().getCode());
		value.setCodeSystem(GENDER_CODE);
		personGender.setValue(value);
		personGender.setSemanticsText( createSemanticsText());
		return personGender;

	}

	public static   JAXBElement<QUPAMT000005GB01PersonGender> toType5PersonGender(Gender gender) {

		QUPAMT000005GB01PersonGender personGender = new QUPAMT000005GB01PersonGender();
		org.hl7.v3.QUPAMT000005GB01PersonGender.Value value = new org.hl7.v3.QUPAMT000005GB01PersonGender.Value();
		value.setCode(gender.getType().getCode());
		value.setCodeSystem(GENDER_CODE);
		personGender.setValue(value);
		personGender.setSemanticsText( createSemanticsText());
		return messageFactory.createQUPAMT000005GB01GetPatientDetailsRequestV10GrouperPersonGender(personGender);
		
	}
	
}
