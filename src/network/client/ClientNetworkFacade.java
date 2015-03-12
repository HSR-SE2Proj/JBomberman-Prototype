package network.client;

import java.io.IOException;

import network.Message;

public interface ClientNetworkFacade {
	
	public void connect(String hostname) throws IOException; //URI ???
	public void close() throws IOException;
	public boolean isOpen();
	
	public void sendMessage(Message message);
	public Message receiveMessage();

}
