package game;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import utils.EventSerializer;
import network.NetworkFacade;

public class EventQueue {
	
	private BlockingQueue<Event> queue = new LinkedBlockingQueue<Event>();
	private NetworkFacade networkFacade;
	
	public EventQueue(NetworkFacade networkFacade) {
		this.networkFacade = networkFacade;
		Thread eventPeeker = new Thread(new Runnable() {
			public void run() {
				while(true) {
					try {
						queue.put(EventSerializer.deserializeEvent(networkFacade.receiveMessage()));
					} catch (InterruptedException e) {
						System.err.println("Error: EventQueue interrupted");
						e.printStackTrace();
						assert true;
					}
				}
			}
		});
		eventPeeker.setDaemon(true);
		eventPeeker.start();
	}
	
	
	public Event take() {
		try {
			return queue.take();
		} catch (InterruptedException e) {
			return null;
		}
	}
	
	public void put(Event event) {
		try {
			queue.put(event);
		} catch (InterruptedException e) {
		}
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
}
