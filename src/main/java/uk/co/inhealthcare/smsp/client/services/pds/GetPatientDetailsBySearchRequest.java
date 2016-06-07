package uk.co.inhealthcare.smsp.client.services.pds;

import org.hl7.v3.IINHSIdentifierType2;
import org.hl7.v3.QUPAMT000004GB01GetPatientDetailsBySearchRequestV10;
import org.hl7.v3.QUPAMT000004GB01GetPatientDetailsBySearchRequestV10.Code;
import org.hl7.v3.QUPAMT000004GB01GetPatientDetailsBySearchRequestV10Grouper;

import uk.co.inhealthcare.smsp.client.utils.DCEUtils;

public class GetPatientDetailsBySearchRequest {

	public static class Builder {

		private DateOfBirth dob;
		private Gender gender;
		private LocalIdentifier local;
		private Name name;
		private Postcode postcode;

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

		public GetPatientDetailsBySearchRequest build() {
			return new GetPatientDetailsBySearchRequest(this);
		}

	}

	protected final org.hl7.v3.ObjectFactory messageFactory = new org.hl7.v3.ObjectFactory();
	private DateOfBirth dob;
	private Gender gender;
	private LocalIdentifier local;
	private Name name;
	private Postcode postcode;
	
	private ServiceRequest serviceRequest;

	private GetPatientDetailsBySearchRequest(Builder builder) {

		if (builder.dob == null)
			throw new IllegalArgumentException("GetPatientDetailsBySearchRequest requires a date of birth");
		if (builder.gender == null)
			throw new IllegalArgumentException("GetPatientDetailsBySearchRequest requires a gender");
		if (builder.name == null)
			throw new IllegalArgumentException("GetPatientDetailsBySearchRequest requires a name");

		this.dob = builder.dob;
		this.gender = builder.gender;
		this.local = builder.local;
		this.name = builder.name;
		this.postcode = builder.postcode;

		generateServiceRequest();

	}

	public ServiceRequest toServiceRequest() {
		return serviceRequest;
	}

	private void generateServiceRequest() {

		String generatedRequestId = DCEUtils.createUUID();

		QUPAMT000004GB01GetPatientDetailsBySearchRequestV10 details = new QUPAMT000004GB01GetPatientDetailsBySearchRequestV10();
		details.setMoodCode("EVN");
		details.setClassCode("CACT");
		IINHSIdentifierType2 requestId = new IINHSIdentifierType2();
		requestId.setRoot(generatedRequestId);
		details.setId(requestId);
		Code requestCode = new Code();
		requestCode.setCode("getPatientDetailsBySearchRequest-v1-0");
		requestCode.setCodeSystem("2.16.840.1.113883.2.1.3.2.4.17.284");
		details.setCode(requestCode);

		QUPAMT000004GB01GetPatientDetailsBySearchRequestV10Grouper event = new QUPAMT000004GB01GetPatientDetailsBySearchRequestV10Grouper();

		event.setPersonDateOfBirth( dob.toType4PersonDateOfBirth() );
		event.setPersonGender( gender.toType4PersonGender() );
		if(local!=null) {
			event.setPersonLocalIdentifier( local.toType4PersonLocalIdentifier() );
		}
		event.setPersonName(name.toType4PersonName());
		if(postcode!=null) {
			event.setPersonPostcode(postcode.toType4PersonPostcode());
		}		

		details.setQueryEvent(event);

		serviceRequest = new ServiceRequest(generatedRequestId,
				MiniServiceMessageType.getPatientDetailsBySearchRequest.getProfileId(),
				messageFactory.createGetPatientDetailsBySearchRequestV10(details));

	}

}
