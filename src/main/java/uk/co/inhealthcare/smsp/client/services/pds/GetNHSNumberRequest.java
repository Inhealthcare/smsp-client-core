package uk.co.inhealthcare.smsp.client.services.pds;

import org.hl7.v3.IINHSIdentifierType2;
import org.hl7.v3.QUPAMT000002GB01GetNHSNumberRequestV10;
import org.hl7.v3.QUPAMT000002GB01GetNHSNumberRequestV10.Code;
import org.hl7.v3.QUPAMT000002GB01GetNHSNumberRequestV10Grouper;

import uk.co.inhealthcare.smsp.client.utils.DCEUtils;

public class GetNHSNumberRequest {

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

		public Builder name(Name name) {
			this.name = name;
			return this;
		}
		
		public Builder local(LocalIdentifier local) {
			this.local = local;
			return this;
		}
		
		public Builder gender(Gender gender) {
			this.gender = gender;
			return this;
		}
		
		public Builder postCode(Postcode postcode) {
			this.postcode = postcode;
			return this;
		}

		public GetNHSNumberRequest build() {
			return new GetNHSNumberRequest(this);
		}

	}

	protected final org.hl7.v3.ObjectFactory messageFactory = new org.hl7.v3.ObjectFactory();
	private DateOfBirth dob;
	private Name name;
	private ServiceRequest serviceRequest;
	private Gender gender;
	private LocalIdentifier local;
	private Postcode postcode;

	private GetNHSNumberRequest(Builder builder) {

		if (builder.dob == null)
			throw new IllegalArgumentException("GetNHSNumberRequest requires a date of birth");
		if(  builder.gender == null ) throw new IllegalArgumentException("GetNHSNumberRequest requires a gender");
		if( builder.name == null ) throw new IllegalArgumentException("GetNHSNumberRequest requires a name");
		
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

		QUPAMT000002GB01GetNHSNumberRequestV10 number = new QUPAMT000002GB01GetNHSNumberRequestV10();
		number.setMoodCode("EVN");
		number.setClassCode("CACT");
		IINHSIdentifierType2 requestId = new IINHSIdentifierType2();
		requestId.setRoot(generatedRequestId);
		number.setId(requestId);
		Code requestCode = new Code();
		requestCode.setCode( MiniServiceMessageType.getNHSNumberRequest.getType() );
		requestCode.setCodeSystem("2.16.840.1.113883.2.1.3.2.4.17.284");
		number.setCode(requestCode);

		QUPAMT000002GB01GetNHSNumberRequestV10Grouper event = new QUPAMT000002GB01GetNHSNumberRequestV10Grouper();

		event.setPersonDateOfBirth( dob.toType2PersonDateOfBirth() );
		event.setPersonGender( gender.toType2PersonGender() );
		event.setPersonName( name.toType2PersonName() );
		if( local != null ) {
			event.setPersonLocalIdentifier( local.toType2PersonLocalIdentifier() );
		}
		if( postcode != null ) {
			event.setPersonPostcode( postcode.toPersonPostcode() );
		}
		
		number.setQueryEvent(event);

		serviceRequest = new ServiceRequest(generatedRequestId,
				MiniServiceMessageType.getNHSNumberRequest.getProfileId(),
				messageFactory.createGetNHSNumberRequestV10(number));

	}

}
