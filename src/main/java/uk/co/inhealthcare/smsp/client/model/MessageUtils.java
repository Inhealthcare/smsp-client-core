package uk.co.inhealthcare.smsp.client.model;

import java.io.Serializable;
import java.util.List;

public class MessageUtils {

	public static final String stringValueOf(List<Serializable> content) {
		StringBuilder builder = new StringBuilder();
		for (Serializable serializable : content) {
			builder.append(serializable.toString());
		}
		return builder.toString();
	}
	
	
	
}
