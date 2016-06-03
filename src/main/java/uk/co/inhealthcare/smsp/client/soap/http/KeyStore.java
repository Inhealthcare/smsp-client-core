package uk.co.inhealthcare.smsp.client.soap.http;

import java.io.File;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.ssl.SSLContexts;

public class KeyStore {

	private String location;
	private String password;

	public KeyStore(String location, String password) {
		this.location = location;
		this.password = password;
	}

	public SSLConnectionSocketFactory createSSLSocketFactory() {

		try {

			// Trust own CA and all self-signed certs
			File keystoreFile = new File(location);
			char[] passwordAsCharArray = password.toCharArray();
			SSLContext sslcontext = SSLContexts.custom()
					.loadTrustMaterial(keystoreFile, passwordAsCharArray, new TrustSelfSignedStrategy())
					.loadKeyMaterial(keystoreFile, passwordAsCharArray, passwordAsCharArray).build();

			// Allow TLSv1 protocol only
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
					null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());

			return sslsf;

		} catch (Exception e) {
			throw new RuntimeException("Could not create HTTP SOAP Connection with SSL " + toString(), e);
		}
	}

}
