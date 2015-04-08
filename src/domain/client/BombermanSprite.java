package domain.client;

import java.awt.image.WritableRaster;
import utils.ImageManager;
import utils.Position;

import java.awt.Graphics2D;
public class BombermanSprite {
	
	private int id;
	private Position position;
	private String imageName = "BackgroundTile";
	private int width, height;
	
	public BombermanSprite(int id, Position position) {
		this.id = id;
		this.position = position;
		height = ImageManager.getInstance().get(imageName).getHeight();
		width = ImageManager.getInstance().get(imageName).getWidth();
	}
	
	public void updateMovement(Position position) {
		this.position = position;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(ImageManager.getInstance().get(imageName), position.getX(), position.getY(), null);
	}
	
	public void draw2(int[] screen) {
		int[] pixels = new int[64*64];
		ImageManager.getInstance().get(imageName).getRGB(0, 0, 64, 64, pixels, 0, 64);
		for(int y = position.y; y < position.y+height; y++) {
			if(y < 0 || y >= 832) continue;
			for(int x = position.x; x < position.x+width; x++) {
				if(x < 0 || x >= 832) continue;
				screen[x + y * 832] = pixels[(x-position.x) + (y - position.y) * width];
			}
		}
	}
	
}
