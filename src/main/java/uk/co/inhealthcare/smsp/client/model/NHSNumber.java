package uk.co.inhealthcare.smsp.client.model;

import org.apache.commons.lang3.StringUtils;
import org.hl7.v3.II;

public class NHSNumber {
	
	

	private String number;

	public NHSNumber(String number) {
		if (StringUtils.isBlank(number))
			throw new IllegalArgumentException("Cannot have blank nhsnumber");
		this.number = number;
	}

	public NHSNumber(II ii) {
		this.number = ii.getExtension();
	}
	
	public String getNumber() {
		return number;
	}


	@Override
	public String toString() {
		return "NHSNumber [number=" + number + "]";
	}
	
	

}
