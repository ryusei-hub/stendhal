package games.stendhal.server.entity.mapstuff.chest;

import static org.junit.Assert.*;

import org.junit.Test;

import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.entity.item.Corpse;

public class HandCartTest {
	
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
