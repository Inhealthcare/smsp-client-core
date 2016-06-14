package uk.co.inhealthcare.smsp.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class SmspClientExampleRunnerOptionsTest {

	@Test
	public void shouldThrowIllegalArgumentExceptionIfRequiredSystemVariablesAreMissing() {

		try {
			new SmspClientExampleRunnerOptions(new String[] {});
			fail("Did not throw exception when required service url declared");
		} catch (Exception e) {

		}

	}

	@Test
	public void shouldThrowIllegalArgumentExceptionIfServiceUrlSystemVariableIsBlank() {

		try {
			System.setProperty("serviceUrl", " ");
			new SmspClientExampleRunnerOptions(new String[] {});
			fail("Did not throw exception when required service url declared");
		} catch (Exception e) {

		}

	}

	@Test
	public void shouldBeConfiguredFromServiceUrlSystemVariable() {

		String url = "https://some.url";
		System.setProperty("serviceUrl", url);
		SmspClientExampleRunnerOptions options = new SmspClientExampleRunnerOptions(new String[] {});
		assertEquals(url, options.getServiceUrl(), "Did not configure correct service url");

	}

}
