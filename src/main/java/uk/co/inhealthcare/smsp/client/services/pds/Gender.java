package uk.co.inhealthcare.smsp.client.services.pds;

import org.hl7.v3.QUPAMT000002GB01PersonGender;
import org.hl7.v3.ST;

public class Gender {

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
	}
	
	private Type type;

	public Gender(Type type) {
		this.type = type;
	}

	public QUPAMT000002GB01PersonGender toPersonGender() {
		QUPAMT000002GB01PersonGender gender = new QUPAMT000002GB01PersonGender();
		org.hl7.v3.QUPAMT000002GB01PersonGender.Value value = new org.hl7.v3.QUPAMT000002GB01PersonGender.Value();
		value.setCode(type.code);
		value.setCodeSystem("2.16.840.1.113883.2.1.3.2.4.16.25");
		gender.setValue(value);
		ST st = new ST();
		st.getContent().add("Person.Gender");
		gender.setSemanticsText(st);
		return gender;
	}

}
