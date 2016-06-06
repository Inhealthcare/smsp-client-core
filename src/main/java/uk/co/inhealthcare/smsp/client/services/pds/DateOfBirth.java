package uk.co.inhealthcare.smsp.client.services.pds;

import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hl7.v3.QUPAMT000001GB01PersonDateOfBirth;
import org.hl7.v3.ST;
import org.hl7.v3.TSNHSTimestampType1;

public class DateOfBirth {

	private static final String SEMANTICS_TEXT = "Person.DateOfBirth";
	private String date;
	private QUPAMT000001GB01PersonDateOfBirth personDateOfBirth;

	public DateOfBirth(String date) {
		if (StringUtils.isBlank(date))
			throw new IllegalArgumentException("Cannot have blank data of birth");
		try {
			DateUtils.parseDate(date, "yyyyMMdd");
		} catch (ParseException e) {
			throw new IllegalArgumentException("Date of birth is not of correct format yyyyMMdd");
		}
		this.date = date;
		generatePersonDateOfBirth();
	}

	private void generatePersonDateOfBirth() {

		QUPAMT000001GB01PersonDateOfBirth dob = new QUPAMT000001GB01PersonDateOfBirth();
		TSNHSTimestampType1 timestamp = new TSNHSTimestampType1();
		timestamp.setValue(date);
		dob.setValue(timestamp);
		ST stdob = new ST();
		stdob.getContent().add(SEMANTICS_TEXT);
		dob.setSemanticsText(stdob);
		personDateOfBirth = dob;

	}

	public QUPAMT000001GB01PersonDateOfBirth toPersonDateOfBirth() {
		return personDateOfBirth;
	}

}
