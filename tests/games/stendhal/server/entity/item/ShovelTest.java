package games.stendhal.server.entity.item;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import utilities.PlayerTestHelper;

public class ShovelTest {
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        MockStendlRPWorld.get();
    }

    @Test
    public void ShovelRemoveTest() {
        final StendhalRPZone zone = new StendhalRPZone("zone");
        Shovel s = new Shovel("shovel", "tool", "shovel", new HashMap<String, String>());
        // add soil to the world at 0,0
        s.addSoil(zone, 0, 0);
        // Soil should be able to be removed when there's soil already there.
        assertTrue(s.removeSoil(zone, 0, 0));
        // shouldn't be able to remove it a second time, because there should'nt be any
        // soil
        assertFalse(s.removeSoil(zone, 0, 0));
        // removing a soil at a position that never had any soil should also fail
        assertFalse(s.removeSoil(zone, 1, 1));
    }

    @Test
    public void ShovelFertileTest() {
        final StendhalRPZone zone = new StendhalRPZone("zone");
        Shovel s = new Shovel("shovel", "tool", "shovel", new HashMap<String, String>());

        //Add initial patch of soil
        assertTrue(s.addSoil(zone, 0, 0));

        //Add soil to same patch, should return false since there is 
        //already soil there
        assertFalse(s.addSoil(zone, 0, 0));
    }

    @Test
    public void ShovelUsedByPlayerTest() {
        final StendhalRPZone zone = new StendhalRPZone("ados");

        final Player player = PlayerTestHelper.createPlayer("bob");
        zone.add(player);

        Shovel s = new Shovel("shovel", "tool", "shovel", new HashMap<String, String>());

        //Add soil using shovel
        s.onUsedInArea(player, zone, 0, 0); 
        //Remove soil 
        assertTrue(s.removeSoil(zone, 0, 0));
        //Check to make sure you cant remove soil again 
        assertFalse(s.removeSoil(zone, 0, 0));

        //Add soil
        s.addSoil(zone, 0, 0);
        //Remove soil using shovel
        s.onUsedInArea(player, zone, 0, 0);
        //Check you cant remove soil again
        assertFalse(s.removeSoil(zone, 0, 0));
    }
}
