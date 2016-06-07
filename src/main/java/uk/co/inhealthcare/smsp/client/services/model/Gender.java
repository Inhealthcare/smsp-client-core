package uk.co.inhealthcare.smsp.client.services.model;

import javax.xml.bind.JAXBElement;

import org.hl7.v3.QUPAMT000002GB01PersonGender;
import org.hl7.v3.QUPAMT000004GB01PersonGender;
import org.hl7.v3.QUPAMT000005GB01PersonGender;
import org.hl7.v3.ST;

public class Gender {

	private static final String GENDER_CODE = "2.16.840.1.113883.2.1.3.2.4.16.25";

	public enum Type {
		not_known("0", "Not known"), male("1", "Male"), female("2", "Female"), not_specified("9", "Not specified");
		private String code;
		private String description;

		private Type(String code, String description) {
			this.code = code;
			this.description = description;
		}

		public String getCode() {
			return code;
		}

		public String getDescription() {
			return description;
		}

		public static Type valueOfCode(String code) {
			for (Type type : values()) {
				if(type.getCode().equals(code)) {
					return type;
				}
			}
			throw new IllegalArgumentException("Unrecognised gender code");
		}
		
		
		
	}
	
	protected final org.hl7.v3.ObjectFactory messageFactory = new org.hl7.v3.ObjectFactory();
	
	private Type type;

	public Gender(Type type) {
		this.type = type;
	}

	public QUPAMT000002GB01PersonGender toType2PersonGender() {
		QUPAMT000002GB01PersonGender gender = new QUPAMT000002GB01PersonGender();
		org.hl7.v3.QUPAMT000002GB01PersonGender.Value value = new org.hl7.v3.QUPAMT000002GB01PersonGender.Value();
		value.setCode(type.code);
		value.setCodeSystem(GENDER_CODE);
		gender.setValue(value);
		gender.setSemanticsText( createSemanticsText());
		return gender;
	}

	private ST createSemanticsText() {
		ST st = new ST();
		st.getContent().add("Person.Gender");
		return st;
	}

	public QUPAMT000004GB01PersonGender toType4PersonGender() {
		
		QUPAMT000004GB01PersonGender gender = new QUPAMT000004GB01PersonGender();
		org.hl7.v3.QUPAMT000004GB01PersonGender.Value value = new org.hl7.v3.QUPAMT000004GB01PersonGender.Value();
		value.setCode(type.code);
		value.setCodeSystem(GENDER_CODE);
		gender.setValue(value);
		gender.setSemanticsText( createSemanticsText());
		return gender;

	}

	public JAXBElement<QUPAMT000005GB01PersonGender> toType5PersonGender() {

		QUPAMT000005GB01PersonGender gender = new QUPAMT000005GB01PersonGender();
		org.hl7.v3.QUPAMT000005GB01PersonGender.Value value = new org.hl7.v3.QUPAMT000005GB01PersonGender.Value();
		value.setCode(type.code);
		value.setCodeSystem(GENDER_CODE);
		gender.setValue(value);
		gender.setSemanticsText( createSemanticsText());
		return messageFactory.createQUPAMT000005GB01GetPatientDetailsRequestV10GrouperPersonGender(gender);
		
	}

}
