package uk.co.inhealthcare.smsp.client.services.pds;

public class MiniServiceException extends Exception {

	private static final long serialVersionUID = 778929844202049030L;

	public MiniServiceException() {
		super();
	}

	public MiniServiceException(String message) {
		super(message);
	}

	public MiniServiceException(Throwable cause) {
		super(cause);
	}

	public MiniServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public MiniServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
