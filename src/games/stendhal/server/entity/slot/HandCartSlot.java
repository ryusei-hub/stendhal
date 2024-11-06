package games.stendhal.server.entity.slot;

import games.stendhal.server.entity.Entity;
import games.stendhal.server.entity.mapstuff.chest.HandCart;

public class HandCartSlot extends LootableSlot{
	private final HandCart cart;

	/**
	 * Creates a ChestSlot
	 *
	 * @param owner
	 *            Chest owning this slot
	 */
	public HandCartSlot(final HandCart owner) {
		super(owner);
		this.cart = owner;
	}

	@Override
	public boolean isReachableForTakingThingsOutOfBy(final Entity entity) {
		if (!cart.isOpen()) {
			setErrorMessage("This " + ((Entity)getOwner()).getDescriptionName(true) + " is not open.");
			return false;
		}
		return super.isReachableForTakingThingsOutOfBy(entity);
	}
}
