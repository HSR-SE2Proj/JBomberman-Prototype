package game;

import java.awt.Dimension;

import utils.Position;

public class Floor extends GameObject {

	public Floor(String imageName, Position position, Dimension dimension) {
		super(imageName, position, dimension);
	}
	
	public Floor(String imageName, Position position) {
		this(imageName, position, new Dimension(64, 64));
	}

	@Override
	public void tick() {
	}
}