package de.hexcode.events;

import de.hexcode.user.User;
import de.hexcode.utils.Utils;
import de.hexcode.uuid.uuidfetcher;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Event_PlayerConnect implements Listener {
	@EventHandler
	public void onJoin(PostLoginEvent e) {
		String uuid = uuidfetcher.getUUID(e.getPlayer().getName()).toString();
		if(Utils.getBans().containsKey(uuid)) {
			if(Utils.getBans().get(uuid).isActiv()) {
				Utils.getBans().get(uuid).checkBanned();
				System.out.println("ist drin ");
			}else {
				new User(e.getPlayer(), uuid);
				System.out.println("ist drin aber nicht Aktiv!");
			}
		}else {
			new User(e.getPlayer(), uuid);
			System.out.println("Ist nicht Drin!");
		}
	}
}
