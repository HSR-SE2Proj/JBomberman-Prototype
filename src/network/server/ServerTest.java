package network.server;

import java.io.IOException;

import network.Message;

public class ServerTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		final ServerNetworkFacade snf = new ServerNetworkFacadeImpl();
		snf.connect("localhost");
		
		new Thread(() -> {
			while(snf.isOpen()) {
				snf.sendMessage(new Message("Hallo".getBytes()));
			}
			System.out.println("[!] Connection closed");
		}).start();
		
		new Thread(() -> {
			while(snf.isOpen()) {
				System.out.println("[X] " + snf.receiveMessage());
			}
			System.out.println("[!] Connection closed");
		}).start();
		
		Thread.sleep(10_000L);
		
		snf.close();
		System.out.println("[*] Connection closed");

	}

}
