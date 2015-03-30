package game;

public abstract class Map {

	protected char[][] map;
	
	public char getTile(int x, int y) {
		return map[y][x];
	}

}
