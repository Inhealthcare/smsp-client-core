package uk.co.inhealthcare.smsp.client.services.model;

import org.hl7.v3.COMTMT000016GB01Organization;
import org.hl7.v3.IINHSIdentifierType3;
import org.hl7.v3.ON;

public class Organisation {

	private String orgId;
	private String name;

	public Organisation(COMTMT000016GB01Organization org) {

		IINHSIdentifierType3 id = org.getId();
		if (id != null) {
			orgId = id.getExtension();
		}

		ON on = org.getName();
		if (on != null) {
			name = MessageUtils.stringValueOf(on.getContent());
		}

	}

}
