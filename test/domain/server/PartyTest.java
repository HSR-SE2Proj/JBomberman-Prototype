package domain.server;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import org.junit.*;

public class PartyTest {
	private Party party;
	private Player player;
	private List<Player> players;
	@Before
	public void init() {
		party = new Party();
		player = new Player("Test",0);
	}
	
	@Test
	public void testAddPlayers(){
		assertEquals(0,party.addPlayer("Test"));
		assertEquals(1,party.addPlayer("Test2"));
	}
	
}
