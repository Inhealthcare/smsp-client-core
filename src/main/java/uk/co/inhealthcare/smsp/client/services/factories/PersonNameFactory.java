package uk.co.inhealthcare.smsp.client.services.factories;

import javax.xml.bind.JAXBElement;

import org.apache.commons.lang3.StringUtils;
import org.hl7.v3.CsEntityNameUse;
import org.hl7.v3.EnFamily;
import org.hl7.v3.EnGiven;
import org.hl7.v3.PNNHSPersonNameType1;
import org.hl7.v3.PNNHSPersonNameType3;
import org.hl7.v3.QUPAMT000001GB01PersonName;
import org.hl7.v3.QUPAMT000002GB01PersonName;
import org.hl7.v3.QUPAMT000003GB01PersonName;
import org.hl7.v3.QUPAMT000004GB01PersonName;
import org.hl7.v3.QUPAMT000005GB01PersonName;
import org.hl7.v3.ST;

import uk.co.inhealthcare.smsp.client.model.Name;

public class PersonNameFactory {
	
	private static final org.hl7.v3.ObjectFactory messageFactory = new org.hl7.v3.ObjectFactory();

	public static JAXBElement<QUPAMT000001GB01PersonName> toType1PersonName(Name name) {

		QUPAMT000001GB01PersonName personName = new QUPAMT000001GB01PersonName();
		PNNHSPersonNameType1 theName = new PNNHSPersonNameType1();

		for (String g : name.getGiven()) {
			EnGiven enGiven = new EnGiven();
			enGiven.getContent().add(g);
			theName.getContent().add(messageFactory.createENGiven(enGiven));
		}

		if (StringUtils.isNotBlank(name.getFamily())) {
			EnFamily enFamily = new EnFamily();
			enFamily.getContent().add(name.getFamily());
			theName.getContent().add(messageFactory.createENFamily(enFamily));
		}

		for (CsEntityNameUse csEntityNameUse : name.getUse()) {
			theName.getUse().add(csEntityNameUse);
		}

		personName.setValue(theName);
		personName.setSemanticsText(createSemanticsText());
		return messageFactory.createQUPAMT000001GB01VerifyNHSNumberRequestV10GrouperPersonName(personName);

	}

	private static  ST createSemanticsText() {
		ST stName = new ST();
		stName.getContent().add("Person.Name");
		return stName;
	}

	public static QUPAMT000002GB01PersonName toType2PersonName(Name name) {
		QUPAMT000002GB01PersonName personName = new QUPAMT000002GB01PersonName();
		PNNHSPersonNameType3 theName = new PNNHSPersonNameType3();

		for (String g : name.getGiven()) {
			EnGiven enGiven = new EnGiven();
			enGiven.getContent().add(g);
			theName.getContent().add(messageFactory.createENGiven(enGiven));
		}

		EnFamily enFamily = new EnFamily();
		enFamily.getContent().add(name.getFamily());
		theName.getContent().add(messageFactory.createENFamily(enFamily));

		personName.setValue(theName);
		personName.setSemanticsText(createSemanticsText());
		return personName;
	}

	public static JAXBElement<QUPAMT000003GB01PersonName> toType3PersonName(Name name) {

		QUPAMT000003GB01PersonName personName = new QUPAMT000003GB01PersonName();
		PNNHSPersonNameType1 theName = new PNNHSPersonNameType1();

		for (String g : name.getGiven()) {
			EnGiven enGiven = new EnGiven();
			enGiven.getContent().add(g);
			theName.getContent().add(messageFactory.createENGiven(enGiven));
		}

		if (StringUtils.isNotBlank(name.getFamily())) {
			EnFamily enFamily = new EnFamily();
			enFamily.getContent().add(name.getFamily());
			theName.getContent().add(messageFactory.createENFamily(enFamily));
		}

		personName.setValue(theName);
		personName.setSemanticsText(createSemanticsText());

		return messageFactory.createQUPAMT000003GB01GetPatientDetailsByNHSNumberRequestV10GrouperPersonName(personName);

	}

	public static QUPAMT000004GB01PersonName toType4PersonName(Name name) {

		QUPAMT000004GB01PersonName personName = new QUPAMT000004GB01PersonName();
		PNNHSPersonNameType3 theName = new PNNHSPersonNameType3();

		for (String g : name.getGiven()) {
			EnGiven enGiven = new EnGiven();
			enGiven.getContent().add(g);
			theName.getContent().add(messageFactory.createENGiven(enGiven));
		}

		EnFamily enFamily = new EnFamily();
		enFamily.getContent().add(name.getFamily());
		theName.getContent().add(messageFactory.createENFamily(enFamily));

		personName.setValue(theName);
		personName.setSemanticsText(createSemanticsText());

		return personName;

	}

	public static JAXBElement<QUPAMT000005GB01PersonName> toType5PersonName(Name name) {

		QUPAMT000005GB01PersonName personName = new QUPAMT000005GB01PersonName();
		PNNHSPersonNameType1 theName = new PNNHSPersonNameType1();

		for (String g : name.getGiven()) {
			EnGiven enGiven = new EnGiven();
			enGiven.getContent().add(g);
			theName.getContent().add(messageFactory.createENGiven(enGiven));
		}

		if (StringUtils.isNotBlank(name.getFamily())) {
			EnFamily enFamily = new EnFamily();
			enFamily.getContent().add(name.getFamily());
			theName.getContent().add(messageFactory.createENFamily(enFamily));
		}

		personName.setValue(theName);
		personName.setSemanticsText(createSemanticsText());

		return messageFactory.createQUPAMT000005GB01GetPatientDetailsRequestV10GrouperPersonName(personName);

	}
	
}
