package uk.co.inhealthcare.smsp.client.services.pds;

import org.apache.commons.lang3.StringUtils;
import org.hl7.v3.II;
import org.hl7.v3.IINHSIdentifierType1;
import org.hl7.v3.QUPAMT000001GB01PersonNHSNumber;
import org.hl7.v3.QUPAMT000003GB01PersonNHSNumber;
import org.hl7.v3.ST;

public class NHSNumber {

	private static final String SEMANTICS_TEXT = "Person.NHSNumber";
	private static final String OID = "2.16.840.1.113883.2.1.4.1";
	private String number;

	public NHSNumber(String number) {
		if (StringUtils.isBlank(number))
			throw new IllegalArgumentException("Cannot have blank nhsnumber");
		this.number = number;
	}

	public NHSNumber(II ii) {
		this.number = ii.getExtension();
	}
	
	public String getNumber() {
		return number;
	}

	public QUPAMT000001GB01PersonNHSNumber toType1PersonNHSNumber() {

		QUPAMT000001GB01PersonNHSNumber nhs = new QUPAMT000001GB01PersonNHSNumber();
		IINHSIdentifierType1 nhsId = new IINHSIdentifierType1();
		nhsId.setRoot(OID);
		nhsId.setExtension(number);
		nhs.setValue(nhsId);
		ST stnhs = new ST();
		stnhs.getContent().add(SEMANTICS_TEXT);
		nhs.setSemanticsText(stnhs);
		return nhs;

	}

	public QUPAMT000003GB01PersonNHSNumber toType3PersonNHSNumber() {

		QUPAMT000003GB01PersonNHSNumber nhs = new QUPAMT000003GB01PersonNHSNumber();
		IINHSIdentifierType1 nhsId = new IINHSIdentifierType1();
		nhsId.setRoot(OID);
		nhsId.setExtension(number);
		nhs.setValue(nhsId);
		ST stnhs = new ST();
		stnhs.getContent().add("Person.NHSNumber");
		nhs.setSemanticsText(stnhs);
		return nhs;

	}

}
