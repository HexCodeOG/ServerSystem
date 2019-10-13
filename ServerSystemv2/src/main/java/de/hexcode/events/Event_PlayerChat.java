package de.hexcode.events;
import de.hexcode.login.Login;
import de.hexcode.utils.Utils;
import de.hexcode.uuid.uuidfetcher;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Event_PlayerChat implements Listener {
	
	String[] lockCommands =  {"/ban","/unban","/chatban","/chatunban","/protect","/cloud","/c","/cloudserver","/cs","/pl","/?","/bukkit","/version","/cperms"};
	String[] DenyCommands = {"//calc","/worldedit:calc","/op"};
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onChat(ChatEvent e) {
		String[] message = e.getMessage().split(" ");
		char[] c = e.getMessage().toCharArray();
		ProxiedPlayer p = (ProxiedPlayer) e.getSender();
		String UUID = uuidfetcher.getUUID(p.getName()).toString();
		if(c[0] == '/') {
			if(isDenyCommand(message[0])) {
				p.sendMessage(Utils.getPrefix() + "§cAchtung, diesen Command darfst du hier nicht verwenden!");
				e.setCancelled(true);
			}else if(isLocktCommand(message[0])) {
				if(isUserLoggedIn(UUID)){
					
				}else {p.sendMessage(Utils.getPrefix() + "§cAchtung, dieser Command ist gesichert!");e.setCancelled(true);}
			}
		}
	}
	
	public boolean isUserLoggedIn(String UUID) {
		if(Utils.getLogins().containsKey(UUID)) {
			Login login = Utils.getLogins().get(UUID);
			if(login.isLoggedIn()) {
				return true;
			}else {return false;}
		}
		return false;
	}
	
	public boolean isLocktCommand(String command) {
		for(String s : lockCommands) {
			if(command.equalsIgnoreCase(s)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isDenyCommand(String command) {
		for(String s : DenyCommands) {
			if(command.equalsIgnoreCase(s)) {
				return true;
			}
		}
		return false;
	}
}
