package view;
import domain.client.Keyboard;
import java.awt.Canvas;
import java.util.Observable;
import java.util.Observer;
import java.awt.Dimension;
import domain.client.GameClient;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import domain.client.BombermanSprite;
public class GameCanvas extends Canvas implements Observer {

	private GameClient game;
	private BufferedImage image;
	public int[] pixels;
	
	static int a = 0;
	
	public GameCanvas(Dimension size, GameClient game, Keyboard keyboard) {
		
		setPreferredSize(size);
		this.game = game;
		image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		game.addObserver(this);
		addKeyListener(keyboard);
	}
	
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		//clear();
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
		
		a++;
		drawRect(a, 10, 100, 100, 255);
		
		for(BombermanSprite bomberman: game.getBombermans()) {
			bomberman.draw2(pixels);
		}
		
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	public void drawRect(int xPos, int yPos, int width, int height, int color) {
		for(int y = yPos; y < yPos+height; y++) {
			if(y < 0 || y >= this.getHeight()) continue;
			for(int x = xPos; x < xPos+width; x++) {
				if(x < 0 || x >= this.getWidth()) continue;
				this.pixels[x + y * this.getWidth()] = color;
			}
		}
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		render();
	}
}
