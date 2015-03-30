package game.client;

import javax.swing.JFrame;

public class GameFrame extends JFrame implements Runnable {
	
	private GamePanel gamePanel;
	
	public GameFrame(GamePanel gamePanel) {
		setTitle("JBomberMan v0.1");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.gamePanel = gamePanel;
		add(gamePanel);
		
		setResizable(false);
		pack();
	}

	@Override
	public void run() {
		int millis = 1000/25;
		long lastTime = System.currentTimeMillis();
		long now;
		long delta;
		long lastDelta = 0;
		while(true) {
			now = System.currentTimeMillis();
			delta = now - lastTime;
			if(delta >= millis) {
				//System.out.println("Delta: " + (delta - millis));
				gamePanel.tick();
				setTitle("JBomberman Prototype | Delta: " + (delta > lastDelta ? delta : 0));
				lastDelta = delta;
				lastTime = System.currentTimeMillis();
			}
		}
		
	}
}
