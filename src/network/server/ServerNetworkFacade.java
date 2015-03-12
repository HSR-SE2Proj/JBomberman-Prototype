package network.server;

import java.io.IOException;

import network.Message;

public interface ServerNetworkFacade {
	
	public void connect(String hostname) throws IOException;
	public void close() throws IOException;
	public boolean isOpen();
	
	public void sendMessage(Message message);
	public Message receiveMessage();
	
}