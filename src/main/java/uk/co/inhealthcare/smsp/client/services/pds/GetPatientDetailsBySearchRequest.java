package uk.co.inhealthcare.smsp.client.services.pds;

import org.hl7.v3.IINHSIdentifierType2;
import org.hl7.v3.QUPAMT000004GB01GetPatientDetailsBySearchRequestV10;
import org.hl7.v3.QUPAMT000004GB01GetPatientDetailsBySearchRequestV10.Code;
import org.hl7.v3.QUPAMT000004GB01GetPatientDetailsBySearchRequestV10Grouper;

import uk.co.inhealthcare.smsp.client.model.DateOfBirth;
import uk.co.inhealthcare.smsp.client.model.Gender;
import uk.co.inhealthcare.smsp.client.model.LocalIdentifier;
import uk.co.inhealthcare.smsp.client.model.Name;
import uk.co.inhealthcare.smsp.client.model.Postcode;
import uk.co.inhealthcare.smsp.client.services.factories.PersonDateOfBirthFactory;
import uk.co.inhealthcare.smsp.client.services.factories.PersonGenderFactory;
import uk.co.inhealthcare.smsp.client.services.factories.PersonLocalIdentifierFactory;
import uk.co.inhealthcare.smsp.client.services.factories.PersonNameFactory;
import uk.co.inhealthcare.smsp.client.services.factories.PersonPostcodeFactory;
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

		event.setPersonDateOfBirth( PersonDateOfBirthFactory.toType4PersonDateOfBirth( dob ) );
		event.setPersonGender( PersonGenderFactory.toType4PersonGender(gender) );
		if(local!=null) {
			event.setPersonLocalIdentifier( PersonLocalIdentifierFactory.toType4PersonLocalIdentifier(local) );
		}
		event.setPersonName(PersonNameFactory.toType4PersonName(name));
		if(postcode!=null) {
			event.setPersonPostcode(PersonPostcodeFactory.toType4PersonPostcode(postcode));
		}		

		details.setQueryEvent(event);

		serviceRequest = new ServiceRequest(generatedRequestId,
				MiniServiceMessageType.getPatientDetailsBySearchRequest.getProfileId(),
				messageFactory.createGetPatientDetailsBySearchRequestV10(details));

	}

}
