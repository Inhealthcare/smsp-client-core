package uk.co.inhealthcare.smsp.client.itk;

import java.io.Serializable;

public interface PayloadQuery<T> {

	/**
	 * @return null if not a match
	 */
	T match(Serializable serializable);

}
