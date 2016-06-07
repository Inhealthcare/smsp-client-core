package uk.co.inhealthcare.smsp.client.services.pds;

import java.util.List;

import javax.xml.bind.JAXBElement;

import org.hl7.v3.ADNHSAddressType2;
import org.hl7.v3.COMTMT000016GB01GetPatientDetailsResponseV10;
import org.hl7.v3.COMTMT000016GB01Patient;
import org.hl7.v3.COMTMT000016GB01Person;
import org.hl7.v3.COMTMT000016GB01Subject;
import org.hl7.v3.II;
import org.hl7.v3.PNNHSPersonNameType2;
import org.hl7.v3.TEL;

import uk.co.inhealthcare.smsp.client.services.model.Address;
import uk.co.inhealthcare.smsp.client.services.model.Communication;
import uk.co.inhealthcare.smsp.client.services.model.LocalIdentifier;
import uk.co.inhealthcare.smsp.client.services.model.NHSNumber;
import uk.co.inhealthcare.smsp.client.services.model.Name;
import uk.co.inhealthcare.smsp.client.services.model.PatientDetails;
import uk.co.inhealthcare.smsp.client.services.model.Person;

public class GetPatientDetailsResponse {

	public static class Builder {

		private COMTMT000016GB01GetPatientDetailsResponseV10 response;

		public Builder serviceResponse(COMTMT000016GB01GetPatientDetailsResponseV10 response) {
			this.response = response;
			return this;
		}

		public GetPatientDetailsResponse build() {
			return new GetPatientDetailsResponse(this);
		}

	}

	private String messageId;
	private PDSMiniServiceResponseCode code;
	private PatientDetails patientDetails;

	public GetPatientDetailsResponse(Builder builder) {

		if (builder.response != null) {
			buildFromResponse(builder.response);
		}

	}

	public PDSMiniServiceResponseCode getCode() {
		return code;
	}

	public String getMessageId() {
		return messageId;
	}

	public PatientDetails getPatientDetails() {
		return patientDetails;
	}

	private void buildFromResponse(COMTMT000016GB01GetPatientDetailsResponseV10 response) {

		this.messageId = response.getId().getRoot();
		// chekc code?
		this.code = PDSMiniServiceResponseCode.valueOfCode(response.getValue().getCode());

		JAXBElement<COMTMT000016GB01Subject> wrapper = response.getSubject();
		if(wrapper != null) {
			
			COMTMT000016GB01Subject subject = wrapper.getValue();
			
			COMTMT000016GB01Patient patient = subject.getPatient();
			
			PatientDetails.Builder builder = new PatientDetails.Builder();
			
			List<II> id = patient.getId();

			builder.nhsNumber( new NHSNumber(id.get(0)) );

			if (id.size() == 2) {
				builder.localIdentifier(new LocalIdentifier(  id.get(1) ) );
			}
			
			PNNHSPersonNameType2 name = patient.getName();			
			if(name!= null) {
				builder.name( new Name( name ) );
			}
			
			List<ADNHSAddressType2> addr = patient.getAddr();
			for (ADNHSAddressType2 adnhsAddressType2 : addr) {
				builder.addAddress( new Address(adnhsAddressType2) );
			}
			
			List<TEL> telecom = patient.getTelecom();
			for (TEL tel : telecom) {
				builder.addCommunication( new Communication( tel ) );
			}
			
			JAXBElement<COMTMT000016GB01Person> patientWrapper = patient.getPatientPerson();
			if(patientWrapper!=null) {
				
				COMTMT000016GB01Person person = patientWrapper.getValue();				
				builder.person( new Person(person) );
				
			}
			
			this.patientDetails = builder.build();
			
		}

	}

}
