package game;

import java.awt.Dimension;

import utils.Position;

public class SolidBlock extends GameObject {

	public SolidBlock(String imageName, Position position, Dimension dimension) {
		super(imageName, position, dimension);
	}
	
	public SolidBlock(String imageName, Position position) {
		this(imageName, position, new Dimension(64, 64));
	}

	@Override
	public void tick() {
	}

}
