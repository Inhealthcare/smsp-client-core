package uk.co.inhealthcare.smsp.client.soap.http;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

public class HttpTrafficLogger {

	public void logRequest(HttpRequest request) {
		HttpFormatterUtils.format(request, System.out);
	}

	public void logResponse(HttpResponse response) {
		HttpFormatterUtils.format(response, System.out);
	}

}