package uk.co.inhealthcare.smsp.client.services.pds;

import javax.xml.bind.JAXBElement;

import org.hl7.v3.IINHSIdentifierType3;
import org.hl7.v3.QUPAMT000002GB01PersonLocalIdentifier;
import org.hl7.v3.ST;

public class LocalIdentifier {

	protected final org.hl7.v3.ObjectFactory messageFactory = new org.hl7.v3.ObjectFactory();

	private String identity;

	public LocalIdentifier(String identity) {
		this.identity = identity;
	}

	public JAXBElement<QUPAMT000002GB01PersonLocalIdentifier> toPersonLocalIdentifier() {

		QUPAMT000002GB01PersonLocalIdentifier local = new QUPAMT000002GB01PersonLocalIdentifier();
		IINHSIdentifierType3 value = new IINHSIdentifierType3();
		value.setRoot(identity);
		local.setValue(value);
		ST st = new ST();
		st.getContent().add("Person.LocalIdentifier");
		local.setSemanticsText(st);
		return messageFactory.createQUPAMT000002GB01GetNHSNumberRequestV10GrouperPersonLocalIdentifier(local);
		
	}

}
