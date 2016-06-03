package uk.co.inhealthcare.smsp.client.itk;

public class ITKGatewayException extends Exception {

	private static final long serialVersionUID = -8317935538552869743L;

	public ITKGatewayException() {
		super();
	}

	public ITKGatewayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ITKGatewayException(String message, Throwable cause) {
		super(message, cause);
	}

	public ITKGatewayException(String message) {
		super(message);
	}

	public ITKGatewayException(Throwable cause) {
		super(cause);
	}

}
