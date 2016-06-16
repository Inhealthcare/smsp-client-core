package uk.co.inhealthcare.smsp.client.soap.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import uk.co.inhealthcare.smsp.client.soap.SOAPClient;
import uk.co.inhealthcare.smsp.client.soap.SOAPConnection;
import uk.co.inhealthcare.smsp.client.soap.SOAPMessageFactory;

public class HttpSoapConnection implements SOAPConnection {

	public static class Builder {

		private KeyStore keyStore;
		private String serviceUrl;
		private boolean logTraffic = false;
		private SOAPMessageFactory factory;
		public boolean disableCompression = false;
		private List<HttpRequestInterceptor> requestInterceptors = new ArrayList<>();
		private List<HttpResponseInterceptor> responseInterceptors = new ArrayList<>();

		public Builder(String serviceUrl, SOAPMessageFactory factory) {
			this.serviceUrl = serviceUrl;
			this.factory = factory;
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

		public Builder requestInterceptor(HttpRequestInterceptor request) {
			requestInterceptors.add(request);
			return this;
		}

		public Builder requestInterceptor(HttpResponseInterceptor response) {
			responseInterceptors.add(response);
			return this;
		}

	}

	private String serviceUrl;
	private KeyStore keyStore;
	private boolean logTraffic;
	private CloseableHttpClient httpClient;
	private SOAPMessageFactory factory;

	private HttpSoapConnection(Builder builder) {
		this.serviceUrl = builder.serviceUrl;
		this.keyStore = builder.keyStore;
		this.logTraffic = builder.logTraffic;
		this.factory = builder.factory;

		HttpClientBuilder clientBuilder = HttpClients.custom();
		if (logTraffic) {
			clientBuilder.disableContentCompression();
		}
		if (keyStore != null) {
			clientBuilder.setSSLSocketFactory(keyStore.createSSLSocketFactory());
		}

		HttpTrafficLoggerInterceptor logger = new HttpTrafficLoggerInterceptor();
		BufferEntityInterceptor bufferEntityInterceptor = new BufferEntityInterceptor();

		clientBuilder.addInterceptorFirst((HttpRequestInterceptor) bufferEntityInterceptor)
				.addInterceptorFirst((HttpResponseInterceptor) bufferEntityInterceptor);

		for (HttpRequestInterceptor inter : builder.requestInterceptors) {
			clientBuilder.addInterceptorFirst(inter);
		}

		for (HttpResponseInterceptor inter : builder.responseInterceptors) {
			clientBuilder.addInterceptorFirst(inter);
		}

		clientBuilder.addInterceptorFirst(new RemoveSoapHeadersInterceptor())
				.addInterceptorLast((HttpRequestInterceptor) logger)
				.addInterceptorFirst((HttpResponseInterceptor) logger);

		httpClient = clientBuilder.build();

	}

	@Override
	public SOAPClient getClient() {
		return new SOAPClient() {

			@Override
			public SOAPMessage send(SOAPMessage soapRequest) throws SOAPException {

				try {

					HttpPost httpPost = new HttpPost(serviceUrl);
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					soapRequest.writeTo(out);
					httpPost.setEntity(new ByteArrayEntity(out.toByteArray(), ContentType.TEXT_XML));

					HttpContext context = HttpClientContext.create();

					// log http traffic
					context.setAttribute(HttpTrafficLoggerInterceptor.ENABLE_LOGGING_ATTRIBUTE_NAME,
							Boolean.valueOf(logTraffic));

					CloseableHttpResponse response = httpClient.execute(httpPost, context);
					try {

						HttpEntity entity = response.getEntity();

						SOAPMessage soapResponse = factory.createFrom(entity.getContent());

						EntityUtils.consume(entity);

						return soapResponse;

					} finally {
						response.close();
					}

				} catch (IOException e) {
					throw new SOAPException("Could not send soap message over HTTP", e);
				}

			}
		};
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
