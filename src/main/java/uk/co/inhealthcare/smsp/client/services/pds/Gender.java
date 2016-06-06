package uk.co.inhealthcare.smsp.client.services.pds;

import org.hl7.v3.QUPAMT000002GB01PersonGender;

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
		// TODO Auto-generated method stub
		return null;
	}

}
