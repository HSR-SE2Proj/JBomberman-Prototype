package view;

import domain.client.BombermanSprite;


import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.awt.Dimension;

import domain.client.GameClient;

public class GamePanel extends JPanel implements Observer {
	
	private GameClient game;
	
	public GamePanel(Dimension size, GameClient game) {
		setPreferredSize(size);
		this.game = game;
		game.addObserver(this);
		setDoubleBuffered(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		for(BombermanSprite bs: game.getBombermans()) {
			bs.draw(g2d);
			
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint(0);
		//paintImmediately(0, 0, 832, 832);
	}
}
