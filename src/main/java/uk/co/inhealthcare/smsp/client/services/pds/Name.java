package uk.co.inhealthcare.smsp.client.services.pds;

import javax.xml.bind.JAXBElement;

import org.apache.commons.lang3.StringUtils;
import org.hl7.v3.EnFamily;
import org.hl7.v3.EnGiven;
import org.hl7.v3.PNNHSPersonNameType1;
import org.hl7.v3.QUPAMT000001GB01PersonName;
import org.hl7.v3.ST;

public class Name {

	public static class Builder {

		private String given;
		private String family;

		public Builder given(String given) {
			this.given = given;
			return this;
		}

		public Builder family(String family) {
			this.family = family;
			return this;
		}

		public Name build() {
			return new Name(this);
		}

	}

	protected final org.hl7.v3.ObjectFactory messageFactory = new org.hl7.v3.ObjectFactory();
	private String family;
	private String given;
	private JAXBElement<QUPAMT000001GB01PersonName> personName;

	private Name(Builder builder) {
		if (StringUtils.isBlank(builder.given) || StringUtils.isBlank(builder.given))
			throw new IllegalArgumentException("Name requires a given or family part");
		family = builder.family;
		given = builder.given;
		generatePersonName();
	}

	private void generatePersonName() {
		QUPAMT000001GB01PersonName name = new QUPAMT000001GB01PersonName();
		PNNHSPersonNameType1 theName = new PNNHSPersonNameType1();

		if (StringUtils.isNotBlank(given)) {
			EnGiven enGiven = new EnGiven();
			enGiven.getContent().add(given);
			theName.getContent().add(messageFactory.createENGiven(enGiven));
		}

		if (StringUtils.isNotBlank(family)) {
			EnFamily enFamily = new EnFamily();
			enFamily.getContent().add(family);
			theName.getContent().add(messageFactory.createENFamily(enFamily));
		}

		name.setValue(theName);
		ST stName = new ST();
		stName.getContent().add("Person.Name");
		name.setSemanticsText(stName);
		personName = messageFactory.createQUPAMT000001GB01VerifyNHSNumberRequestV10GrouperPersonName(name);

	}

	public JAXBElement<QUPAMT000001GB01PersonName> toPersonName() {
		return personName;
	}

}
