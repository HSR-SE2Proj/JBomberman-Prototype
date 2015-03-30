package game.client;

import game.Event;
import game.EventQueue;
import game.GameObject;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import network.NetworkFacade;
import network.client.ClientNetwork;

public class GameClient {
	
	private List<Map<Integer, GameObject>> gameObjects;
	private EventQueue eventQueue;
	private NetworkFacade networkFacade;
	
	public GameClient(NetworkFacade networkFacade) {
		
		networkFacade.connect("localhost");
		eventQueue = new EventQueue(networkFacade);
		
		gameObjects = new ArrayList<Map<Integer, GameObject>>(4);
		for(int i = 0; i < 4; ++i) {
			gameObjects.add(new HashMap<Integer, GameObject>());
		}
	}
	
	public void tick() {
		while(!eventQueue.isEmpty()) {
			Event event = eventQueue.take();
			switch(event.getEventType()) {
			case ADD:
				GameObject o = (GameObject) event.getProperties()[0];
				gameObjects.get(o.getLayer()).put(o.getId(), o);
				System.out.println("add");
				break;
			case INPUT:
				break;
			case MOVEMENT:
				break;
			default:
				System.err.println("Undefined EventType");
				break;
			}
		}
	}
	
	public void drawAll(Graphics2D g2d) {
		for(Map<Integer, GameObject> o: gameObjects) {
			for(GameObject g: o.values()) {
				g.draw(g2d);
			}
		}
	}
	
	public static void main(String[] args) {
		NetworkFacade networkFacade = new ClientNetwork();
		GameClient gameClient = new GameClient(networkFacade);
		GamePanel gamePanel = new GamePanel(new Dimension(832, 832), gameClient);
		GameFrame gameFrame = new GameFrame(gamePanel);
		gameFrame.setVisible(true);
		Thread timer = new Thread(gameFrame);
		timer.setDaemon(true);
		timer.start();
	}
}
