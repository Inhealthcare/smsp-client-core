package uk.co.inhealthcare.smsp.client;

public class Main {

	public static void main(String[] args) throws Exception {

		SmspClientExampleRunnerOptions options = new SmspClientExampleRunnerOptions(args);
		new SmspClientExampleRunner(options).run();

	}

}