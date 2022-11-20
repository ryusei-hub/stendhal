package games.stendhal.server.entity.mapstuff.chest;

import games.stendhal.common.Direction;
import games.stendhal.server.entity.ActiveEntity;
import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.entity.player.Player;

public class HandCart extends ActiveEntity {
	
	
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
	
	
	public boolean onUsed(final RPEntity user) {
		return false;
	}
	
	public void open() {
		
	}
	
	public void close() {
		
	}
	
	public boolean isOpen() {
		return false;
	}

}
