package uk.co.inhealthcare.smsp.client.itk;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import itk.nhs.ns._201005.DistributionEnvelopeType;

public class DistributionEnvelopeExtractor {

	public JAXBElement<DistributionEnvelopeType> extract(SOAPMessage soapMessage) throws SOAPException {

		SOAPBody soapBody = soapMessage.getSOAPBody();

		try {

			JAXBContext context = JAXBContext.newInstance(ITKJAXB.JABXB_CONTEXTS);
			return context.createUnmarshaller().unmarshal(soapBody, DistributionEnvelopeType.class);

		} catch (JAXBException e) {
			throw new SOAPException("Could not unmarshall envelope from SOAP body", e);
		}

	}

}
