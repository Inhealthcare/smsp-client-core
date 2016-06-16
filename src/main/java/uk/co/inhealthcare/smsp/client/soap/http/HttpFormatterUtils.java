package uk.co.inhealthcare.smsp.client.soap.http;

import java.io.IOException;
import java.io.PrintStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

public class HttpFormatterUtils {

	public static class Formatter {

		private PrintStream out;

		public Formatter(PrintStream out) {
			this.out = out;
		}

		public void writeRequest(HttpRequest request) {

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
					this.out.println(EntityUtils.toString(entity));
				} catch (ParseException | IOException e) {
					e.printStackTrace();
				}
			}
		}

		private void writeHeaders(Header[] allHeaders) {
			for (Header header : allHeaders) {
				this.out.println(header);
			}
		}

		private void writeRequestLine(HttpRequest request) {
			this.out.println(request.getRequestLine());
		}

		private void spacer() {
			this.out.println("");
		}

		private void writeTitle(String title) {
			this.out.println("");
			this.out.println(title + " ------------------------------");
			this.out.println("");
		}

		public void writeResponse(HttpResponse response) {

			writeTitle("HTTP RESPONSE");
			spacer();
			this.out.println(response.getStatusLine());
			spacer();
			writeHeaders(response.getAllHeaders());
			spacer();
			writeResponseBody(response);
		}

		private void writeResponseBody(HttpResponse response) {
			HttpEntity entity = response.getEntity();
			try {
				this.out.println(EntityUtils.toString(entity));
			} catch (ParseException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void format(HttpRequest request, PrintStream out) {
		new Formatter(out).writeRequest(request);
	}

	public static void format(HttpResponse response, PrintStream out) {
		new Formatter(out).writeResponse(response);
	}

}
