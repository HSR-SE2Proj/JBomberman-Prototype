package utils;

public class Timer implements Runnable {
	
	private GameLoop loop;
	private int millis;
	
	public Timer(int millis, GameLoop loop) {
		this.millis = millis;
		this.loop = loop;
	}
	
	@Override
	public void run() {
		long lastTime = System.currentTimeMillis();
		long now;
		long delta;
		System.out.println("[*] Timer started");
		while(true) {
			now = System.currentTimeMillis();
			delta = now - lastTime;
			if(delta >= millis) {
				//System.out.println("Delta: " + (delta - millis));
				loop.loop();
				lastTime = System.currentTimeMillis();
			}
		}
	}
}
