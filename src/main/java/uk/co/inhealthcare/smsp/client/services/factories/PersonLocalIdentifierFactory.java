package uk.co.inhealthcare.smsp.client.services.factories;

import javax.xml.bind.JAXBElement;

import org.hl7.v3.IINHSIdentifierType3;
import org.hl7.v3.QUPAMT000002GB01PersonLocalIdentifier;
import org.hl7.v3.QUPAMT000003GB01PersonLocalIdentifier;
import org.hl7.v3.QUPAMT000004GB01PersonLocalIdentifier;
import org.hl7.v3.QUPAMT000005GB01PersonLocalIdentifier;
import org.hl7.v3.ST;

import uk.co.inhealthcare.smsp.client.model.LocalIdentifier;

public class PersonLocalIdentifierFactory {

	private static final String LOCAL_OID = "1.3.5.7.9.24.68.1";

	private static final org.hl7.v3.ObjectFactory messageFactory = new org.hl7.v3.ObjectFactory();

	public static JAXBElement<QUPAMT000002GB01PersonLocalIdentifier> toType2PersonLocalIdentifier(
			LocalIdentifier localIdentifier) {

		QUPAMT000002GB01PersonLocalIdentifier local = new QUPAMT000002GB01PersonLocalIdentifier();
		local.setValue(createIdentifier(localIdentifier));
		local.setSemanticsText(createSematicsText());
		return messageFactory.createQUPAMT000002GB01GetNHSNumberRequestV10GrouperPersonLocalIdentifier(local);

	}

	private static ST createSematicsText() {
		ST st = new ST();
		st.getContent().add("Person.LocalIdentifier");
		return st;
	}

	public static JAXBElement<QUPAMT000003GB01PersonLocalIdentifier> toType3PersonLocalIdentifier(
			LocalIdentifier localIdentifier) {

		QUPAMT000003GB01PersonLocalIdentifier local = new QUPAMT000003GB01PersonLocalIdentifier();
		local.setValue(createIdentifier(localIdentifier));
		local.setSemanticsText(createSematicsText());
		return messageFactory
				.createQUPAMT000003GB01GetPatientDetailsByNHSNumberRequestV10GrouperPersonLocalIdentifier(local);

	}

	public static JAXBElement<QUPAMT000004GB01PersonLocalIdentifier> toType4PersonLocalIdentifier(
			LocalIdentifier localIdentifier) {

		QUPAMT000004GB01PersonLocalIdentifier local = new QUPAMT000004GB01PersonLocalIdentifier();
		local.setValue(createIdentifier(localIdentifier));
		local.setSemanticsText(createSematicsText());
		return messageFactory
				.createQUPAMT000004GB01GetPatientDetailsBySearchRequestV10GrouperPersonLocalIdentifier(local);

	}

	public static JAXBElement<QUPAMT000005GB01PersonLocalIdentifier> toType5PersonLocalIdentifier(
			LocalIdentifier localIdentifier) {

		QUPAMT000005GB01PersonLocalIdentifier local = new QUPAMT000005GB01PersonLocalIdentifier();
		local.setValue(createIdentifier(localIdentifier));
		local.setSemanticsText(createSematicsText());
		return messageFactory.createQUPAMT000005GB01GetPatientDetailsRequestV10GrouperPersonLocalIdentifier(local);

	}

	private static IINHSIdentifierType3 createIdentifier(LocalIdentifier localIdentifier) {
		IINHSIdentifierType3 value = new IINHSIdentifierType3();
		value.setExtension(localIdentifier.getIdentity());
		value.setRoot(LOCAL_OID);
		return value;
	}

}
