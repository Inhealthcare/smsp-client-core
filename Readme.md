#Inhealthcare SMSP client core

Code that demonstrates how to connect and use an SMSP endpoint

##Build

Requires maven and Java 7 to be installed and available on the system path 

	cd <project-root>/
	mvn install
	cd target/

##Command Line Usage

	java [args] -jar smsp-client-core.jar

###Args

	serviceUrl - url location of the SMSP ITK endpoint 
	sslKeystoreLocation - location of Java keystore that contains client and SMSP certificates
	sslKeystorePassword - password for the keystore 
	sslDebug - if "true" then javax.ssl.Debug will be turned on to assist with SSL debugging
	username - username provided by Inhealthcare
	clientServiceUrl - url of your client
	auditIdentity - HSCIC auditable identity
	logTraffic - if "true" then the http traffic will be logged and http compression will be disabled
	service - the name of the service you want to try. one of verifyNHSNumber, getNHSNumber, getPatientDetailsByNHSNumber, getPatientDetailsBySearch, getPatientDetails

###Example

	java
	-DserviceUrl=https://test1-rmf.inhealthcare.thirdparty.nhs.uk/smsp-itk-handler/itk
	-Dusername=********
	-DauditIdentity=urn:nhs-uk:identity:ods:*******:SYS:*******
	-DclientServiceUrl=http://myservicehost/smsp
	-DlogTraffic=true
	-DsslKeystoreLocation=<path/to/mykeystore/>keystore.jks
	-DsslKeystorePassword=*********
	-Dservice=getPatientDetailsBySearch -jar smsp-client-core.jar

##Writing your own client

Also see SmspClientExampleRunner.java

	// create a factory for soap messages
	SimpleSOAPMessageFactory factory = new SimpleSOAPMessageFactory();
	
	// create a connection to the smsp soap endpoint
	SOAPConnection soapConnection = new HttpSoapConnection.Builder(options.getServiceUrl(), factory)
				.useSSL(options.createKeyStore()).logTraffic(options.isLogTraffic()).build();
	
	// create the client of the soap connection for sending soap messages
	SimpleSOAPSender soapSender = new SimpleSOAPSender(soapConnection);
	
	// create a gateway object that takes itk messages and sends them over
	// soap
	ITKGateway itkGateway = new SOAPITKGateway(soapSender, factory);
	
	// create the request context object
	RequestContext context = options.createContext();
	
	// get an example client
	// create the verify nhs number service
	VerifyNHSNumberMiniService service = new VerifyNHSNumberMiniService(itkGateway);
	
	// create the request
	VerifyNHSNumberRequest request = createRequest();
	
	// invoke the service
	VerifyNHSNumberResponse response = service.verifyNhsNumber(context, request);
	
	// handle response
	handleResponse(response);

# Creating Java keystores

When connecting to the Inhealthcare SMSP, you should have already been provided with the necessary server certificates and created your own client certificate.

	# test your existing certificates using openssl
	openssl s_client -connect Test1-RMF.Inhealthcare.thirdparty.nhs.uk:443 -state -CAfile combined.cer -cert client.crt -key client.key
	
	# Create a JAVA keystore from a p12 formatted certificate (** Remember your password **)
	openssl pkcs12 -export -clcerts -in client.crt -inkey client.key -out client.p12
	
	# Import those certificates into a keystore file using the java keytool
	keytool -v -importkeystore -srckeystore client.p12 -srcstoretype PKCS12 -destkeystore keystore.jks -deststoretype JKS
	
# Further documentation

* [NHS England Developer site](http://developer.nhs.uk/)
* [Interoperability Toolkit (ITK)](http://developer.nhs.uk/testcentre/itk/) - Useful resources for ITK messages, screencasts in slide formats
* [Anatomy of an ITK Message](http://developer.nhs.uk/wp-content/uploads/2013/03/Anatomy-of-an-ITK-Message-over-SOAP1.pdf)
* [ITK Screencasts](https://youtube.com/channel/UC5yK75mnC8x7jDbZVakM07A?feature=relmfu)
* [HSCIC ITK](http://systems.hscic.gov.uk/interop/itk) for standards and overview
* [TRUD - Technology Reference data Update Distribution site for specifications](https://isd.hscic.gov.uk/trud3/user/guest/group/0/home). 
  * Grab an account
  * Go to the Interoperability Framework
  * Download the ITK Core Release
* [Toolkit Workbenchs](http://systems.hscic.gov.uk/sa/tools) for stubbing out services
	* To stub out spine responses use the [HSCIC SPINE Toolkit Workbench (Spine TKW)](http://systems.hscic.gov.uk/sa/tools/spinetkwinstal.jar) 