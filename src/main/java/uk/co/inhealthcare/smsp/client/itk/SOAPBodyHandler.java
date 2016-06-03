package uk.co.inhealthcare.smsp.client.itk;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.dom.DOMResult;

import org.w3c.dom.Document;

import itk.nhs.ns._201005.DistributionEnvelopeType;
import uk.co.inhealthcare.smsp.client.soap.SOAPMessageProcessor;

public class SOAPBodyHandler implements SOAPMessageProcessor {

	private JAXBElement<DistributionEnvelopeType> distributionEnvelope;

	public SOAPBodyHandler(JAXBElement<DistributionEnvelopeType> distributionEnvelope) {
		this.distributionEnvelope = distributionEnvelope;
	}

	@Override
	public SOAPMessage process(SOAPMessage message) throws SOAPException {

		try {
			DOMResult res = new DOMResult();
			JAXBContext context = JAXBContext.newInstance(itk.nhs.ns._201005.ObjectFactory.class,
					org.hl7.v3.ObjectFactory.class, xhtml.npfit.presentationtext.ObjectFactory.class);
			context.createMarshaller().marshal(distributionEnvelope, res);
			message.getSOAPBody().addDocument((Document) res.getNode());
			return message;
		} catch (JAXBException e) {
			throw new SOAPException("Could not create SOAP body", e);
		}

	}

}
