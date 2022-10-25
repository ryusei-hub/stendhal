package games.stendhal.server.maps.ados.city;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.entity.Outfit;
import games.stendhal.server.entity.item.Item;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.entity.player.Player;
import utilities.QuestHelper;
import utilities.ZonePlayerAndNPCTestImpl;
import utilities.RPClass.ItemTestHelper;

public class MakeupArtistNPCTest extends ZonePlayerAndNPCTestImpl {

	private static final String ZONE_NAME = "testzone";

	private Player player;
	private SpeakerNPC fidoreaNpc;
	private Engine fidoreaEngine;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();

		setupZone(ZONE_NAME, new MakeupArtistNPC());
	}

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();

		player = createPlayer("bob");
		fidoreaNpc = SingletonRepository.getNPCList().get("Fidorea");
		fidoreaEngine = fidoreaNpc.getEngine();
	}

	public MakeupArtistNPCTest() {
		super(ZONE_NAME, "Fidorea");
	}

	@Test
	public void testBuyMask() {
        final Item item = ItemTestHelper.createItem("money", 50000);
		player.getSlot("bag").add(item);

        fidoreaEngine.step(player, "hi");
		fidoreaEngine.step(player, "buy 1");
        fidoreaEngine.step(player, "yes");
        Outfit currentOutfit = player.getOutfit();
        assertTrue(new Outfit("mask=994,hair=-1,hat=-1").isPartOf(currentOutfit));

		fidoreaEngine.step(player, "hi");
		fidoreaEngine.step(player, "buy 2");
        fidoreaEngine.step(player, "yes");
        currentOutfit = player.getOutfit();
        assertTrue(new Outfit("mask=995,hair=-1,hat=-1").isPartOf(currentOutfit));

        fidoreaEngine.step(player, "hi");
		fidoreaEngine.step(player, "buy 3");
        fidoreaEngine.step(player, "yes");
        currentOutfit = player.getOutfit();
        assertTrue(new Outfit("mask=996,hair=-1,hat=-1").isPartOf(currentOutfit));

		fidoreaEngine.step(player, "hi");
		fidoreaEngine.step(player, "buy 4");
        fidoreaEngine.step(player, "yes");
        currentOutfit = player.getOutfit();
        assertTrue(new Outfit("mask=997,hair=-1,hat=-1").isPartOf(currentOutfit));

        fidoreaEngine.step(player, "hi");
		fidoreaEngine.step(player, "buy 5");
        fidoreaEngine.step(player, "yes");
        currentOutfit = player.getOutfit();
        assertTrue(new Outfit("mask=998,hair=-1,hat=-1").isPartOf(currentOutfit));

        fidoreaEngine.step(player, "hi");
		fidoreaEngine.step(player, "buy 6");
        fidoreaEngine.step(player, "yes");
        currentOutfit = player.getOutfit();
        assertTrue(new Outfit("mask=999,hair=-1,hat=-1").isPartOf(currentOutfit));

	}
}