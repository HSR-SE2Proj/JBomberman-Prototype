package domain.server;

public class Player {
	
	private String name;
	private int id;
	
	public Player(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
}
