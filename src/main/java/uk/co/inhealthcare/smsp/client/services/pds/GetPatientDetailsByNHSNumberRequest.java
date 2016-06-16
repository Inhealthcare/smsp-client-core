package uk.co.inhealthcare.smsp.client.services.pds;

import org.hl7.v3.IINHSIdentifierType2;
import org.hl7.v3.QUPAMT000003GB01GetPatientDetailsByNHSNumberRequestV10;
import org.hl7.v3.QUPAMT000003GB01GetPatientDetailsByNHSNumberRequestV10.Code;
import org.hl7.v3.QUPAMT000003GB01GetPatientDetailsByNHSNumberRequestV10Grouper;

import uk.co.inhealthcare.smsp.client.model.DateOfBirth;
import uk.co.inhealthcare.smsp.client.model.LocalIdentifier;
import uk.co.inhealthcare.smsp.client.model.NHSNumber;
import uk.co.inhealthcare.smsp.client.model.Name;
import uk.co.inhealthcare.smsp.client.services.factories.PersonDateOfBirthFactory;
import uk.co.inhealthcare.smsp.client.services.factories.PersonLocalIdentifierFactory;
import uk.co.inhealthcare.smsp.client.services.factories.PersonNHSNumberFactory;
import uk.co.inhealthcare.smsp.client.services.factories.PersonNameFactory;
import uk.co.inhealthcare.smsp.client.utils.DCEUtils;

public class GetPatientDetailsByNHSNumberRequest {

	public static class Builder {

		private DateOfBirth dob;
		private LocalIdentifier local;
		private NHSNumber nhsNumber;
		private Name name;

		public Builder nhsNumber(NHSNumber nhsNumber) {
			this.nhsNumber = nhsNumber;
			return this;
		}

		public Builder dateOfBirth(DateOfBirth dob) {
			this.dob = dob;
			return this;
		}

		public Builder name(Name name) {
			this.name = name;
			return this;
		}

		public Builder local(LocalIdentifier local) {
			this.local = local;
			return this;
		}

		public GetPatientDetailsByNHSNumberRequest build() {
			return new GetPatientDetailsByNHSNumberRequest(this);
		}

	}

	protected final org.hl7.v3.ObjectFactory messageFactory = new org.hl7.v3.ObjectFactory();
	private DateOfBirth dob;
	private Name name;
	private ServiceRequest serviceRequest;
	private LocalIdentifier local;
	private NHSNumber nhsNumber;

	private GetPatientDetailsByNHSNumberRequest(Builder builder) {

		if (builder.dob == null)
			throw new IllegalArgumentException("GetPatientDetailsByNHSNumberRequest requires a date of birth");
		if (builder.nhsNumber == null)
			throw new IllegalArgumentException("GetPatientDetailsByNHSNumberRequest requires a nhs number");

		this.dob = builder.dob;
		this.local = builder.local;
		this.nhsNumber = builder.nhsNumber;
		this.name = builder.name;

		generateServiceRequest();

	}
	
	public DateOfBirth getDob() {
		return dob;
	}
	
	public Name getName() {
		return name;
	}
	
	public NHSNumber getNhsNumber() {
		return nhsNumber;
	}
	
	public LocalIdentifier getLocal() {
		return local;
	}

	public ServiceRequest toServiceRequest() {
		return serviceRequest;
	}

	private void generateServiceRequest() {

		String generatedRequestId = DCEUtils.createUUID();

		QUPAMT000003GB01GetPatientDetailsByNHSNumberRequestV10 details = new QUPAMT000003GB01GetPatientDetailsByNHSNumberRequestV10();
		details.setMoodCode("EVN");
		details.setClassCode("CACT");
		IINHSIdentifierType2 requestId = new IINHSIdentifierType2();
		requestId.setRoot(generatedRequestId);
		details.setId(requestId);
		Code requestCode = new Code();
		requestCode.setCode("getPatientDetailsByNHSNumberRequest-v1-0");
		requestCode.setCodeSystem("2.16.840.1.113883.2.1.3.2.4.17.284");
		details.setCode(requestCode);

		QUPAMT000003GB01GetPatientDetailsByNHSNumberRequestV10Grouper event = new QUPAMT000003GB01GetPatientDetailsByNHSNumberRequestV10Grouper();

		event.setPersonDateOfBirth(PersonDateOfBirthFactory.toType3PersonDateOfBirth( dob ) );
		if (local != null) {
			event.setPersonLocalIdentifier(PersonLocalIdentifierFactory.toType3PersonLocalIdentifier(local));
		}
		event.setPersonNHSNumber(PersonNHSNumberFactory.toType3PersonNHSNumber(nhsNumber));
		if (name != null) {
			event.setPersonName(PersonNameFactory.toType3PersonName(name));
		}

		details.setQueryEvent(event);

		serviceRequest = new ServiceRequest(generatedRequestId,
				MiniServiceMessageType.getPatientDetailsByNHSNumberRequest.getProfileId(),
				messageFactory.createGetPatientDetailsByNHSNumberRequestV10(details));

	}

}
