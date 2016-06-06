package uk.co.inhealthcare.smsp.client.itk;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.JAXBElement;

import itk.nhs.ns._201005.AuditIdentityType;
import itk.nhs.ns._201005.DistributionEnvelopeType;
import itk.nhs.ns._201005.DistributionHeaderType;
import itk.nhs.ns._201005.IdentityType;
import itk.nhs.ns._201005.ManifestItemType;
import itk.nhs.ns._201005.ManifestType;
import itk.nhs.ns._201005.PayloadType;
import itk.nhs.ns._201005.PayloadsType;
import uk.co.inhealthcare.smsp.client.services.pds.ServiceRequest;

public class ITKRequestMessageBuilder {


	private ITKHeaders headers;
	private List<ServiceRequest> serviceRequests = new ArrayList<>();

	public ITKRequestMessageBuilder(ITKHeaders headers) {
		this.headers = headers;
	}

	public ITKMessage build() {
		return new ITKMessageImpl(this);
	}

	public ITKRequestMessageBuilder addPayload(ServiceRequest serviceRequest) {
		serviceRequests.add(serviceRequest);
		return this;
	}

	private static class ITKMessageImpl implements ITKMessage {

		private static final itk.nhs.ns._201005.ObjectFactory itkFactory = new itk.nhs.ns._201005.ObjectFactory();
		private ITKHeaders headers;
		private JAXBElement<DistributionEnvelopeType> distributionEnvelope;
		private List<ServiceRequest> serviceRequests;

		public ITKMessageImpl(ITKRequestMessageBuilder builder) {
			this.headers = builder.headers;
			this.serviceRequests = builder.serviceRequests;
			generateEnvelope();
		}

		private void generateEnvelope() {

			DistributionEnvelopeType envelope = new DistributionEnvelopeType();

			PayloadsType payloads = new PayloadsType();
			ManifestType manifest = new ManifestType();

			for (ServiceRequest serviceRequest : serviceRequests) {

				PayloadType payload = new PayloadType();
				payload.setId( createUUID() );
				payload.getContent().add(serviceRequest.getContent());
				payloads.getPayload().add(payload);

				ManifestItemType item = new ManifestItemType();
				item.setProfileid(serviceRequest.getProfileId());
				item.setMimetype("text/xml");
				item.setBase64(Boolean.FALSE);
				item.setCompressed(Boolean.FALSE);
				item.setId(payload);
				manifest.getManifestitem().add(item);

			}

			payloads.setCount(BigInteger.valueOf(serviceRequests.size()));
			envelope.setPayloads(payloads);

			DistributionHeaderType header = new DistributionHeaderType();

			manifest.setCount(BigInteger.valueOf(serviceRequests.size()));
			header.setManifest(manifest);

			header.setService(this.headers.getService());
			header.setTrackingid(this.headers.getTrackingId());

			AuditIdentityType audit = new AuditIdentityType();
			IdentityType identity = new IdentityType();
			identity.setUri(this.headers.getAuditIdentity());
			audit.getId().add(identity);

			header.setAuditIdentity(audit);

			envelope.setHeader(header);
			this.distributionEnvelope = itkFactory.createDistributionEnvelope(envelope);

		}

		private String createUUID() {
			return "uuid_" +  UUID.randomUUID().toString();
		}

		@Override
		public JAXBElement<DistributionEnvelopeType> getDistributionEnvelope() {
			return this.distributionEnvelope;
		}
		
		public ITKHeaders getHeaders() {
			return headers;
		}

	}

}