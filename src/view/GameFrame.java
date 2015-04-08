package view;

import javax.swing.JFrame;

import domain.client.GameClient;
import domain.client.Keyboard;

import java.awt.Dimension;

public class GameFrame extends JFrame {
	
	public GameFrame(GameClient game, Keyboard keyboard) {
		setTitle("JBomberman");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	//	addKeyListener(keyboard);
		
		//GamePanel panel = new GamePanel(new Dimension(832, 832), game);
		GameCanvas canvas = new GameCanvas(new Dimension(832, 832), game, keyboard);
		add(canvas);
		
		setResizable(false);
		pack();
	}
}