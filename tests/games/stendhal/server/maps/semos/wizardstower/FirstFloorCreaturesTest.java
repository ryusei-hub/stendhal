package games.stendhal.server.maps.semos.wizardstower;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import games.stendhal.server.maps.MockStendlRPWorld;
import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.player.Player;
import games.stendhal.client.actions.DropAction;
import games.stendhal.server.entity.item.Item;
import utilities.PlayerTestHelper;

public class FirstFloorCreaturesTest {

	private StendhalRPZone tower;
	
	Player Player;
	Item candle = SingletonRepository.getEntityManager().getItem("candle");
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MockStendlRPWorld.get();
	}
	
	@Before
	public void setUp() { //adds a player into the first step of the test
		tower = new StendhalRPZone("int_semos_wizards_tower_1");
		SingletonRepository.getRPWorld().addRPZone(tower);
		Player = PlayerTestHelper.createPlayer("jeremy");
		tower.add(Player);
		
	}

	@Test
	public void testWhetherItemsCanBeDroppedInZone() { //makes sure you can't drop anything whilst in there
		Player.equip("bag", candle);
		final DropAction action = new DropAction();
		assertFalse(action.execute(new String[]{"1"}, "candle"));
	}
	
}