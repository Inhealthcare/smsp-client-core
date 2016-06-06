package uk.co.inhealthcare.smsp.client.itk;

import javax.xml.bind.JAXBElement;

import itk.nhs.ns._201005.DistributionEnvelopeType;

public class ITKResponseMessageBuilder {

	private JAXBElement<DistributionEnvelopeType> distributionEnvelope;

	public ITKResponseMessageBuilder() {

	}

	public ITKResponseMessageBuilder distributionEnvelope(JAXBElement<DistributionEnvelopeType> distributionEnvelope) {
		this.distributionEnvelope = distributionEnvelope;
		return this;
	}

	public ITKMessage build() {
		return new ITKMessageImpl(this);
	}

	private static class ITKMessageImpl extends AbstractITKMessage {

		public ITKMessageImpl(ITKResponseMessageBuilder builder) {
			setDistributionEnvelope( builder.distributionEnvelope );
		}

	}

}