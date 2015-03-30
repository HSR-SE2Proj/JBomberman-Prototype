package game.server;

import java.util.ArrayList;
import java.util.List;

import network.NetworkFacade;
import network.server.ServerNetwork;
import utils.EventSerializer;
import utils.Position;
import game.Event;
import game.EventQueue;
import game.EventType;
import game.Floor;
import game.GameObject;
import game.Map;
import game.SolidBlock;
import game.StandardMap;

public class GameServer {
	
	private Map map;
	private Player[] players;
	private List<GameObject> background;
	private List<GameObject> mapelements;
	private List<GameObject> bombs;
	private List<GameObject> explosions;
	private NetworkFacade networkFacade;
	private EventQueue eventQueue;
	 
	public GameServer(Player[] players, Map map, NetworkFacade networkFacade, EventQueue eventQueue) {
		this.players = players;
		this.map = map;
		this.networkFacade = networkFacade;
		this.eventQueue = eventQueue;
		
		background = new ArrayList<GameObject>();
		mapelements = new ArrayList<GameObject>();
		bombs = new ArrayList<GameObject>();
		explosions = new ArrayList<GameObject>();
		
		
		//initialize map
		for(int y = 0; y < 13; ++y) {
			for(int x = 0; x < 13; ++x) {
				
				background.add(new Floor("BackgroundTile", new Position(x*64, y*64))); //jäääääh isch halt eifacher
				
				if(map.getTile(x, y) == '#')
					mapelements.add(new SolidBlock("SolidBlock", new Position(x*64, y * 64)));
				
			}
		}
		//---------------------------------
		
		//send all
		for(GameObject o: background)
			networkFacade.sendMessage(EventSerializer.serializeEvent(new Event(EventType.ADD, new Object[]{o})));
		for(GameObject o: mapelements)
			networkFacade.sendMessage(EventSerializer.serializeEvent(new Event(EventType.ADD, new Object[]{o})));
		
		System.out.println("Game initialized: " + background.size());
	}
	
	public void tick() {
		while(!eventQueue.isEmpty()) {
			Event event = eventQueue.take();
			switch(event.getEventType()) {
			case ADD:
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
	
	public static void main(String[] args) {
		NetworkFacade networkFacade = new ServerNetwork();
		networkFacade.connect("localhost");
		EventQueue eventQueue = new EventQueue(networkFacade);
		GameServer gameServer = new GameServer(null, new StandardMap(), networkFacade, eventQueue);
	}
}
