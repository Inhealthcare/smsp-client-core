package uk.co.inhealthcare.smsp.client.soap.http;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.util.EntityUtils;

public class MessageLogger {

	public void logRequest(HttpRequest request) {
		
		writeTitle("HTTP REQUEST");
		spacer();
		writeRequestLine(request);
		spacer();
		writeHeaders(request.getAllHeaders());
		spacer();
		writeRequestBody(request);

	}

	private void writeRequestBody(HttpRequest request) {
		if (request instanceof HttpEntityEnclosingRequest) {
			HttpEntity entity = ((HttpEntityEnclosingRequest) request).getEntity();
			try {
				if (entity != null) {
					entity = new BufferedHttpEntity(entity);
				}
				((HttpEntityEnclosingRequest) request).setEntity(entity);
				System.out.println(EntityUtils.toString(entity));
			} catch (ParseException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void writeHeaders(Header[] allHeaders) {
		for (Header header : allHeaders) {
			System.out.println(header);
		}
	}

	private void writeRequestLine(HttpRequest request) {
		System.out.println(request.getRequestLine());
	}

	private void spacer() {
		System.out.println("");
	}

	private void writeTitle(String title) {
		System.out.println("");
		System.out.println(title + " ------------------------------");
		System.out.println("");
	}

	public void logResponse(HttpResponse response) {
		
		writeTitle("HTTP RESPONSE");
		spacer();
		System.out.println(response.getStatusLine());
		spacer();
		writeHeaders(response.getAllHeaders());
		spacer();
		writeResponseBody(response);
	}

	private void writeResponseBody(HttpResponse response) {
		HttpEntity entity = response.getEntity();
		try {
			if (entity != null) {
				entity = new BufferedHttpEntity(entity);
			}
			response.setEntity(entity);
			System.out.println(EntityUtils.toString(entity));
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}