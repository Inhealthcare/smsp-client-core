package uk.co.inhealthcare.smsp.client.soap.http;

import java.io.IOException;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import uk.co.inhealthcare.smsp.client.soap.SOAPConnection;

public class HttpSoapConnection implements SOAPConnection {

	public static class Builder {

		private KeyStore keyStore;
		private String serviceUrl;
		private boolean logTraffic;

		public Builder(String serviceUrl) {
			this.serviceUrl = serviceUrl;
		}

		public HttpSoapConnection build() {
			return new HttpSoapConnection(this);
		}

		public Builder logTraffic(boolean logTraffic) {
			this.logTraffic = logTraffic;
			return this;
		}

		public Builder useSSL(KeyStore keyStore) {
			this.keyStore = keyStore;
			return this;
		}

	}

	private String serviceUrl;
	private KeyStore keyStore;
	private boolean logTraffic;
	private CloseableHttpClient httpClient;

	private HttpSoapConnection(Builder builder) {
		this.serviceUrl = builder.serviceUrl;
		this.keyStore = builder.keyStore;
		this.logTraffic = builder.logTraffic;

		HttpClientBuilder clientBuilder = HttpClients.custom();
		if (keyStore != null) {
			clientBuilder.setSSLSocketFactory(keyStore.createSSLSocketFactory());
		}
		if (logTraffic) {
			System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
			System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
			System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "DEBUG");
			//System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.wire", "ERROR");
		}

		httpClient = clientBuilder.addInterceptorFirst(new RemoveSoapHeadersInterceptor()).build();

	}

	/**
	 * HttpClient {@link org.apache.http.HttpRequestInterceptor} implementation
	 * that removes {@code Content-Length} and {@code Transfer-Encoding} headers
	 * from the request. Necessary, because some SAAJ and other SOAP
	 * implementations set these headers themselves, and HttpClient throws an
	 * exception if they have been set.
	 */
	public static class RemoveSoapHeadersInterceptor implements HttpRequestInterceptor {

		@Override
		public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
			if (request instanceof HttpEntityEnclosingRequest) {
				if (request.containsHeader(HTTP.TRANSFER_ENCODING)) {
					request.removeHeaders(HTTP.TRANSFER_ENCODING);
				}
				if (request.containsHeader(HTTP.CONTENT_LEN)) {
					request.removeHeaders(HTTP.CONTENT_LEN);
				}
			}
		}
	}

}
