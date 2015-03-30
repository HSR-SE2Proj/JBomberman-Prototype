package game;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.Serializable;

import utils.IDGenerator;
import utils.ImageManager;
import utils.Position;

public abstract class GameObject implements Serializable {
	
	private static final long serialVersionUID = 3941981280507602749L;
	protected String imageName;
	protected Position position;
	protected Dimension dimension;
	protected int layer = 0;
	protected int id;
	
	public GameObject(String imageName, Position position, Dimension dimension) {
		this.imageName = imageName;
		this.position = position;
		this.dimension = dimension;
		id = IDGenerator.getID();
	}
	
	public abstract void tick();
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(ImageManager.getInstance().get(imageName), position.getX(), position.getY(), null);
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public int getLayer() {
		return layer;
	}

	public int getId() {
		return id;
	}
	
	
	
}