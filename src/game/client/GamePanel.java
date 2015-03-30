package game.client;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 5275453277775327909L;
	private GameClient game;
	
	public GamePanel(Dimension size, GameClient game) {
		setPreferredSize(size);
		this.game = game;
	}
	
	public void tick() {
		game.tick();
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		game.drawAll(g2d);
	}
}
