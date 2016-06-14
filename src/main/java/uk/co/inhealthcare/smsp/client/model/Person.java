package uk.co.inhealthcare.smsp.client.model;

public class Person {

	private Gender gender;
	private DateOfBirth dateOfBirth;
	private String deceasedOn;
	private GPPractice gp;

	public Person(Gender gender, DateOfBirth dateOfBirth, String deceasedOn, GPPractice gp) {
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.deceasedOn = deceasedOn;
		this.gp = gp;
	}

	public DateOfBirth getDateOfBirth() {
		return dateOfBirth;
	}

	public String getDeceasedOn() {
		return deceasedOn;
	}

	public Gender getGender() {
		return gender;
	}

	public GPPractice getGp() {
		return gp;
	}

	@Override
	public String toString() {
		return "Person [gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", deceasedOn=" + deceasedOn + ", gp=" + gp
				+ "]";
	}

}
