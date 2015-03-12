package network.client;

import java.io.IOException;

import network.Message;

public class ClientTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		ClientNetworkFacade cnf = new ClientNetworkFacadeImpl();
		cnf.connect("localhost");
		
		new Thread(() -> {
			while(cnf.isOpen()) {
				cnf.sendMessage(new Message("Hallo".getBytes()));
			}
			System.out.println("[!] Connection closed");
		}).start();
		
		new Thread(() -> {
			while(cnf.isOpen()) {
				System.out.println("[X] " + cnf.receiveMessage());
			}
			System.out.println("[!] Connection closed");
		}).start();
		
		Thread.sleep(10_000L);
		
		cnf.close();
		System.out.println("[*] Connection closed");
	}
}
