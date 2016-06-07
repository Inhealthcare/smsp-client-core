package uk.co.inhealthcare.smsp.client.services.model;

import java.util.ArrayList;
import java.util.List;

import org.hl7.v3.CsTelecommunicationAddressUse;
import org.hl7.v3.IVLTS;
import org.hl7.v3.TEL;

public class Communication {

	private String value;
	private List<UseablePeriod> useablePeriods = new ArrayList<>();
	private List<CsTelecommunicationAddressUse> uses = new ArrayList<>();

	public Communication(TEL tel) {

		value = tel.getValue();

		List<CsTelecommunicationAddressUse> use = tel.getUse();
		if (use != null) {
			this.uses.addAll(use);
		}

		List<IVLTS> useablePeriod = tel.getUseablePeriod();
		if (useablePeriod != null) {
			for (IVLTS ivlts : useablePeriod) {
				useablePeriods.add(new UseablePeriod(ivlts));
			}
		}

	}

}
