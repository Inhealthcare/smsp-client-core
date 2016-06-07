package uk.co.inhealthcare.smsp.client.services.pds;

import javax.xml.bind.JAXBElement;

import org.hl7.v3.COMTMT000013GB01Component;
import org.hl7.v3.COMTMT000013GB01Patient;
import org.hl7.v3.COMTMT000013GB01Subject;
import org.hl7.v3.COMTMT000013GB01ValidIdentifier;
import org.hl7.v3.COMTMT000013GB01VerifyNHSNumberResponseV10;
import org.hl7.v3.IINHSIdentifierType1;

import uk.co.inhealthcare.smsp.client.services.model.NHSNumber;

public class VerifyNHSNumberResponse {

	public static class Builder {

		private COMTMT000013GB01VerifyNHSNumberResponseV10 response;

		public Builder serviceResponse(COMTMT000013GB01VerifyNHSNumberResponseV10 response) {
			this.response = response;
			return this;
		}

		public VerifyNHSNumberResponse build() {
			return new VerifyNHSNumberResponse(this);
		}

	}

	private String messageId;
	private PDSMiniServiceResponseCode code;
	private boolean isVerified = false;
	private NHSNumber nhsNumber;

	public VerifyNHSNumberResponse(Builder builder) {

		if (builder.response != null) {
			buildFromResponse(builder.response);
		}

	}

	public PDSMiniServiceResponseCode getCode() {
		return code;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public String getMessageId() {
		return messageId;
	}

	public NHSNumber getNhsNumber() {
		return nhsNumber;
	}

	private void buildFromResponse(COMTMT000013GB01VerifyNHSNumberResponseV10 response) {

		this.messageId = response.getId().getRoot();
		// chekc code?
		this.code = PDSMiniServiceResponseCode.valueOfCode(response.getValue().getCode());

		JAXBElement<COMTMT000013GB01Component> componentWrapper = response.getComponent();
		if (componentWrapper != null) {

			COMTMT000013GB01Component component = componentWrapper.getValue();

			COMTMT000013GB01ValidIdentifier identifier = component.getValidIdentifier();

			isVerified = identifier.getValue().isValue();

			COMTMT000013GB01Subject subject = identifier.getSubject();

			COMTMT000013GB01Patient patient = subject.getPatient();

			IINHSIdentifierType1 id = patient.getId();

			nhsNumber = new NHSNumber(id);

		}

	}

}
