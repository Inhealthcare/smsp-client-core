package uk.co.inhealthcare.smsp.client.soap.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.ParseException;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.protocol.HttpContext;

public class BufferEntityInterceptor implements HttpRequestInterceptor, HttpResponseInterceptor {
	
	@Override
	public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
		if (request instanceof HttpEntityEnclosingRequest) {
			HttpEntity entity = ((HttpEntityEnclosingRequest) request).getEntity();
			try {
				if (entity != null) {
					entity = new BufferedHttpEntity(entity);
				}
				((HttpEntityEnclosingRequest) request).setEntity(entity);
			} catch (ParseException | IOException e) {
				throw new HttpException("Could not buffer request", e);
			}
		}
	}

	@Override
	public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {

		HttpEntity entity = response.getEntity();
		try {
			if (entity != null) {
				entity = new BufferedHttpEntity(entity);
			}
			response.setEntity(entity);

		} catch (ParseException | IOException e) {
			throw new HttpException("Could not buffer response", e);
		}

	}

}
