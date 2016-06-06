package uk.co.inhealthcare.smsp.client.soap.http;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;

public class MessageLoggerInterceptor implements HttpRequestInterceptor, HttpResponseInterceptor {

	protected static final String LOGGER_ATTRIBUTE_NAME = "messageLogger";
	protected static final String ENABLE_LOGGING_ATTRIBUTE_NAME = "enableLogging";
	

	@Override
	public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
		Boolean enableLogging = (Boolean) context.getAttribute(ENABLE_LOGGING_ATTRIBUTE_NAME);
		if (enableLogging != null && enableLogging) {
			MessageLogger logger = (MessageLogger) context.getAttribute(LOGGER_ATTRIBUTE_NAME);
			logger.logRequest(request);
		}
	}

	@Override
	public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
		Boolean enableLogging = (Boolean) context.getAttribute(ENABLE_LOGGING_ATTRIBUTE_NAME);
		if (enableLogging != null && enableLogging) {
			MessageLogger logger = (MessageLogger) context.getAttribute(LOGGER_ATTRIBUTE_NAME);
			logger.logResponse(response);
		}
	}
}