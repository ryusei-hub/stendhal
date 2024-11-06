package games.stendhal.server.entity.mapstuff.chest;

//import java.util.Arrays;
import java.util.Iterator;

import games.stendhal.common.Direction;
//import java.util.List;
//
//import games.stendhal.common.Direction;
//import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
//import games.stendhal.server.core.events.MovementListener;
//import games.stendhal.server.core.events.TurnListener;
import games.stendhal.server.core.events.UseListener;
//import games.stendhal.server.core.events.ZoneEnterExitListener;
//import games.stendhal.server.entity.ActiveEntity;
import games.stendhal.server.entity.PassiveEntity;
import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.entity.mapstuff.block.Block;
import games.stendhal.server.entity.player.Player;
//import games.stendhal.server.entity.mapstuff.block.BlockTarget;
//import games.stendhal.server.entity.player.Player;
import marauroa.common.game.RPObject;

public class HandCart extends Block implements UseListener{
	
	public Chest chest;
	
	/**
	 * Creates a new cart.
	 */
	public HandCart() {
		super(true);
		chest = new Chest();
	}

	/**
	 * Creates a new cart.
	 *
	 * @param object
	 *            RPObject
	 */
	public HandCart(final RPObject object) {
		super(true);
		chest = new Chest(object);
	}


	//
	// Cart
	//

	@Override
	public void update() {
		chest.update();
	}

	/**
	 * Open the cart.
	 */
	public void open() {
		chest.open();
	}

	/**
	 * Close the cart.
	 */
	public void close() {
		chest.close();
	}

	/**
	 * Determine if the cart is open.
	 *
	 * @return <code>true</code> if the chest is open.
	 */
	public boolean isOpen() {
		return chest.isOpen();
	}

	/**
	 * Adds a passive entity (like an item) to the cart.
	 *
	 * @param entity
	 *            entity to add
	 */
	public void add(final PassiveEntity entity) {
		chest.add(entity);
	}

	@Override
	public int size() {
		return chest.size();
	}

	/**
	 * Returns the content.
	 *
	 * @return iterator for the content
	 */
	public Iterator<RPObject> getContent() {
		return chest.getContent();
	}

	//
	// UseListener
	//

	@Override
	public boolean onUsed(final RPEntity user) {
		return chest.onUsed(user);
	}

	//
	// Entity
	//

	@Override
	public String describe() {
		return chest.describe();
	}
	
	@Override
	public void onAdded(final StendhalRPZone zone) {
		super.onAdded(zone);
		hide();
		zone.add(chest);
	}
	
	@Override 
	protected void onMoved(final int oldX, final int oldY, final int newX, final int newY) {
		chest.setPosition(newX, newY);
	}
	
	@Override
	public void push(Player p, Direction d) {
		super.push(p, d);
		chest.setPosition(getX(), getY());
		chest.notifyWorldAboutChanges();
	}
	
	//setPosition is final, so we have this instead
	public void setPos(int x, int y) {
		super.setPosition(x,  y);
		chest.setPosition(x,  y);
	}
	

}
