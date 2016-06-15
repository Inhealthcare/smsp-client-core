
package uk.co.inhealthcare.smsp.client.itk;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.JAXBElement;

import itk.nhs.ns._201005.DistributionEnvelopeType;
import itk.nhs.ns._201005.PayloadType;

class AbstractITKMessage implements ITKMessage {
	
	private JAXBElement<DistributionEnvelopeType> distributionEnvelope;
	private ITKHeaders headers;

	@Override
	public ITKHeaders getHeaders() {
		return headers;
	}
	
	protected void setDistributionEnvelope(JAXBElement<DistributionEnvelopeType> distributionEnvelope) {
		this.distributionEnvelope = distributionEnvelope;
	}

	@Override
	public JAXBElement<DistributionEnvelopeType> getDistributionEnvelope() {
		return distributionEnvelope;
	}
	
	protected void setHeaders(ITKHeaders headers) {
		this.headers = headers;
	}


	@Override
	public <T> T findPayload(PayloadQuery<T> query) {
		List<PayloadType> payload = distributionEnvelope.getValue().getPayloads().getPayload();
		for (PayloadType payloadType : payload) {
			List<Serializable> content = payloadType.getContent();
			for (Serializable serializable : content) {
				T match = query.match(serializable);
				if (match != null) {
					return match;
				}
			}
		}
		return null;
	}

}
