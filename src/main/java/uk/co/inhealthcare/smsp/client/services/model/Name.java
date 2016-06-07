package uk.co.inhealthcare.smsp.client.services.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.apache.commons.lang3.StringUtils;
import org.hl7.v3.CsEntityNameUse;
import org.hl7.v3.EnFamily;
import org.hl7.v3.EnGiven;
import org.hl7.v3.EnPrefix;
import org.hl7.v3.PNNHSPersonNameType1;
import org.hl7.v3.PNNHSPersonNameType2;
import org.hl7.v3.PNNHSPersonNameType3;
import org.hl7.v3.QUPAMT000001GB01PersonName;
import org.hl7.v3.QUPAMT000002GB01PersonName;
import org.hl7.v3.QUPAMT000003GB01PersonName;
import org.hl7.v3.QUPAMT000004GB01PersonName;
import org.hl7.v3.QUPAMT000005GB01PersonName;
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
	private List<String> given = new ArrayList<>();
	private List<CsEntityNameUse> use = new ArrayList<>();
	private String prefix;

	private Name(Builder builder) {
		if (StringUtils.isBlank(builder.given) || StringUtils.isBlank(builder.given))
			throw new IllegalArgumentException("Name requires a given or family part");
		family = builder.family;
		if (builder.given != null) {
			given = Arrays.asList(builder.given);
		}
		if (builder.use != null) {
			use = Arrays.asList(builder.use);
		}
	}

	public Name(PNNHSPersonNameType2 name) {
		List<CsEntityNameUse> uses = name.getUse();
		if (uses != null) {
			this.use.addAll(uses);
		}
		List<Serializable> content = name.getContent();
		if (content != null) {
			for (Serializable serializable : content) {
				if (serializable instanceof JAXBElement<?>) {
					Object value = ((JAXBElement<?>) serializable).getValue();
					if (value instanceof EnPrefix) {
						prefix = MessageUtils.stringValueOf(((EnPrefix) value).getContent());
					}
					if (value instanceof EnFamily) {
						family = MessageUtils.stringValueOf(((EnFamily) value).getContent());
					}
					if (value instanceof EnGiven) {
						given.add(MessageUtils.stringValueOf(((EnGiven) value).getContent()));
					}
				}
			}
		}
	}

	public JAXBElement<QUPAMT000001GB01PersonName> toType1PersonName() {

		QUPAMT000001GB01PersonName name = new QUPAMT000001GB01PersonName();
		PNNHSPersonNameType1 theName = new PNNHSPersonNameType1();

		for (String g : given) {
			EnGiven enGiven = new EnGiven();
			enGiven.getContent().add(g);
			theName.getContent().add(messageFactory.createENGiven(enGiven));
		}

		if (StringUtils.isNotBlank(family)) {
			EnFamily enFamily = new EnFamily();
			enFamily.getContent().add(family);
			theName.getContent().add(messageFactory.createENFamily(enFamily));
		}

		for (CsEntityNameUse csEntityNameUse : use) {
			theName.getUse().add(csEntityNameUse);
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

		for (String g : given) {
			EnGiven enGiven = new EnGiven();
			enGiven.getContent().add(g);
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

	public JAXBElement<QUPAMT000003GB01PersonName> toType3PersonName() {

		QUPAMT000003GB01PersonName name = new QUPAMT000003GB01PersonName();
		PNNHSPersonNameType1 theName = new PNNHSPersonNameType1();

		for (String g : given) {
			EnGiven enGiven = new EnGiven();
			enGiven.getContent().add(g);
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

		return messageFactory.createQUPAMT000003GB01GetPatientDetailsByNHSNumberRequestV10GrouperPersonName(name);

	}

	public QUPAMT000004GB01PersonName toType4PersonName() {

		QUPAMT000004GB01PersonName name = new QUPAMT000004GB01PersonName();
		PNNHSPersonNameType3 theName = new PNNHSPersonNameType3();

		for (String g : given) {
			EnGiven enGiven = new EnGiven();
			enGiven.getContent().add(g);
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

	public JAXBElement<QUPAMT000005GB01PersonName> toType5PersonName() {

		QUPAMT000005GB01PersonName name = new QUPAMT000005GB01PersonName();
		PNNHSPersonNameType1 theName = new PNNHSPersonNameType1();

		for (String g : given) {
			EnGiven enGiven = new EnGiven();
			enGiven.getContent().add(g);
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

		return messageFactory.createQUPAMT000005GB01GetPatientDetailsRequestV10GrouperPersonName(name);

	}

	@Override
	public String toString() {
		return "Name [family=" + family + ", given=" + given + ", use=" + use + ", prefix=" + prefix + "]";
	}
	
	

}
