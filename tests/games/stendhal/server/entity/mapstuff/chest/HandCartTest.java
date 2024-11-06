package games.stendhal.server.entity.mapstuff.chest;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.entity.item.Corpse;
import games.stendhal.common.Direction;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.player.Player;
import utilities.PlayerTestHelper;

public class HandCartTest {
	
	@Test
	public void testPush() {
		HandCart hc = new HandCart();
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
	
	/**
	 * Tests for open.
	 */
	@Test
	public final void testOpen() {
		final HandCart hc = new HandCart();
		assertFalse(hc.isOpen());
		hc.open();

		assertTrue(hc.isOpen());
		hc.close();
		assertFalse(hc.isOpen());
	}

	/**
	 * Tests for onUsed.
	 */
	@Test
	public final void testOnUsed() {
		final HandCart hc = new HandCart();
		assertFalse(hc.isOpen());
		hc.onUsed(new RPEntity() {

			@Override
			protected void dropItemsOn(final Corpse corpse) {
			}

			@Override
			public void logic() {

			}
		});

		assertTrue(hc.isOpen());
		hc.onUsed(new RPEntity() {

			@Override
			protected void dropItemsOn(final Corpse corpse) {
			}

			@Override
			public void logic() {

			}
		});
		assertFalse(hc.isOpen());
	}
}
