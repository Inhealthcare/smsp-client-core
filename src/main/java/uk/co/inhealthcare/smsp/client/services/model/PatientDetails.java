package uk.co.inhealthcare.smsp.client.services.model;

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

	private NHSNumber nhsNumber;
	private LocalIdentifier localIdentifier;
	private Name name;
	private List<Address> addresses;
	private List<Communication> communications;
	private Person person;

	public PatientDetails(Builder builder) {
		this.nhsNumber = builder.nhsNumber;
		this.localIdentifier = builder.localIdentifier;
		this.name = builder.name;
		this.addresses = builder.addresses;
		this.communications = builder.communications;
		this.person = builder.person;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public List<Communication> getCommunications() {
		return communications;
	}

	public LocalIdentifier getLocalIdentifier() {
		return localIdentifier;
	}

	public Name getName() {
		return name;
	}

	public NHSNumber getNhsNumber() {
		return nhsNumber;
	}

	public Person getPerson() {
		return person;
	}

	@Override
	public String toString() {
		return "PatientDetails [nhsNumber=" + nhsNumber + ", localIdentifier=" + localIdentifier + ", name=" + name
				+ ", addresses=" + addresses + ", communications=" + communications + ", person=" + person + "]";
	}

}
