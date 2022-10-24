package games.stendhal.server.actions.equip;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

import marauroa.common.game.RPAction;
import marauroa.common.game.RPObject;
import games.stendhal.common.EquipActionConsts;
import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.item.Item;
import games.stendhal.server.entity.player.Player;
import utilities.PlayerTestHelper;
import utilities.ZoneAndPlayerTestImpl;

public class FirstFloorCreaturesTest extends ZoneAndPlayerTestImpl{

	private Item candle;
	private static final String towerName = "int_semos_wizards_tower_1";
	
	public FirstFloorCreaturesTest() {
	    super(towerName);
    }
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		SingletonRepository.getRPWorld();

		setupZone(towerName);
	}

	@Test
	public void testWhetherItemsCanBeDroppedInZone() { //makes sure you can't drop anything whilst in there
		final StendhalRPZone tower = new StendhalRPZone("int_semos_wizards_tower_1",15,16); // taken from DropActionTest.java
		final Player player = PlayerTestHelper.createPlayer("bob");
		tower.add(player);

		Item item = SingletonRepository.getEntityManager().getItem("candle");
		player.equipToInventoryOnly(item);
		assertEquals(0, tower.getItemsOnGround().size()); //taken from testDropDice() in DropActionTest
		candle = player.getFirstEquipped("candle");
		
		RPObject parent = candle.getContainer();
		RPAction action = new RPAction();
		action.put("type", "drop");
		action.put("baseitem", candle.getID().getObjectID());
		action.put(EquipActionConsts.BASE_OBJECT, parent.getID().getObjectID());
		action.put(EquipActionConsts.BASE_SLOT, candle.getContainerSlot().getName());
		action.put("x", player.getX());
		action.put("y", player.getY() + 1);

		new DropAction().onAction(player, action);

		assertEquals(0, tower.getItemsOnGround().size());

	}
	
}