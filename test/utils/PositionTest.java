package utils;
import static org.junit.Assert.*;

import org.junit.*;

public class PositionTest {
	private Position pos;
	@Before
	public void init() {
		pos = new Position(3,4);
	}
	
	@Test
	public void testXCoordinate(){
		assertEquals(3,pos.x);
	}
	
	@Test
	public void testYCoordinate() {
		assertEquals(4,pos.y);
	}
	
	@Test
	public void testSetXCoordinate(){
		pos.setX(5);
		assertEquals(5,pos.x);
	}
	
	@Test
	public void testSetYCoordinate(){
		pos.setY(9);
		assertEquals(9,pos.y);
	}
	
	
}
