package uk.co.inhealthcare.smsp.client.services.pds;

import org.apache.commons.lang3.StringUtils;
import org.hl7.v3.IINHSIdentifierType1;
import org.hl7.v3.QUPAMT000001GB01PersonNHSNumber;
import org.hl7.v3.ST;

public class NHSNumber {

	private static final String SEMANTICS_TEXT = "Person.NHSNumber";
	private static final String OID = "2.16.840.1.113883.2.1.4.1";
	private String number;
	private QUPAMT000001GB01PersonNHSNumber personNHSNumber;

	public NHSNumber(String number) {
		if(StringUtils.isBlank(number)) throw new IllegalArgumentException("Cannot have blank nhsnumber");
		this.number = number;
		generatePersonNHSNumber();
	}

	private void generatePersonNHSNumber() {
		QUPAMT000001GB01PersonNHSNumber nhs = new QUPAMT000001GB01PersonNHSNumber();
		IINHSIdentifierType1 nhsId = new IINHSIdentifierType1();
		nhsId.setRoot(OID);
		nhsId.setExtension(number);
		nhs.setValue(nhsId);
		ST stnhs = new ST();
		stnhs.getContent().add(SEMANTICS_TEXT);
		nhs.setSemanticsText(stnhs);	
		personNHSNumber = nhs;
	}

	public QUPAMT000001GB01PersonNHSNumber toPersonNHSNumber() {
		return personNHSNumber;
	}

}
