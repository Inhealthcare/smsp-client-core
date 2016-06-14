package uk.co.inhealthcare.smsp.client.model;

public class GPPractice {

	private Address address;
	private Communication communication;
	private Organisation organisation;

	public GPPractice(Address address, Communication communication, Organisation organisation) {
		this.address = address;
		this.communication = communication;
		this.organisation = organisation;
	}
	
	public Organisation getOrganisation() {
		return organisation;
	}

	public Address getAddress() {
		return address;
	}
	
	public Communication getCommunication() {
		return communication;
	}
	

	@Override
	public String toString() {
		return "GPPractice [address=" + address + ", communication=" + communication + "]";
	}

}
