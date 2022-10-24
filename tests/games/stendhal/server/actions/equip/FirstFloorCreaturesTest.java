package games.stendhal.server.actions.equip;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import games.stendhal.server.maps.MockStendlRPWorld;
import marauroa.common.game.RPAction;
import games.stendhal.common.EquipActionConsts;
import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.player.Player;
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
		Player.equip("bag", candle);
		tower.add(Player);
		
	}

	@Test
	public void testWhetherItemsCanBeDroppedInZone() { //makes sure you can't drop anything whilst in there
		RPAction drop = new RPAction(); //taken from DropAction.java
		
		//write code for dropping candle (taken from DropAction.java)
		drop.put(EquipActionConsts.TYPE, "drop");
		drop.put(EquipActionConsts.GROUND_X, Player.getX());
		drop.put(EquipActionConsts.GROUND_Y, Player.getY());
		drop.put(EquipActionConsts.QUANTITY, 1);
		drop.put(EquipActionConsts.BASE_ITEM, candle.getID().getObjectID());
		
		final DropAction action = new DropAction();
		action.onAction(Player, drop);
		assertTrue(Player.isEquippedItemInSlot("bag", "candle"));
	}
	
}