package uk.co.inhealthcare.smsp.client.smsp.pds;

public class VerifyNHSNumberRequest {

	public static class Builder {

		private DateOfBirth dob;
		private NHSNumber nhsNumber;
		private Name name;

		public Builder dateOfBirth(DateOfBirth dob) {
			this.dob = dob;
			return this;
		}

		public Builder nhsNumber(NHSNumber nhsNumber) {
			this.nhsNumber = nhsNumber;
			return this;
		}

		public Builder name(Name name) {
			this.name = name;
			return this;
		}
		
		public VerifyNHSNumberRequest build() {
			return new VerifyNHSNumberRequest(this);
		}

	}

	private VerifyNHSNumberRequest(Builder builder) {
		// TODO Auto-generated constructor stub
	}
}
