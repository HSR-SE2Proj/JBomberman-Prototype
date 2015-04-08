package domain.server;

import java.util.ArrayList;
import java.util.List;

import utils.ActionDispatcher;
import utils.ActionSerializer;
import utils.GameLoop;
import network.NetworkFacade;
import action.ActionQueue;
import action.Action;
import action.ActionType;

public class GameServer implements GameLoop {
	
	private Party party;
	private NetworkFacade network;
	private ActionQueue queue;

	//GameObjects
	private List<Bomberman> bombermans = new ArrayList<>();
	
	public GameServer(Party party, NetworkFacade network) {
		this.party = party;
		this.network = network;
		
		network.sendMessage(ActionSerializer.serializeAction(new Action(ActionType.StartGame, null)));
		//network.sendMessage(ActionSerializer.serializeAction(new Action(ActionType.StartGame, null)));
		
		for(Player player: party.getPlayers()) {
			bombermans.add(player.getID(), new Bomberman(player.getID(), network));
		}
		
		queue = new ActionQueue();
		ActionDispatcher dispatcher = new ActionDispatcher(queue, network);
		Thread dispatcherThread = new Thread(dispatcher);
		dispatcherThread.setDaemon(true);
		dispatcherThread.start();
		
		
	}
	
	@Override
	public void loop() {
		
		//handle Actions
		while(!queue.isEmpty()) {
			Action action = queue.take();
			
			switch(action.getActionType()) {
			case INPUT:
				bombermans.get((int)action.getPropertie(0)).input((int)action.getPropertie(1), (boolean)action.getPropertie(2));
				break;
			}
		}
		
		//update
		for(Bomberman bomberman: bombermans) {
			bomberman.tick();
			bomberman.update();
		}
		
		
		
		
		
	}
	
	
	
	
	
}