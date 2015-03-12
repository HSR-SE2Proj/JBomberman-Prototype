package network;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Message implements Serializable {
	
	public byte[] body;
	
	public Message(byte[] body) {
		this.body = body;
	}

}
