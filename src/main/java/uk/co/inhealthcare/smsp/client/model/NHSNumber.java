package uk.co.inhealthcare.smsp.client.model;

import org.apache.commons.lang3.StringUtils;

public class NHSNumber {

	private String number;

	public NHSNumber(String number) {
		if (StringUtils.isBlank(number))
			throw new IllegalArgumentException("Cannot have blank nhsnumber");
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return "NHSNumber [number=" + number + "]";
	}

}
