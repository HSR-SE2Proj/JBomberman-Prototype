package application.client;

import view.ConnectionFrame;

public class ClientApp {
	
	public ClientApp() {
		
	}

	public static void main(String[] args) {
		ConnectionFrame connection = new ConnectionFrame();
		connection.setVisible(true);
	}

}
