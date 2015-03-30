package network.client;

import java.io.IOException;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;
import network.NetworkFacade;

public class ClientNetwork implements NetworkFacade {

	private Connection connection;
	private ClientSender sender;
	private ClientReceiver receiver;

	@Override
	public void connect(String hostname) {
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(hostname);
		
		try {
			connection = factory.newConnection();
			sender = new ClientSender(connection);
			receiver = new ClientReceiver(connection);
		} catch (IOException e) {
			System.err.println("Error: Could not connect");
			e.printStackTrace();
			assert true;
		}
	}
	
	@Override
	public void close() {
		try {
			sender.close();
			receiver.close();
			connection.close();
		} catch (IOException e) {
			System.err.println("Error: Could not close down connection");
			e.printStackTrace();
			assert true;
		}
	}

	@Override
	public void sendMessage(byte[] message) {
		try {
			sender.send(message);
		} catch (IOException e) {
			System.err.println("Error: Could not send message");
			e.printStackTrace();
			assert true;
		}
	}

	@Override
	public byte[] receiveMessage() {
		try {
			return receiver.receive();
		} catch (ShutdownSignalException | ConsumerCancelledException
				| InterruptedException e) {
			System.err.println("Error: Could not receive message");
			e.printStackTrace();
			assert true;
			return null;
		}
	}

	@Override
	public boolean isOpen() {
		return connection.isOpen();
	}

}
