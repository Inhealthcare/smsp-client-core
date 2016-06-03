package uk.co.inhealthcare.smsp.client.itk;

/**
 * @author r_tegg
 *
 *         Send out {@link ITKMessage}s to a configured endpoint
 */
public interface ITKGateway {

	ITKResponse send(ITKMessage itkMessage);

}
