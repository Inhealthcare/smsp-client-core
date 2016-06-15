package uk.co.inhealthcare.smsp.client.services.factories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.hl7.v3.ADNHSAddressType2;
import org.hl7.v3.ADXP;
import org.hl7.v3.COMTMT000016GB01GPPractice;
import org.hl7.v3.COMTMT000016GB01Organization;
import org.hl7.v3.COMTMT000016GB01Person;
import org.hl7.v3.COMTMT000016GB01Person.AdministrativeGenderCode;
import org.hl7.v3.CsEntityNameUse;
import org.hl7.v3.CsPostalAddressUse;
import org.hl7.v3.CsTelecommunicationAddressUse;
import org.hl7.v3.EnFamily;
import org.hl7.v3.EnGiven;
import org.hl7.v3.EnPrefix;
import org.hl7.v3.II;
import org.hl7.v3.IINHSIdentifierType3;
import org.hl7.v3.IVLINT.High;
import org.hl7.v3.IVLINT.Low;
import org.hl7.v3.IVLTS;
import org.hl7.v3.ON;
import org.hl7.v3.PNNHSPersonNameType2;
import org.hl7.v3.QTY;
import org.hl7.v3.TEL;
import org.hl7.v3.TSNHSTimestampType1;
import org.hl7.v3.TSNHSTimestampType3;

import uk.co.inhealthcare.smsp.client.model.Address;
import uk.co.inhealthcare.smsp.client.model.Communication;
import uk.co.inhealthcare.smsp.client.model.DateOfBirth;
import uk.co.inhealthcare.smsp.client.model.GPPractice;
import uk.co.inhealthcare.smsp.client.model.Gender;
import uk.co.inhealthcare.smsp.client.model.Gender.Type;
import uk.co.inhealthcare.smsp.client.model.LocalIdentifier;
import uk.co.inhealthcare.smsp.client.model.NHSNumber;
import uk.co.inhealthcare.smsp.client.model.Name;
import uk.co.inhealthcare.smsp.client.model.Name.Builder;
import uk.co.inhealthcare.smsp.client.model.Organisation;
import uk.co.inhealthcare.smsp.client.model.Person;
import uk.co.inhealthcare.smsp.client.model.UseablePeriod;

public class PatientDetailsFactory {

	public static NHSNumber toNHSNumber(II ii) {
		return new NHSNumber(ii.getExtension());
	}

	public static LocalIdentifier toLocalIdentifier(II ii) {
		return new LocalIdentifier(ii.getExtension());
	}

	public static Name toName(PNNHSPersonNameType2 personName) {
		Builder builder = new Name.Builder();
		List<CsEntityNameUse> uses = personName.getUse();
		if (uses != null) {
			builder.uses(uses);
		}
		List<Serializable> content = personName.getContent();
		if (content != null) {
			for (Serializable serializable : content) {
				if (serializable instanceof JAXBElement<?>) {
					Object value = ((JAXBElement<?>) serializable).getValue();
					if (value instanceof EnPrefix) {
						builder.prefix(MessageUtils.stringValueOf(((EnPrefix) value).getContent()));
					}
					if (value instanceof EnFamily) {
						builder.family(MessageUtils.stringValueOf(((EnFamily) value).getContent()));
					}
					if (value instanceof EnGiven) {
						builder.given(MessageUtils.stringValueOf(((EnGiven) value).getContent()));
					}
				}
			}
		}
		return builder.build();
	}

	public static Address toAddress(ADNHSAddressType2 adnhsAddressType2) {
		List<String> addressLines = new ArrayList<>();
		List<CsPostalAddressUse> use = new ArrayList<>();
		String postalCode = null;

		List<CsPostalAddressUse> uses = adnhsAddressType2.getUse();
		if (uses != null) {
			use.addAll(uses);
		}

		List<Serializable> content = adnhsAddressType2.getContent();
		for (Serializable serializable : content) {
			if (serializable instanceof JAXBElement<?>) {

				Object value = ((JAXBElement) serializable).getValue();
				QName name = ((JAXBElement<?>) serializable).getName();

				if (name.getLocalPart().equals("postalCode")) {
					if (value instanceof ADXP) {
						postalCode = MessageUtils.stringValueOf(((ADXP) value).getContent());
					}
				}
				if (name.getLocalPart().equals("streetAddressLine")) {
					if (value instanceof ADXP) {
						addressLines.add(MessageUtils.stringValueOf(((ADXP) value).getContent()));
					}
				}

			}
		}

		return new Address(use, addressLines, postalCode);
	}

