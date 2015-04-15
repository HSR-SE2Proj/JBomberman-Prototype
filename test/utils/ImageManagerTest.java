package utils;

import static org.junit.Assert.*;
import org.junit.*;

public class ImageManagerTest {

	private ImageManager im;
	@Before
	public void instanceofImagemanager() {
		im = ImageManager.getInstance();
	}
	
	@Test
	public void testImageManagerheight() {
		assertEquals(64,im.get("BackgroundTile").getHeight());
	}
	
	@Test
	public void testImageManagerwidth() {
		assertEquals(64,im.get("BackgroundTile").getHeight());
	}
	
	
	
}
