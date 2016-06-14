package uk.co.inhealthcare.smsp.client.model;

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

		public static Type valueOfCode(String code) {
			for (Type type : values()) {
				if (type.getCode().equals(code)) {
					return type;
				}
			}
			throw new IllegalArgumentException("Unrecognised gender code");
		}

	}

	private Type type;

	public Gender(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Gender [type=" + type + "]";
	}

}
