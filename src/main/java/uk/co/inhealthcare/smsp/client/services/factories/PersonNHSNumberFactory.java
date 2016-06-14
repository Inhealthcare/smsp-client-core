package uk.co.inhealthcare.smsp.client.services.factories;

import javax.xml.bind.JAXBElement;

import org.hl7.v3.IINHSIdentifierType1;
import org.hl7.v3.QUPAMT000001GB01PersonNHSNumber;
import org.hl7.v3.QUPAMT000003GB01PersonNHSNumber;
import org.hl7.v3.QUPAMT000005GB01PersonNHSNumber;
import org.hl7.v3.ST;

import uk.co.inhealthcare.smsp.client.model.NHSNumber;

public class PersonNHSNumberFactory {

	private static final org.hl7.v3.ObjectFactory messageFactory = new org.hl7.v3.ObjectFactory();

	private static final String SEMANTICS_TEXT = "Person.NHSNumber";
	private static final String OID = "2.16.840.1.113883.2.1.4.1";

	public static QUPAMT000001GB01PersonNHSNumber toType1PersonNHSNumber(NHSNumber nhsNumber) {

		QUPAMT000001GB01PersonNHSNumber nhs = new QUPAMT000001GB01PersonNHSNumber();
		nhs.setValue(createIdentifier(nhsNumber));
		nhs.setSemanticsText(createSemanticsText());
		return nhs;

	}

	private static ST createSemanticsText() {
		ST stnhs = new ST();
		stnhs.getContent().add(SEMANTICS_TEXT);
		return stnhs;
	}

	private static IINHSIdentifierType1 createIdentifier(NHSNumber nhsNumber) {
		IINHSIdentifierType1 nhsId = new IINHSIdentifierType1();
		nhsId.setRoot(OID);
		nhsId.setExtension(nhsNumber.getNumber());
		return nhsId;
	}

	public static QUPAMT000003GB01PersonNHSNumber toType3PersonNHSNumber(NHSNumber nhsNumber) {

		QUPAMT000003GB01PersonNHSNumber nhs = new QUPAMT000003GB01PersonNHSNumber();
		nhs.setValue(createIdentifier(nhsNumber));
		nhs.setSemanticsText(createSemanticsText());
		return nhs;

	}

	public static JAXBElement<QUPAMT000005GB01PersonNHSNumber> toType5PersonNHSNumber(NHSNumber nhsNumber) {

		QUPAMT000005GB01PersonNHSNumber nhs = new QUPAMT000005GB01PersonNHSNumber();
		nhs.setValue(createIdentifier(nhsNumber));
		nhs.setSemanticsText(createSemanticsText());
		return messageFactory.createQUPAMT000005GB01GetPatientDetailsRequestV10GrouperPersonNHSNumber(nhs);

	}

}
