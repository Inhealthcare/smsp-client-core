package uk.co.inhealthcare.smsp.client.services.pds;

import org.hl7.v3.IINHSIdentifierType2;
import org.hl7.v3.QUPAMT000005GB01GetPatientDetailsRequestV10Grouper;
import org.hl7.v3.QUPAMT000005GB01GetPatientDetailsV10;
import org.hl7.v3.QUPAMT000005GB01GetPatientDetailsV10.Code;

import uk.co.inhealthcare.smsp.client.utils.DCEUtils;

public class GetPatientDetailsRequest {

	public static class Builder {

		private DateOfBirth dob;
		private Gender gender;
		private LocalIdentifier local;
		private NHSNumber nhsNumber;
		private Name name;
		private Postcode postcode;

		public Builder setNhsNumber(NHSNumber nhsNumber) {
			this.nhsNumber = nhsNumber;
			return this;
		}

		public Builder dateOfBirth(DateOfBirth dob) {
			this.dob = dob;
			return this;
		}

		public Builder gender(Gender gender) {
			this.gender = gender;
			return this;
		}

		public Builder local(LocalIdentifier local) {
			this.local = local;
			return this;
		}

		public Builder name(Name name) {
			this.name = name;
			return this;
		}

		public Builder postcode(Postcode postcode) {
			this.postcode = postcode;
			return this;
		}

		public GetPatientDetailsRequest build() {
			return new GetPatientDetailsRequest(this);
		}

	}

	protected final org.hl7.v3.ObjectFactory messageFactory = new org.hl7.v3.ObjectFactory();
	private DateOfBirth dob;
	private Gender gender;
	private LocalIdentifier local;
	private Name name;
	private Postcode postcode;
	private NHSNumber nhsNumber;

	private ServiceRequest serviceRequest;

	private GetPatientDetailsRequest(Builder builder) {

		if (builder.dob == null)
			throw new IllegalArgumentException("GetPatientDetailsRequest requires a date of birth");

		this.dob = builder.dob;
		this.gender = builder.gender;
		this.local = builder.local;
		this.nhsNumber = builder.nhsNumber;
		this.name = builder.name;
		this.postcode = builder.postcode;

		generateServiceRequest();

	}

	public ServiceRequest toServiceRequest() {
		return serviceRequest;
	}

	private void generateServiceRequest() {

		String generatedRequestId = DCEUtils.createUUID();

		QUPAMT000005GB01GetPatientDetailsV10 details = new QUPAMT000005GB01GetPatientDetailsV10();
		details.setMoodCode("EVN");
		details.setClassCode("CACT");
		IINHSIdentifierType2 requestId = new IINHSIdentifierType2();
		requestId.setRoot(generatedRequestId);
		details.setId(requestId);
		Code requestCode = new Code();
		requestCode.setCode("getPatientDetailsRequest-v1-0");
		requestCode.setCodeSystem("2.16.840.1.113883.2.1.3.2.4.17.284");
		details.setCode(requestCode);

		QUPAMT000005GB01GetPatientDetailsRequestV10Grouper event = new QUPAMT000005GB01GetPatientDetailsRequestV10Grouper();

		event.setPersonDateOfBirth(dob.toType5PersonDateOfBirth());
		if (gender != null) {
			event.setPersonGender(gender.toType5PersonGender());
		}
		if (local != null) {
			event.setPersonLocalIdentifier(local.toType5PersonLocalIdentifier());
		}
		if (name != null) {
			event.setPersonName(name.toType5PersonName());
		}
		if (postcode != null) {
			event.setPersonPostcode(postcode.toType5PersonPostcode());
		}
		if (nhsNumber != null) {
			event.setPersonNHSNumber(nhsNumber.toType5PersonNHSNumber());
		}

		details.setQueryEvent(event);

		serviceRequest = new ServiceRequest(generatedRequestId,
				MiniServiceMessageType.getPatientDetailsRequest.getProfileId(),
				messageFactory.createGetPatientDetailsV10(details));

	}

}
