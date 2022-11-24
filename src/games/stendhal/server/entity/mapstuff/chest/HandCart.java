package games.stendhal.server.entity.mapstuff.chest;

import java.util.Iterator;

import games.stendhal.common.Direction;
import games.stendhal.common.grammar.Grammar;
import games.stendhal.server.core.events.UseListener;
import games.stendhal.server.entity.ActiveEntity;
import games.stendhal.server.entity.PassiveEntity;
import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.entity.slot.HandCartSlot;
import marauroa.common.game.RPClass;
import marauroa.common.game.RPObject;
import marauroa.common.game.RPSlot;
import marauroa.common.game.Definition.Type;

public class HandCart extends ActiveEntity implements UseListener{
	
	
	public HandCart(boolean multiPush) {
		
	}
	
	public int getX() {
		return 1;
	}
	
	public int getY() {
		return 1;
	}
	
	public void push(Player p, Direction d) {
		
	}
	
	
	private static final String CHEST_RPCLASS_NAME = "chest";

	/**
	 * Whether the chest is open.
	 */
	private boolean open;

	/**
	 * Creates a new chest.
	 */
	public HandCart() {
		setRPClass(CHEST_RPCLASS_NAME);
		put("type", CHEST_RPCLASS_NAME);
		open = false;

		final RPSlot slot = new HandCartSlot(this);
		addSlot(slot);
	}

	/**
	 * Creates a new chest.
	 *
	 * @param object
	 *            RPObject
	 */
	public HandCart(final RPObject object) {
		super(object);
		setRPClass(CHEST_RPCLASS_NAME);
		put("type", CHEST_RPCLASS_NAME);

		if (!hasSlot("content")) {
			final RPSlot slot = new HandCartSlot(this);
			addSlot(slot);
		}

		update();
	}

	public static void generateRPClass() {
		if (!RPClass.hasRPClass(CHEST_RPCLASS_NAME)) {
			final RPClass chest = new RPClass(CHEST_RPCLASS_NAME);
			chest.isA("entity");
			chest.addAttribute("open", Type.FLAG);
			chest.addRPSlot("content", 30);
		}
	}


	//
	// Chest
	//

	@Override
    public String getDescriptionName(final boolean definite) {
	    return Grammar.article_noun(CHEST_RPCLASS_NAME, definite);
    }

	@Override
	public void update() {
		super.update();
		open = false;
		if (has("open")) {
			open = true;
		}
	}

	/**
	 * Open the chest.
	 */
	public void open() {
		this.open = true;
		put("open", "");
	}

	/**
	 * Close the chest.
	 */
	public void close() {
		this.open = false;

		if (has("open")) {
			remove("open");
		}
	}

	/**
	 * Determine if the chest is open.
	 *
	 * @return <code>true</code> if the chest is open.
	 */
	public boolean isOpen() {
		return open;
	}

	/**
	 * Adds a passive entity (like an item) to the chest.
	 *
	 * @param entity
	 *            entity to add
	 */
	public void add(final PassiveEntity entity) {
		final RPSlot content = getSlot("content");
		content.add(entity);
	}

	@Override
	public int size() {
		return getSlot("content").size();
	}

	/**
	 * Returns the content.
	 *
	 * @return iterator for the content
	 */
	public Iterator<RPObject> getContent() {
		final RPSlot content = getSlot("content");
		return content.iterator();
	}

	//
	// UseListener
	//

	@Override
	public boolean onUsed(final RPEntity user) {
		if (user.nextTo(this)) {
			if (isOpen()) {
				close();
			} else {
				open();
			}

			notifyWorldAboutChanges();
			return true;
		}
		if (user instanceof Player) {
			final Player player = (Player) user;
			player.sendPrivateText("You cannot reach the chest from there.");
		}
		return false;
	}

	//
	// Entity
	//

	@Override
	public String describe() {
		String text = "You see a chest.";

		if (hasDescription()) {
			text = getDescription();
		}

		if (isOpen()) {
			text += " It is open.";
			text += " You can right click and inspect this item to see its contents.";
		} else {
			text += " It is closed.";
		}

		return (text);
	}

}
