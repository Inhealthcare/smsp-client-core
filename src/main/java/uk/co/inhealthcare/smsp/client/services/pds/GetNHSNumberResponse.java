package uk.co.inhealthcare.smsp.client.services.pds;

import java.util.List;

import javax.xml.bind.JAXBElement;

import org.hl7.v3.COMTMT000014GB01GetNHSNumberResponseV10;
import org.hl7.v3.COMTMT000014GB01Patient;
import org.hl7.v3.COMTMT000014GB01Subject;
import org.hl7.v3.II;

public class GetNHSNumberResponse {

	public static class Builder {

		private COMTMT000014GB01GetNHSNumberResponseV10 response;

		public Builder serviceResponse(COMTMT000014GB01GetNHSNumberResponseV10 response) {
			this.response = response;
			return this;
		}

		public GetNHSNumberResponse build() {
			return new GetNHSNumberResponse(this);
		}

	}

	private String messageId;
	private PDSMiniServiceResponseCode code;
	private NHSNumber nhsNumber;
	private LocalIdentifier localIdentifier;

	public GetNHSNumberResponse(Builder builder) {

		if (builder.response != null) {
			buildFromResponse(builder.response);
		}

	}
	
	public LocalIdentifier getLocalIdentifier() {
		return localIdentifier;
	}

	public PDSMiniServiceResponseCode getCode() {
		return code;
	}

	public String getMessageId() {
		return messageId;
	}

	public NHSNumber getNhsNumber() {
		return nhsNumber;
	}

	private void buildFromResponse(COMTMT000014GB01GetNHSNumberResponseV10 response) {

		this.messageId = response.getId().getRoot();
		// chekc code?
		this.code = PDSMiniServiceResponseCode.valueOfCode(response.getValue().getCode());

		JAXBElement<COMTMT000014GB01Subject> wrapper = response.getSubject();
		if (wrapper != null) {

			COMTMT000014GB01Subject subject = wrapper.getValue();

			COMTMT000014GB01Patient patient = subject.getPatient();

			List<II> id = patient.getId();

			nhsNumber = new NHSNumber(id.get(0));

			if (id.size() == 2) {
				localIdentifier = new LocalIdentifier(  id.get(1) );
			}
		}

	}

}
