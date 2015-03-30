package game;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Event implements Serializable {
	
	private EventType eventType;
	private Object[] properties;
	
	public Event(EventType eventType, Object[] properties) {
		this.eventType = eventType;
		this.properties = properties;
	}
	
	public EventType getEventType() {
		return eventType;
	}

	public Object[] getProperties() {
		return properties;
	}

}
