package uk.co.inhealthcare.smsp.client.model;

import org.apache.commons.lang3.StringUtils;

public class DateOfBirth {

	private String date;

	public DateOfBirth(String date) {
		if (StringUtils.isBlank(date)) {
			throw new IllegalArgumentException("Cannot have blank data of birth");
		}
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "DateOfBirth [date=" + date + "]";
	}

}
