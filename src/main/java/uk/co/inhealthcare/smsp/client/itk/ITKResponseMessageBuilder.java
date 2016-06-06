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

	private static class ITKMessageImpl implements ITKMessage {

		private JAXBElement<DistributionEnvelopeType> distributionEnvelope;

		public ITKMessageImpl(ITKResponseMessageBuilder builder) {
			this.distributionEnvelope = builder.distributionEnvelope;
		}

		@Override
		public JAXBElement<DistributionEnvelopeType> getDistributionEnvelope() {
			return distributionEnvelope;
		}

		@Override
		public ITKHeaders getHeaders() {
			return null;
		}

	}

}