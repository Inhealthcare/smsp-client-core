package uk.co.inhealthcare.smsp.client.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hl7.v3.CsEntityNameUse;

public class Name {

	public static class Builder {

		private List<String> given = new ArrayList<>();
		private String family;
		private List<CsEntityNameUse> uses = new ArrayList<>();
		private String prefix;

		public Builder given(String given) {
			this.given.add(given);
			return this;
		}

		public Builder family(String family) {
			this.family = family;
			return this;
		}

		public Builder use(CsEntityNameUse use) {
			this.uses.add(use);
			return this;
		}

		public Name build() {
			return new Name(this);
		}

		public Builder uses(List<CsEntityNameUse> uses) {
			this.uses.addAll(uses);
			return this;
		}

		public Builder prefix(String prefix) {
			this.prefix = prefix;
			return this;
		}

	}

	private String family;
	private List<String> given = new ArrayList<>();
	private List<CsEntityNameUse> use = new ArrayList<>();
	private String prefix;

	private Name(Builder builder) {
		if (builder.given.isEmpty() && StringUtils.isBlank(builder.family))
			throw new IllegalArgumentException("Name requires a given or family part");
		this.family = builder.family;
		this.given.addAll(builder.given);
		this.use.addAll(builder.uses);
		this.prefix = builder.prefix;
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

	@Override
	public String toString() {
		return "Name [family=" + family + ", given=" + given + ", use=" + use + ", prefix=" + prefix + "]";
	}

}
