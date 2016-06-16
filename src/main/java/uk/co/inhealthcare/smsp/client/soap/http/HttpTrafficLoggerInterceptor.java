package uk.co.inhealthcare.smsp.client.soap.http;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;

public class HttpTrafficLoggerInterceptor implements HttpRequestInterceptor, HttpResponseInterceptor {

	protected static final String ENABLE_LOGGING_ATTRIBUTE_NAME = "enableLogging";
	private HttpTrafficLogger logger = new HttpTrafficLogger();

	@Override
	public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
		Boolean enableLogging = (Boolean) context.getAttribute(ENABLE_LOGGING_ATTRIBUTE_NAME);
		if (enableLogging != null && enableLogging) {
			logger.logRequest(request);
		}
	}

	@Override
	public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
		Boolean enableLogging = (Boolean) context.getAttribute(ENABLE_LOGGING_ATTRIBUTE_NAME);
		if (enableLogging != null && enableLogging) {
			logger.logResponse(response);
		}
	}
}