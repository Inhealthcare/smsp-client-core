# Inhealthcare SMSP client core

Code that demonstrates how to connect and use an SMSP endpoint

## Usage

	// create a connection to the smsp soap endpoint
	SOAPConnection soapConnection = new HttpSoapConnection.Builder(getServiceUrl()).useSSL(createKeyStore()).logTraffic(isLogTraffic()).build();
	
	// create the client of the soap connection for sending soap messages
	SimpleSOAPSender soapSender = new SimpleSOAPSender(soapConnection);
	
	// create a gateway object that takes itk messages and sends them over soap
	ITKGateway itkGateway = new SOAPITKGateway(soapSender);
	
	// create the verify nhs number service
	VerifyNHSNumberMiniService service = new VerifyNHSNumberMiniService(itkGateway);
	
	// create the identity object
	Identity identity = createIdentity();
	
	// create the request
	VerifyNHSNumberRequest request = createRequest();
	
	// invoke the service
	VerifyNHSNumberResponse response = service.verifyNhsNumber(identity, request);
	
	// handle response
	handleResponse(response);

## Run an example

	cd <project-root>
	mvn install
	cd target/
	java -DserviceUrl=https://test1-rmf.inhealthcare.thirdparty.nhs.uk -DsslKeystoreLocation={} -DsslKeystorePassword={} -jar smsp-client-core.jar [service]

Services
* veifyNhsNumber
* veifyNhsNumber 
* veifyNhsNumber 
* veifyNhsNumber 
* veifyNhsNumber  

# Schemas

src/main/schemas

# Creating Java keystores

