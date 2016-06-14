package uk.co.inhealthcare.smsp.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import uk.co.inhealthcare.smsp.client.services.RequestContext;

public class SmspClientExampleRunnerOptionsTest {

	private SystemProperties systemProperties = new SystemProperties();
	private static final String HTTPS_SERVICE_URL = "https://service.url";
	private static final String HTTPS_CLIENT_URL = "https://client.url";
	private static final String AUDIT = "aduti";
	private static final String SERVICE_NAME = "verify";
	private static final String USER = "user";

	@Before
	public void setup() {
		systemProperties.clear();
	}

	@Test
	public void shouldRequireServiceUrl() {
		try {
			systemProperties.service().username().clientServiceUrl().auditIdentity();
			new SmspClientExampleRunnerOptions(new String[] {});
			fail("Did not throw exception when required service url not declared");
		} catch (Exception e) {

		}
	}

	@Test
	public void shouldRequireService() {
		try {
			systemProperties.serviceUrl().username().clientServiceUrl().auditIdentity();
			new SmspClientExampleRunnerOptions(new String[] {});
			fail("Did not throw exception when required service not declared");
		} catch (Exception e) {

		}
	}

	@Test
	public void shouldRequireUsername() {
		try {
			systemProperties.serviceUrl().service().clientServiceUrl().auditIdentity();
			new SmspClientExampleRunnerOptions(new String[] {});
			fail("Did not throw exception when required uesrname not declared");
		} catch (Exception e) {

		}
	}

	@Test
	public void shouldRequireClientServiceUrl() {
		try {
			systemProperties.serviceUrl().username().service().auditIdentity();
			new SmspClientExampleRunnerOptions(new String[] {});
			fail("Did not throw exception when required client service url not declared");
		} catch (Exception e) {

		}
	}

	@Test
	public void shouldRequireAuditIdentity() {
		try {
			systemProperties.serviceUrl().username().clientServiceUrl().service();
			new SmspClientExampleRunnerOptions(new String[] {});
			fail("Did not throw exception when required audit identity not declared");
		} catch (Exception e) {

		}
	}

	@Test
	public void shouldConfigureService() {
		systemProperties.required();
		SmspClientExampleRunnerOptions options = new SmspClientExampleRunnerOptions(new String[] {});
		assertEquals(SERVICE_NAME, options.getService());
	}

	@Test
	public void shouldConfigureServiceUrl() {
		systemProperties.required();
		SmspClientExampleRunnerOptions options = new SmspClientExampleRunnerOptions(new String[] {});
		assertEquals(HTTPS_SERVICE_URL, options.getServiceUrl());
	}

	@Test
	public void shouldCreateAKeyStoreIfLocationAndPasswordProvided() {
		systemProperties.required().ssl();
		SmspClientExampleRunnerOptions options = new SmspClientExampleRunnerOptions(new String[] {});
		assertNotNull(options.createKeyStore());
	}

	@Test
	public void shouldNotCreateAKeyStoreIfLocationMissing() {
		systemProperties.required().keystorePass();
		SmspClientExampleRunnerOptions options = new SmspClientExampleRunnerOptions(new String[] {});
		assertNull(options.createKeyStore());
	}

	@Test
	public void shouldNotCreateAKeyStoreIfPasswordMissing() {
		systemProperties.required().keystoreLoc();
		SmspClientExampleRunnerOptions options = new SmspClientExampleRunnerOptions(new String[] {});
		assertNull(options.createKeyStore());
	}

	@Test
	public void shouldEnableLogTrafficFlag() {
		systemProperties.required().logTraffic();
		SmspClientExampleRunnerOptions options = new SmspClientExampleRunnerOptions(new String[] {});
		assertTrue(options.isLogTraffic());
	}

	@Test
	public void shouldEnableSSLDebugFlag() {
		systemProperties.required().sslDebug();
		SmspClientExampleRunnerOptions options = new SmspClientExampleRunnerOptions(new String[] {});
		assertTrue(options.isSSLDebug());
	}

	@Test
	public void shouldConfigureARequestContext() {
		systemProperties.required();
		SmspClientExampleRunnerOptions options = new SmspClientExampleRunnerOptions(new String[] {});
		RequestContext context = options.createContext();
		assertEquals(USER, context.getUsername());
		assertEquals(HTTPS_SERVICE_URL, context.getServiceUrl());
		assertEquals(HTTPS_CLIENT_URL, context.getClientServiceUrl());
		assertEquals(AUDIT, context.getAuditIdentity());
	}

	private static class SystemProperties {

		public SystemProperties service() {
			System.setProperty(SmspClientExampleRunnerOptions.SERVICE_PROPERTY, SERVICE_NAME);
			return this;
		}

		public SystemProperties logTraffic() {
			System.setProperty(SmspClientExampleRunnerOptions.LOG_TRAFFIC_PROPERTY, Boolean.TRUE.toString());
			return this;
		}

		public SystemProperties sslDebug() {
			System.setProperty(SmspClientExampleRunnerOptions.SSL_DEBUG_PROPERTY, Boolean.TRUE.toString());
			return this;
		}

		public SystemProperties ssl() {
			return keystoreLoc().keystorePass();
		}

		public SystemProperties keystoreLoc() {
			System.setProperty(SmspClientExampleRunnerOptions.SSL_KEYSTORE_LOCATION_PROPERTY, "key");
			return this;
		}

		public SystemProperties keystorePass() {
			System.setProperty(SmspClientExampleRunnerOptions.SSL_KEYSTORE_PASSWORD_PROPERTY, "pass");
			return this;
		}

		public void clear() {
			System.clearProperty(SmspClientExampleRunnerOptions.SERVICE_PROPERTY);
			System.clearProperty(SmspClientExampleRunnerOptions.AUDIT_IDENTITY_PROPERTY);
			System.clearProperty(SmspClientExampleRunnerOptions.CLIENT_SERVICE_URL_PROPERTY);
			System.clearProperty(SmspClientExampleRunnerOptions.USERNAME_PROPERTY);
			System.clearProperty(SmspClientExampleRunnerOptions.SERVICE_URL_PROPERTY);
			System.clearProperty(SmspClientExampleRunnerOptions.SSL_KEYSTORE_LOCATION_PROPERTY);
			System.clearProperty(SmspClientExampleRunnerOptions.SSL_KEYSTORE_PASSWORD_PROPERTY);
			System.clearProperty(SmspClientExampleRunnerOptions.LOG_TRAFFIC_PROPERTY);
			System.clearProperty(SmspClientExampleRunnerOptions.SSL_DEBUG_PROPERTY);
		}

		public SystemProperties required() {
			return service().auditIdentity().clientServiceUrl().username().serviceUrl();
		}

		public SystemProperties auditIdentity() {
			System.setProperty(SmspClientExampleRunnerOptions.AUDIT_IDENTITY_PROPERTY, AUDIT);
			return this;
		}

		public SystemProperties clientServiceUrl() {
			System.setProperty(SmspClientExampleRunnerOptions.CLIENT_SERVICE_URL_PROPERTY, HTTPS_CLIENT_URL);
			return this;
		}

		public SystemProperties username() {
			System.setProperty(SmspClientExampleRunnerOptions.USERNAME_PROPERTY, USER);
			return this;
		}

		public SystemProperties serviceUrl() {
			System.setProperty(SmspClientExampleRunnerOptions.SERVICE_URL_PROPERTY, HTTPS_SERVICE_URL);
			return this;
		}

	}

}
