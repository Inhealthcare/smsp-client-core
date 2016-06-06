package uk.co.inhealthcare.smsp.client.services.pds;

import org.hl7.v3.IINHSIdentifierType2;
import org.hl7.v3.QUPAMT000001GB01VerifyNHSNumberRequestV10;
import org.hl7.v3.QUPAMT000001GB01VerifyNHSNumberRequestV10.Code;
import org.hl7.v3.QUPAMT000001GB01VerifyNHSNumberRequestV10Grouper;

import uk.co.inhealthcare.smsp.client.utils.DCEUtils;

public class VerifyNHSNumberRequest {

	public static class Builder {

		private DateOfBirth dob;
		private NHSNumber nhsNumber;
		private Name name;

		public Builder dateOfBirth(DateOfBirth dob) {
			this.dob = dob;
			return this;
		}

		public Builder nhsNumber(NHSNumber nhsNumber) {
			this.nhsNumber = nhsNumber;
			return this;
		}

		public Builder name(Name name) {
			this.name = name;
			return this;
		}

		public VerifyNHSNumberRequest build() {
			return new VerifyNHSNumberRequest(this);
		}

	}

	protected final org.hl7.v3.ObjectFactory messageFactory = new org.hl7.v3.ObjectFactory();
	private DateOfBirth dob;
	private Name name;
	private NHSNumber nhsNumber;
	private ServiceRequest serviceRequest;

	private VerifyNHSNumberRequest(Builder builder) {

		if (builder.nhsNumber == null)
			throw new IllegalArgumentException("VerifyNHSNumber requires a name");
		if (builder.dob == null)
			throw new IllegalArgumentException("VerifyNHSNumber requires a date of birth");
		this.dob = builder.dob;
		this.name = builder.name;
		this.nhsNumber = builder.nhsNumber;
		generateServiceRequest();

	}

	public ServiceRequest toServiceRequest() {
		return serviceRequest;
	}

	private void generateServiceRequest() {

		String generatedRequestId = DCEUtils.createUUID();

		QUPAMT000001GB01VerifyNHSNumberRequestV10 verify = new QUPAMT000001GB01VerifyNHSNumberRequestV10();
		verify.setMoodCode("EVN");
		verify.setClassCode("CACT");
		
		IINHSIdentifierType2 requestId = new IINHSIdentifierType2();
		requestId.setRoot(generatedRequestId);
		verify.setId(requestId);
		Code requestCode = new Code();
		requestCode.setCode(MiniServiceMessageType.verifyNHSNumberRequest.getType());
		requestCode.setCodeSystem("2.16.840.1.113883.2.1.3.2.4.17.284");
		verify.setCode(requestCode);
		
		QUPAMT000001GB01VerifyNHSNumberRequestV10Grouper event = new QUPAMT000001GB01VerifyNHSNumberRequestV10Grouper();

		event.setPersonDateOfBirth(this.dob.toType1PersonDateOfBirth());
		event.setPersonNHSNumber(nhsNumber.toPersonNHSNumber());
		if (name != null) {
			event.setPersonName(name.toType1PersonName());
		}

		verify.setQueryEvent(event);

		serviceRequest = new ServiceRequest(generatedRequestId,
				MiniServiceMessageType.verifyNHSNumberRequest.getProfileId(),
				messageFactory.createVerifyNHSNumberRequestV10(verify));

	}

}
