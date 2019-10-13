package de.hexcode.events;

import de.hexcode.utils.Utils;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Event_PlayerLeav implements Listener {
	@EventHandler
	public void onDis(PlayerDisconnectEvent e) {
		if(Utils.getUser().containsKey(e.getPlayer())) {
			Utils.getUser().get(e.getPlayer()).savePlayer();
			Utils.getUser().get(e.getPlayer()).close();
		}
	}
}
