package uk.co.inhealthcare.smsp.client.services.pds;

import java.util.ArrayList;
import java.util.List;

public class PatientDetails {

	public static class Builder {

		private NHSNumber nhsNumber;
		private LocalIdentifier localIdentifier;
		private Name name;
		private List<Address> addresses = new ArrayList<>();
		private List<Communication> communications = new ArrayList<>();
		private Person person;

		public PatientDetails build() {
			return new PatientDetails(this);
		}

		public void nhsNumber(NHSNumber nhsNumber) {
			this.nhsNumber = nhsNumber;
		}

		public void localIdentifier(LocalIdentifier localIdentifier) {
			this.localIdentifier = localIdentifier;
		}

		public void name(Name name) {
			this.name = name;
		}

		public Builder addAddress(Address address) {
			this.addresses.add(address);
			return this;
		}

		public Builder addCommunication(Communication communication) {
			this.communications.add(communication);
			return this;
		}

		public Builder person(Person person) {
			this.person = person;
			return this;
		}

	}

	public PatientDetails(Builder builder) {

	}

}
