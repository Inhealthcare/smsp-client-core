package uk.co.inhealthcare.smsp.client;

public class Main {

	public static void main(String[] args) throws Exception {

		SmspClientOptions options = new SmspClientOptions(args);
		new SmspClient(options).run();

	}

}