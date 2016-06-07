package uk.co.inhealthcare.smsp.client.services.model;

import javax.xml.bind.JAXBElement;

import org.hl7.v3.ADNHSAddressType2;
import org.hl7.v3.COMTMT000016GB01GPPractice;
import org.hl7.v3.COMTMT000016GB01Organization;
import org.hl7.v3.TEL;

public class GPPractice {

	private Address address;
	private Communication communication;

	public GPPractice(COMTMT000016GB01GPPractice practice) {

		ADNHSAddressType2 addr = practice.getAddr();
		if (addr != null) {
			address = new Address(addr);
		}

		TEL telecom = practice.getTelecom();
		if (telecom != null) {
			communication = new Communication(telecom);
		}

		JAXBElement<COMTMT000016GB01Organization> orgWrapper = practice.getLocationOrganization();
		if (orgWrapper != null) {

			COMTMT000016GB01Organization org = orgWrapper.getValue();

			new Organisation(org);

		}

	}

	@Override
	public String toString() {
		return "GPPractice [address=" + address + ", communication=" + communication + "]";
	}

}
