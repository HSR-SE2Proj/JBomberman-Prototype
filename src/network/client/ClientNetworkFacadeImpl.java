package network.client;

import java.io.IOException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;

import network.Message;

public class ClientNetworkFacadeImpl implements ClientNetworkFacade {
	
	private Connection connection;
	
	
	private ClientSender sender;
	private ClientReceiver receiver;

	@Override
	public void connect(String hostname) throws IOException {
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(hostname);
		connection = factory.newConnection();
		
		//sender
		sender = new ClientSender(connection);
		//receiver
		receiver = new ClientReceiver(connection);
		
	}
	
	@Override
	public void close() throws IOException {
		sender.close();
		receiver.close();
		connection.close();
	}

	@Override
	public void sendMessage(Message message) {
		try {
			sender.send(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Message receiveMessage() {
		try {
			return receiver.receive();
		} catch (ShutdownSignalException | ConsumerCancelledException
				| InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean isOpen() {
		return connection.isOpen();
	}
}
