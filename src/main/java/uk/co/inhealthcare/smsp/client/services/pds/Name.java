package uk.co.inhealthcare.smsp.client.services.pds;

import javax.xml.bind.JAXBElement;

import org.apache.commons.lang3.StringUtils;
import org.hl7.v3.CsEntityNameUse;
import org.hl7.v3.EnFamily;
import org.hl7.v3.EnGiven;
import org.hl7.v3.PNNHSPersonNameType1;
import org.hl7.v3.PNNHSPersonNameType3;
import org.hl7.v3.QUPAMT000001GB01PersonName;
import org.hl7.v3.QUPAMT000002GB01PersonName;
import org.hl7.v3.ST;

public class Name {

	public static class Builder {

		private String given;
		private String family;
		private CsEntityNameUse use;

		public Builder given(String given) {
			this.given = given;
			return this;
		}

		public Builder family(String family) {
			this.family = family;
			return this;
		}

		public Builder use(CsEntityNameUse use) {
			this.use = use;
			return this;
		}

		public Name build() {
			return new Name(this);
		}

	}

	protected final org.hl7.v3.ObjectFactory messageFactory = new org.hl7.v3.ObjectFactory();
	private String family;
	private String given;
	private CsEntityNameUse use;

	private Name(Builder builder) {
		if (StringUtils.isBlank(builder.given) || StringUtils.isBlank(builder.given))
			throw new IllegalArgumentException("Name requires a given or family part");
		family = builder.family;
		given = builder.given;
		use = builder.use;
	}

	public JAXBElement<QUPAMT000001GB01PersonName> toType1PersonName() {

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

		if (use != null) {
			theName.getUse().add(use);
		}

		name.setValue(theName);
		ST stName = new ST();
		stName.getContent().add("Person.Name");
		name.setSemanticsText(stName);
		return messageFactory.createQUPAMT000001GB01VerifyNHSNumberRequestV10GrouperPersonName(name);

	}

	public QUPAMT000002GB01PersonName toType2PersonName() {
		QUPAMT000002GB01PersonName name = new QUPAMT000002GB01PersonName();
		PNNHSPersonNameType3 theName = new PNNHSPersonNameType3();

		if (StringUtils.isNotBlank(given)) {
			EnGiven enGiven = new EnGiven();
			enGiven.getContent().add(given);
			theName.getContent().add(messageFactory.createENGiven(enGiven));
		}

		EnFamily enFamily = new EnFamily();
		enFamily.getContent().add(family);
		theName.getContent().add(messageFactory.createENFamily(enFamily));

		name.setValue(theName);
		ST stName = new ST();
		stName.getContent().add("Person.Name");
		name.setSemanticsText(stName);
		return name;
	}

}
