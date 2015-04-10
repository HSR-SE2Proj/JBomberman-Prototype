package controller;

import javax.swing.JPanel;

import view.ConnectionPanel;
import view.LobbyPanel;
import view.StartFrame;

public class ClientController {
	StartFrame sf;
	ConnectionPanel cp;
	LobbyPanel lp;
	
	String server;

	public static void main(String[] args) {
		new ClientController();
	}
	
	public ClientController() {
		cp = new ConnectionPanel(this);
		sf = new StartFrame(cp);
	}
	
	public void connect(String server) {
		this.server = server;
		lp = new LobbyPanel(this, server);
		switchPanel(cp, lp);
	}
	
	public void disconnect() {
		switchPanel(lp, cp);
	}

	private void switchPanel(JPanel from, JPanel to) {
		sf.remove(from);
		sf.add(to);
		sf.repaint();
		sf.pack();
	}
}
