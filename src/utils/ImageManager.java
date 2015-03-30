package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class ImageManager {
	
	private static ImageManager im;
	private Map<String, BufferedImage> imgMap = new HashMap<>();
	
	public static ImageManager getInstance() {
		if(im == null)
			im = new ImageManager();
		return im;
	}
	
	private ImageManager() {
		try {
			imgMap.put("BackgroundTile", ImageIO.read(	new File("src/images/BackgroundTile.png")));
			imgMap.put("SolidBlock", ImageIO.read(		new File("src/images/SolidBlock.png")));
			imgMap.put("ExplodableBlock", ImageIO.read(	new File("src/images/ExplodableBlock.png")));
		} catch(IOException e) {
			System.out.println("Error: Could not load images");
			e.printStackTrace();
			assert true;
		}
	}
	
	public BufferedImage get(String imgName) {
		return imgMap.get(imgName);
	}
}
