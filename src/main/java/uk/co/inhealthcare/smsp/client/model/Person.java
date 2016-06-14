package uk.co.inhealthcare.smsp.client.model;

import javax.xml.bind.JAXBElement;

import org.hl7.v3.COMTMT000016GB01GPPractice;
import org.hl7.v3.COMTMT000016GB01Person;
import org.hl7.v3.COMTMT000016GB01Person.AdministrativeGenderCode;

import uk.co.inhealthcare.smsp.client.model.Gender.Type;

import org.hl7.v3.TSNHSTimestampType1;
import org.hl7.v3.TSNHSTimestampType3;

public class Person {

	private Gender gender;
	private DateOfBirth dateOfBirth;
	private String deceasedOn;
	private GPPractice gp;

	public Person(COMTMT000016GB01Person person) {

		AdministrativeGenderCode genderCode = person.getAdministrativeGenderCode();
		if (genderCode != null) {
			gender = new Gender(Type.valueOfCode(genderCode.getCode()));
		}

		TSNHSTimestampType3 birthTime = person.getBirthTime();
		if (birthTime != null) {
			dateOfBirth = new DateOfBirth(birthTime.getValue());
		}

		TSNHSTimestampType1 deceasedTime = person.getDeceasedTime();
		if (deceasedTime != null) {
			deceasedOn = deceasedTime.getValue();
		}

		JAXBElement<COMTMT000016GB01GPPractice> gpwrapper = person.getGPPractice();
		if (gpwrapper != null) {

			COMTMT000016GB01GPPractice practice = gpwrapper.getValue();

			this.gp = new GPPractice(practice);

		}

	}

	@Override
	public String toString() {
		return "Person [gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", deceasedOn=" + deceasedOn + ", gp=" + gp
				+ "]";
	}

}
