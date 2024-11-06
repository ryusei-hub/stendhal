/* $Id$ */
/***************************************************************************
 *                   (C) Copyright 2003-2011 - Stendhal                    *
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

import java.util.ArrayList;
import java.util.List;

import games.stendhal.server.entity.item.Item;
import games.stendhal.server.entity.npc.ConversationStates;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.action.SetQuestAction;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.quests.AbstractQuest;
import marauroa.common.game.RPObject;
import marauroa.common.game.RPSlot;

public class AdosBankChest extends AbstractQuest {

  public static final String QUEST_SLOT = "Ados Bank";

  @Override
  public void addToWorld() {
    fillQuestInfo("Ados Bank", "Summary of contents of Ados Bank Chest", false);
    prepareQuestStep();
  }

  private List<String> getBankContents(final Player player, final String... slots) {
    final List<String> res = new ArrayList<String>();

    for (final String slotName : slots) {
      RPSlot slot = player.getEntitySlot(slotName);
  
      for (final RPObject object : slot) {
        // Ignore if not an item
        if (!(object instanceof Item)) {
          continue;
        }
  
        final Item item = (Item) object;
  
        // Add item with quantity to list
        res.add(String.format("%d %s", item.getQuantity(), item.getName()));
      }
    }

    return res;
  }

  @Override
  public List<String> getHistory(final Player player) {
    final List<String> res = new ArrayList<String>();

    if (!player.hasQuest(QUEST_SLOT)) {
      return res;
    }

    return getBankContents(player, "bank_ados");
  }

  public void prepareQuestStep() {
    SpeakerNPC npc = npcs.get("Rachel");

    npc.add(ConversationStates.ATTENDING,
        "balance",
        null,
        ConversationStates.QUEST_OFFERED,
        "Check your Travel Log to see the contents of your chests!",
        new SetQuestAction(QUEST_SLOT, "start"));
  }

  @Override
  public String getSlotName() {
    return QUEST_SLOT;
  }

  @Override
  public String getName() {
    return "AdosBank";
  }
}