package uk.co.inhealthcare.smsp.client.utils;

import java.util.UUID;

public class DCEUtils {

	public static final String createUUID() {
		return UUID.randomUUID().toString().toUpperCase();
	}

	public static final String createManifestItmUUID() {
		return "uuid_" + createUUID();
	}

}
