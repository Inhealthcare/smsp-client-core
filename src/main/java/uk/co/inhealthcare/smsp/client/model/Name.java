package uk.co.inhealthcare.smsp.client.model;

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
import org.hl7.v3.PNNHSPersonNameType2;

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
	
	public String getFamily() {
		return family;
	}
	
	public List<String> getGiven() {
		return given;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public List<CsEntityNameUse> getUse() {
		return use;
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

	

	@Override
	public String toString() {
		return "Name [family=" + family + ", given=" + given + ", use=" + use + ", prefix=" + prefix + "]";
	}
	
	

}
