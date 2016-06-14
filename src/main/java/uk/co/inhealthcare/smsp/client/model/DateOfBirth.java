package uk.co.inhealthcare.smsp.client.model;

import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

public class DateOfBirth {

	private String date;

	public DateOfBirth(String date) {
		if (StringUtils.isBlank(date))
			throw new IllegalArgumentException("Cannot have blank data of birth");
		try {
			DateUtils.parseDate(date, "yyyyMMdd");
		} catch (ParseException e) {
			throw new IllegalArgumentException("Date of birth is not of correct format yyyyMMdd");
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
