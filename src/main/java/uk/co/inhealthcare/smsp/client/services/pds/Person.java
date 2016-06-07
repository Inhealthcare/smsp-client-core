package uk.co.inhealthcare.smsp.client.services.pds;

import javax.xml.bind.JAXBElement;

import org.hl7.v3.ADNHSAddressType2;
import org.hl7.v3.COMTMT000016GB01GPPractice;
import org.hl7.v3.COMTMT000016GB01Organization;
import org.hl7.v3.COMTMT000016GB01Person;
import org.hl7.v3.COMTMT000016GB01Person.AdministrativeGenderCode;
import org.hl7.v3.TEL;
import org.hl7.v3.TSNHSTimestampType1;
import org.hl7.v3.TSNHSTimestampType3;

import uk.co.inhealthcare.smsp.client.services.pds.Gender.Type;

public class Person {

	private Gender gender;
	private DateOfBirth dateOfBirth;
	private String deceasedOn;
	private Address address;
	private Communication communication;

	public Person(COMTMT000016GB01Person person) {

		AdministrativeGenderCode genderCode = person.getAdministrativeGenderCode();
		if (genderCode != null) {
			gender = new Gender(Type.valueOfCode(genderCode.getCode()));
		}
		
		TSNHSTimestampType3 birthTime = person.getBirthTime();
		if(birthTime!=null) {
			dateOfBirth = new DateOfBirth( birthTime.getValue() );
		}
		
		TSNHSTimestampType1 deceasedTime = person.getDeceasedTime();
		if(deceasedTime!=null) {
			deceasedOn = deceasedTime.getValue();
		}
		
		JAXBElement<COMTMT000016GB01GPPractice> gpwrapper = person.getGPPractice();
		if(gpwrapper!=null) {
			
			COMTMT000016GB01GPPractice practice = gpwrapper.getValue();
			
			ADNHSAddressType2 addr = practice.getAddr();
			if(addr != null) {			
				address = new Address(addr);				
			}
			
			TEL telecom = practice.getTelecom();
			if(telecom!=null) {
				communication = new Communication(telecom);
			}
			
			JAXBElement<COMTMT000016GB01Organization> orgWrapper = practice.getLocationOrganization();
			if( orgWrapper != null ) {
				
				COMTMT000016GB01Organization org = orgWrapper.getValue();
				
				new Organisation( org );
				
			}
		}
		
	}

}
