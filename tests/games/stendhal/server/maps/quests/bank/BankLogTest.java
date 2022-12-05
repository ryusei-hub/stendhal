/* $Id$ */
/***************************************************************************
 *                   (C) Copyright 2003-2010 - Stendhal                    *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.server.maps.quests.bank;

import static org.junit.Assert.assertEquals;
import static utilities.SpeakerNPCTestHelper.getReply;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.ados.bank.BankNPC;
import games.stendhal.server.maps.quests.AbstractQuest;
import utilities.PlayerTestHelper;
import utilities.QuestHelper;

public class BankLogTest {
	private static final String QUEST_SLOT = "Ados Bank";

	private Player player = null;
	private SpeakerNPC npc = null;
	private Engine en = null;
	private AbstractQuest quest;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();
	}

	@Before
	public void setUp() {
		final StendhalRPZone zone = new StendhalRPZone("admin_test");

    new BankNPC().configureZone(zone, null);

		quest = new AdosBankChest();
		quest.addToWorld();

		player = PlayerTestHelper.createPlayer("bob");
	}

	@Test
	public void testGetWood() {
		npc = SingletonRepository.getNPCList().get("Rachel");
		en = npc.getEngine();

		en.step(player, "hi");
		assertEquals("Welcome to Ados Bank!", getReply(npc));

		PlayerTestHelper.equipWithItemToSlot(player, "wood", "bank_ados");
		
	    // Request travel log update
	    en.step(player, "balance");
	    assertEquals("Check your Travel Log to see the contents of your chests!", getReply(npc));
	    assertEquals("start",player.getQuest("Ados Bank"));

	    // Check travel log displays item
	    assertEquals("1 wood", quest.getHistory(player).get(0));
	    
	    // Check travel log updates with new items
		PlayerTestHelper.equipWithItemToSlot(player, "apple", "bank_ados");
	    assertEquals("1 apple", quest.getHistory(player).get(1));
		
	}
}
