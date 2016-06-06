package uk.co.inhealthcare.smsp.client.itk;

import javax.xml.bind.JAXBElement;

import itk.nhs.ns._201005.DistributionEnvelopeType;

public interface ITKMessage {
	
	ITKHeaders getHeaders();

	JAXBElement<DistributionEnvelopeType> getDistributionEnvelope();

	<T> T findPayload(PayloadQuery<T> query);
	
}
