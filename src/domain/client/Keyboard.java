package domain.client;

import action.Action;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import utils.ActionSerializer;
import action.ActionType;
import network.NetworkFacade;

public class Keyboard extends KeyAdapter {
	
	private NetworkFacade network;
	private boolean w, a, s, d, enter;
	private int id;
	
	public Keyboard(int id, NetworkFacade network) {
		this.network = network;
		this.id = id;
	}

    public void keyPressed(KeyEvent e) {
    	System.out.println("key");
    	switch(e.getKeyCode()) {
    	case KeyEvent.VK_W:
    		if(w == false)
    			update(e.getKeyCode(), true);
    		w = true;
    		break;
    	case KeyEvent.VK_A:
    		if(a == false)
    			update(e.getKeyCode(), true);
    		a = true;
    		break;
    	case KeyEvent.VK_S:
    		if(s == false)
    			update(e.getKeyCode(), true);
    		s = true;
    		break;
    	case KeyEvent.VK_D:
    		if(d == false)
    			update(e.getKeyCode(), true);
    		d = true;
    		break;
    	case KeyEvent.VK_ENTER:
    		if(enter == false)
    			update(e.getKeyCode(), true);
    		enter = true;
    		break;
    	default:
    		System.out.println("[*] Invalid Key");
    	}
    }
    
    public void keyReleased(KeyEvent e) {
    	switch(e.getKeyCode()) {
    	case KeyEvent.VK_W:
    		if(w == true)
    			update(e.getKeyCode(), false);
    		w = false;
    		break;
    	case KeyEvent.VK_A:
    		if(a == true)
    			update(e.getKeyCode(), false);
    		a = false;
    		break;
    	case KeyEvent.VK_S:
    		if(s == true)
    			update(e.getKeyCode(), false);
    		s = false;
    		break;
    	case KeyEvent.VK_D:
    		if(d == true)
    			update(e.getKeyCode(), false);
    		d = false;
    		break;
    	case KeyEvent.VK_ENTER:
    		if(enter == true)
    			update(e.getKeyCode(), false);
    		enter = false;
    		break;
    	default:
    		System.out.println("[*] Invalid Key");
    	}
    }
    
    private void update(int keyCode, boolean pressed) {
    	network.sendMessage(ActionSerializer.serializeAction(new Action(ActionType.INPUT, new Object[]{id, keyCode, pressed})));
    }
    
}