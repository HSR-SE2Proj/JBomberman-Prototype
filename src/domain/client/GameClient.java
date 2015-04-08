package domain.client;

import domain.client.BombermanSprite;
import java.util.ArrayList;
import utils.Position;
import network.NetworkFacade;

import java.util.List;
import java.util.Observable;

import utils.ActionDispatcher;
import utils.GameLoop;
import action.ActionQueue;
import action.Action;

public class GameClient extends Observable implements GameLoop {

	private ActionQueue queue;
	//Sprites
	List<BombermanSprite> bombermans = new ArrayList<>();
	
	
	public GameClient(NetworkFacade network) {
		queue = new ActionQueue();
		ActionDispatcher dispatcher = new ActionDispatcher(queue, network);
		Thread dispatcherThread = new Thread(dispatcher);
		dispatcherThread.setDaemon(true);
		dispatcherThread.start();
	}
	
	public List<BombermanSprite> getBombermans() {
		return bombermans;
	}
	
	@Override
	public void loop() {
			
		while(!queue.isEmpty()) {
			Action action = queue.take();
			switch(action.getActionType()) {
			case MOVEMENT:
				bombermans.get((int)action.getPropertie(0)).updateMovement((Position)action.getPropertie(1));
				//System.out.println("Movement: " + ((Position)action.getPropertie(1)).getX());
				break;
			case ADD:
				if(((String)action.getPropertie(0)).compareTo("bomberman") == 0) {
					bombermans.add((int)action.getPropertie(1), new BombermanSprite((int)action.getPropertie(1), (Position)action.getPropertie(2)));
				}
			}
		}
		
		
		setChanged();
		notifyObservers();
	}
}