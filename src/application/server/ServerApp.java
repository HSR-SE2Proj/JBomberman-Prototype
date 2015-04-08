package application.server;

import utils.ActionSerializer;
import network.server.ServerNetwork;
import network.NetworkFacade;
import domain.server.Party;
import action.Action;
import action.ActionType;
import domain.server.Player;
import domain.server.GameServer;
import utils.Timer;

public class ServerApp {
	
	private NetworkFacade network = new ServerNetwork();
	private Party party;
	
	public ServerApp() {
		network.connect("localhost");
		if(network.isOpen())
			System.out.println("[*] Connected to RabbitMQ Broker");
		
		System.out.println("[*] Waiting for Party");
		party = waitForParty();
		
		System.out.println("[*] Starting Game");
		startGame();
	}
	
	private Party waitForParty() {
		Party party = new Party();
		
		for(int i = 0; i < 1; i++) {
			Action action = ActionSerializer.deserializeAction(network.receiveMessage());
			if(action.getActionType() == ActionType.HELLO) {
				String name = (String) action.getPropertie(0);
				int id = party.addPlayer(name);
				//network.sendMessage(ActionSerializer.serializeAction(new Action(ActionType.HELLO, new Object[]{new Integer(id)})));
				System.out.println("[*] Added " + name + " to Party");
			}
		}
		
		
		return party;
	}
	
	private void startGame() {
		GameServer game = new GameServer(party, network);
		Timer timer = new Timer(1000/25, game);
		Thread timerThread = new Thread(timer);
		timerThread.start();
	}
	

	public static void main(String[] args) {
		new ServerApp();

	}

}
