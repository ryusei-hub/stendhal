package games.stendhal.server.entity.mapstuff.chest;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import games.stendhal.common.Direction;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.player.Player;
import utilities.PlayerTestHelper;

public class HandCartTest {

	@Test
	public void testPush() {
		HandCart hc = new HandCart(true);
		hc.setPosition(0, 0);
		StendhalRPZone z = new StendhalRPZone("test", 10, 10);
		Player p = PlayerTestHelper.createPlayer("pusher");
		z.add(hc);
		assertThat(Integer.valueOf(hc.getX()), is(Integer.valueOf(0)));
		assertThat(Integer.valueOf(hc.getY()), is(Integer.valueOf(0)));

		hc.push(p, Direction.RIGHT);
		assertThat(Integer.valueOf(hc.getX()), is(Integer.valueOf(1)));
		assertThat(Integer.valueOf(hc.getY()), is(Integer.valueOf(0)));

		hc.push(p, Direction.LEFT);
		assertThat(Integer.valueOf(hc.getX()), is(Integer.valueOf(0)));
		assertThat(Integer.valueOf(hc.getY()), is(Integer.valueOf(0)));

		hc.push(p, Direction.DOWN);
		assertThat(Integer.valueOf(hc.getX()), is(Integer.valueOf(0)));
		assertThat(Integer.valueOf(hc.getY()), is(Integer.valueOf(1)));

		hc.push(p, Direction.UP);
		assertThat(Integer.valueOf(hc.getX()), is(Integer.valueOf(0)));
		assertThat(Integer.valueOf(hc.getY()), is(Integer.valueOf(0)));
	}

}
