package uk.co.inhealthcare.smsp.client.services.factories;

import org.hl7.v3.QUPAMT000001GB01PersonDateOfBirth;
import org.hl7.v3.QUPAMT000002GB01PersonDateOfBirth;
import org.hl7.v3.QUPAMT000003GB01PersonDateOfBirth;
import org.hl7.v3.QUPAMT000004GB01PersonDateOfBirth;
import org.hl7.v3.QUPAMT000005GB01PersonDateOfBirth;
import org.hl7.v3.ST;
import org.hl7.v3.TSNHSTimestampType1;
import org.hl7.v3.TSNHSTimestampType4;

import uk.co.inhealthcare.smsp.client.model.DateOfBirth;

public class PersonDateOfBirthFactory {

	private static final String SEMANTICS_TEXT = "Person.DateOfBirth";

	public static QUPAMT000001GB01PersonDateOfBirth toType1PersonDateOfBirth(DateOfBirth dateOfBirth) {

		QUPAMT000001GB01PersonDateOfBirth dob = new QUPAMT000001GB01PersonDateOfBirth();
		TSNHSTimestampType1 timestamp = new TSNHSTimestampType1();
		timestamp.setValue(dateOfBirth.getDate());
		dob.setValue(timestamp);
		dob.setSemanticsText(createSemanticsText());
		return dob;

	}

	private static ST createSemanticsText() {
		ST stdob = new ST();
		stdob.getContent().add(SEMANTICS_TEXT);
		return stdob;
	}

	public static QUPAMT000002GB01PersonDateOfBirth toType2PersonDateOfBirth(DateOfBirth dateOfBirth) {

		QUPAMT000002GB01PersonDateOfBirth dob = new QUPAMT000002GB01PersonDateOfBirth();
		TSNHSTimestampType4 timestamp = new TSNHSTimestampType4();
		timestamp.setValue(dateOfBirth.getDate());
		dob.setValue(timestamp);
		dob.setSemanticsText(createSemanticsText());
		return dob;

	}

	public static QUPAMT000003GB01PersonDateOfBirth toType3PersonDateOfBirth(DateOfBirth dateOfBirth) {

		QUPAMT000003GB01PersonDateOfBirth dob = new QUPAMT000003GB01PersonDateOfBirth();
		TSNHSTimestampType1 timestamp = new TSNHSTimestampType1();
		timestamp.setValue(dateOfBirth.getDate());
		dob.setValue(timestamp);
		dob.setSemanticsText(createSemanticsText());
		return dob;

	}

	public static QUPAMT000004GB01PersonDateOfBirth toType4PersonDateOfBirth(DateOfBirth dateOfBirth) {

		QUPAMT000004GB01PersonDateOfBirth dob = new QUPAMT000004GB01PersonDateOfBirth();
		TSNHSTimestampType1 timestamp = new TSNHSTimestampType1();
		timestamp.setValue(dateOfBirth.getDate());
		dob.setValue(timestamp);
		dob.setSemanticsText(createSemanticsText());
		return dob;

	}

	public static QUPAMT000005GB01PersonDateOfBirth toType5PersonDateOfBirth(DateOfBirth dateOfBirth) {

		QUPAMT000005GB01PersonDateOfBirth dob = new QUPAMT000005GB01PersonDateOfBirth();
		TSNHSTimestampType1 timestamp = new TSNHSTimestampType1();
		timestamp.setValue(dateOfBirth.getDate());
		dob.setValue(timestamp);
		dob.setSemanticsText(createSemanticsText());
		return dob;

	}

}
