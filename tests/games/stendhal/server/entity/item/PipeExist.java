package games.stendhal.server.entity.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import java.util.Arrays;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import games.stendhal.server.core.engine.StendhalRPZone;


import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.semos.townhall.LeaderNPC;
import utilities.PlayerTestHelper;
import utilities.QuestHelper;
import utilities.RPClass.ItemTestHelper;

public class PipeExist {

	private Player player = null;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();

		ItemTestHelper.generateRPClasses();

		final StendhalRPZone zone = new StendhalRPZone("admin_test");
		new LeaderNPC().configureZone(zone, null);

	}

	@Before
	public void setUp() {
		player = PlayerTestHelper.createPlayer("player");
	}
	
	@Test
	public void TestPipeEquipable() {

		
		final Item weapon = new Item("orcarina", "pipe", "subclass", null);
		weapon.setEquipableSlots(Arrays.asList("lhand"));
		/**weapon.put("atk", 0);
		weapon.put("rate", 100);**/
		player.equipToInventoryOnly(weapon);
		assertTrue(player.isEquipped("orcarina"));

		player.drop(weapon);

		
	}
	
	@Test
	public void TestPipeProperty() {
		final Item weapon = new Item("orcarina", "pipe", "subclass", null);
		assertEquals(0,weapon.getAttack());
		assertEquals(100,weapon.getAttackRate());
		
		}
	}