	public static Communication toCommunication(TEL tel) {

		String value = tel.getValue();
		List<CsTelecommunicationAddressUse> uses = new ArrayList<>();
		List<UseablePeriod> useablePeriods = new ArrayList<>();

		List<CsTelecommunicationAddressUse> use = tel.getUse();
		if (use != null) {
			uses.addAll(use);
		}

		List<IVLTS> ivltss = tel.getUseablePeriod();
		if (ivltss != null) {
			useablePeriods.addAll(toUseable(ivltss));

		}

		return new Communication(uses, value, useablePeriods);
	}

	private static Collection<? extends UseablePeriod> toUseable(List<IVLTS> ivltss) {
		List<UseablePeriod> periods = new ArrayList<>();
		for (IVLTS ivlts : ivltss) {
			List<JAXBElement<? extends QTY>> rests = ivlts.getRest();
			if (rests != null) {
				for (JAXBElement<? extends QTY> jaxbElement : rests) {
					QTY value = jaxbElement.getValue();
					if (value instanceof Low) {
						periods.add(new UseablePeriod(UseablePeriod.Type.Low, ((Low) value).getValue()));
					} else if (value instanceof High) {
						periods.add(new UseablePeriod(UseablePeriod.Type.High, ((High) value).getValue()));
					}
				}
			}
		}
		return periods;
	}

	public static Person toPerson(COMTMT000016GB01Person person) {

		Gender gender = null;
		DateOfBirth dateOfBirth = null;
		String deceasedOn = null;
		GPPractice gp = null;

		AdministrativeGenderCode genderCode = person.getAdministrativeGenderCode();
		if (genderCode != null) {
			gender = new Gender(Type.valueOfCode(genderCode.getCode()));
		}

		TSNHSTimestampType3 birthTime = person.getBirthTime();
		if (birthTime != null) {
			dateOfBirth = new DateOfBirth(birthTime.getValue());
		}

		TSNHSTimestampType1 deceasedTime = person.getDeceasedTime();
		if (deceasedTime != null) {
			deceasedOn = deceasedTime.getValue();
		}

		JAXBElement<COMTMT000016GB01GPPractice> gpwrapper = person.getGPPractice();
		if (gpwrapper != null) {

			COMTMT000016GB01GPPractice practice = gpwrapper.getValue();

			gp = PatientDetailsFactory.toGPPractice(practice);

		}

		return new Person(gender, dateOfBirth, deceasedOn, gp);

	}

	private static GPPractice toGPPractice(COMTMT000016GB01GPPractice practice) {

		Address address = null;
		Communication communication = null;
		Organisation organisation = null;

		ADNHSAddressType2 addr = practice.getAddr();
		if (addr != null) {
			address = toAddress(addr);
		}

		TEL telecom = practice.getTelecom();
		if (telecom != null) {
			communication = toCommunication(telecom);
		}

		JAXBElement<COMTMT000016GB01Organization> orgWrapper = practice.getLocationOrganization();
		if (orgWrapper != null) {

			COMTMT000016GB01Organization org = orgWrapper.getValue();

			organisation = toOrganisation(org);

		}

		return new GPPractice(address, communication, organisation);

	}

	private static Organisation toOrganisation(COMTMT000016GB01Organization org) {

		String orgId = null;
		String name = null;

		IINHSIdentifierType3 id = org.getId();
		if (id != null) {
			orgId = id.getExtension();
		}

		ON on = org.getName();
		if (on != null) {
			name = MessageUtils.stringValueOf(on.getContent());
		}

		return new Organisation(orgId, name);

	}

}
