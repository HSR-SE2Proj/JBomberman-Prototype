package domain.server;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Party {
	
	private Player[] playes = new Player[4];
	private List<Player> players = new ArrayList<>();
	
	private static int nOfPlayers = 0;

	public int addPlayer(String name) {
		Player player = new Player(name, nOfPlayers);
		players.add(nOfPlayers, player);
		return nOfPlayers++;
	}
	
	public Player getPlayer(int i) {
		return players.get(i);
	}
	
	public List<Player> getPlayers() {
		return players;
	}
}
