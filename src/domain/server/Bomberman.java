package domain.server;

import java.awt.event.KeyEvent;

import utils.ActionSerializer;
import utils.Position;
import network.NetworkFacade;
import action.Action;
import action.ActionType;

public class Bomberman {
	
	private Position position = new Position(0, 0);
	private int speed = 5;
	private boolean w, a, s, d, enter;
	private boolean isUpdated = true;
	private int id;
	private NetworkFacade network;
	
	public Bomberman(int id, NetworkFacade network) {
		this.id = id;
		this.network = network;
		
		network.sendMessage(ActionSerializer.serializeAction(new Action(ActionType.ADD, new Object[]{"bomberman", id, position})));
	}
	
	public void input(int key, boolean pressed) {
		if(key == KeyEvent.VK_W)
			w = pressed;
		if(key == KeyEvent.VK_A)
			a = pressed;
		if(key == KeyEvent.VK_S)
			s = pressed;
		if(key == KeyEvent.VK_D)
			d = pressed;
		if(key == KeyEvent.VK_ENTER)
			enter = pressed;
	}
	
	public void tick() {
		if(w) {
			position.y-=speed;
			isUpdated = false;
		}
			
		if(a) {
			position.x-=speed;
			isUpdated = false;
		}
			
		if(s) {
			position.y+=speed;
			isUpdated = false;
		}
			
		if(d) {
			position.x+=speed;
			isUpdated = false;
		}
			
	}
	
	public Position getPosition() {
		return position;
	}
	
	public void update() {
		if(!isUpdated) {
			network.sendMessage(ActionSerializer.serializeAction(new Action(ActionType.MOVEMENT, new Object[]{id, position})));
		}
		isUpdated = true;
	}
}
