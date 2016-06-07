package uk.co.inhealthcare.smsp.client.services.pds;

import javax.xml.bind.JAXBElement;

import org.hl7.v3.II;
import org.hl7.v3.IINHSIdentifierType3;
import org.hl7.v3.QUPAMT000002GB01PersonLocalIdentifier;
import org.hl7.v3.QUPAMT000003GB01PersonLocalIdentifier;
import org.hl7.v3.QUPAMT000004GB01PersonLocalIdentifier;
import org.hl7.v3.QUPAMT000005GB01PersonLocalIdentifier;
import org.hl7.v3.ST;

public class LocalIdentifier {

	protected final org.hl7.v3.ObjectFactory messageFactory = new org.hl7.v3.ObjectFactory();

	private String identity;

	public LocalIdentifier(String identity) {
		this.identity = identity;
	}

	public LocalIdentifier(II ii) {
		this.identity = ii.getExtension();
	}

	public String getIdentity() {
		return identity;
	}

	public JAXBElement<QUPAMT000002GB01PersonLocalIdentifier> toPersonLocalIdentifier() {

		QUPAMT000002GB01PersonLocalIdentifier local = new QUPAMT000002GB01PersonLocalIdentifier();
		IINHSIdentifierType3 value = new IINHSIdentifierType3();
		value.setExtension(identity);
		value.setRoot("1.3.5.7.9.24.68.1");
		local.setValue(value);
		ST st = new ST();
		st.getContent().add("Person.LocalIdentifier");
		local.setSemanticsText(st);
		return messageFactory.createQUPAMT000002GB01GetNHSNumberRequestV10GrouperPersonLocalIdentifier(local);

	}

	public JAXBElement<QUPAMT000003GB01PersonLocalIdentifier> toType3PersonLocalIdentifier() {

		QUPAMT000003GB01PersonLocalIdentifier local = new QUPAMT000003GB01PersonLocalIdentifier();
		IINHSIdentifierType3 value = new IINHSIdentifierType3();
		value.setExtension(identity);
		value.setRoot("1.3.5.7.9.24.68.1");
		local.setValue(value);
		ST st = new ST();
		st.getContent().add("Person.LocalIdentifier");
		local.setSemanticsText(st);
		return messageFactory
				.createQUPAMT000003GB01GetPatientDetailsByNHSNumberRequestV10GrouperPersonLocalIdentifier(local);

	}

	public JAXBElement<QUPAMT000004GB01PersonLocalIdentifier> toType4PersonLocalIdentifier() {

		QUPAMT000004GB01PersonLocalIdentifier local = new QUPAMT000004GB01PersonLocalIdentifier();
		IINHSIdentifierType3 value = new IINHSIdentifierType3();
		value.setExtension(identity);
		value.setRoot("1.3.5.7.9.24.68.1");
		local.setValue(value);
		ST st = new ST();
		st.getContent().add("Person.LocalIdentifier");
		local.setSemanticsText(st);
		return messageFactory
				.createQUPAMT000004GB01GetPatientDetailsBySearchRequestV10GrouperPersonLocalIdentifier(local);

	}

	public JAXBElement<QUPAMT000005GB01PersonLocalIdentifier> toType5PersonLocalIdentifier() {

		QUPAMT000005GB01PersonLocalIdentifier local = new QUPAMT000005GB01PersonLocalIdentifier();
		IINHSIdentifierType3 value = new IINHSIdentifierType3();
		value.setExtension(identity);
		value.setRoot("1.3.5.7.9.24.68.1");
		local.setValue(value);
		ST st = new ST();
		st.getContent().add("Person.LocalIdentifier");
		local.setSemanticsText(st);
		return messageFactory.createQUPAMT000005GB01GetPatientDetailsRequestV10GrouperPersonLocalIdentifier(local);

	}

}
