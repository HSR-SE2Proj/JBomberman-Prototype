package action;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ActionQueue {
	
	private BlockingQueue<Action> queue = new LinkedBlockingQueue<Action>();
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	public Action take() {
		try {
			return queue.take();
		} catch (InterruptedException e) {
			return null;
		}
	}
	
	public void put(Action action) {
		try {
			queue.put(action);
		} catch (InterruptedException e) {
		}
	}
}
