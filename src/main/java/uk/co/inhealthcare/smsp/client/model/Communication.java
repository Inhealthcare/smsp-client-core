package uk.co.inhealthcare.smsp.client.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hl7.v3.CsTelecommunicationAddressUse;

public class Communication {

	private String value;
	private List<UseablePeriod> useablePeriods = new ArrayList<>();
	private List<CsTelecommunicationAddressUse> uses = new ArrayList<>();


	public Communication(List<CsTelecommunicationAddressUse> uses, String value, List<UseablePeriod> useablePeriod) {
		this.uses.addAll(uses);
		this.value= value;
		this.useablePeriods.addAll(useablePeriods);
	}
	
	public Collection<UseablePeriod> getUseablePeriods() {
		return useablePeriods;
	}
	
	public Collection<CsTelecommunicationAddressUse> getUses() {
		return uses;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "Communication [value=" + value + ", useablePeriods=" + useablePeriods + ", uses=" + uses + "]";
	}

	
}
