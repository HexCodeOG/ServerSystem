package de.hexcode.serversystem;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;

import de.hexcode.commands.Command_Ban;
import de.hexcode.commands.Command_Login;
import de.hexcode.commands.Command_UnBan;
import de.hexcode.configuration.file.YamlConfiguration;
import de.hexcode.events.Event_PlayerChat;
import de.hexcode.events.Event_PlayerConnect;
import de.hexcode.events.Event_PlayerLeav;
import de.hexcode.mysql.LoadMySQL;
import de.hexcode.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public class ServerSystem extends Plugin{
	
	
	
	@Override
	public void onEnable() {
		Utils.setUser(new HashMap<>());
		Utils.setBans(new HashMap<>());
		Utils.setLogins(new HashMap<>());
		Utils.setDevKey("&.1BFvy96AL}3YU");
		this.getProxy().getPluginManager().registerCommand(this, new Command_Login("Login"));
		this.getProxy().getPluginManager().registerCommand(this, new Command_Ban("Ban"));
		this.getProxy().getPluginManager().registerCommand(this, new Command_UnBan("Unban"));
		setUpConfig();
		registerEvents();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public void registerEvents() {
		HashSet<Listener> events = new HashSet<>();
		events.add(new Event_PlayerChat());
		events.add(new Event_PlayerConnect());
		events.add(new Event_PlayerLeav());
		for(Listener l : events) {
			this.getProxy().getPluginManager().registerListener(this, l);
		}
	}
	
	public void setUpConfig() {
		File f = new File("plugins/ServerSystem/config.cfg");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		set(f, "Config.Prefix", "&6&o&lLuna&8&o&f: &f");
		set(f, "Config.tsbot", true);
		set(f, "Config.tsip", "127.0.0.1");
		set(f, "Config.tsport", 9987);
		set(f, "Config.tsuser", "user");
		set(f, "Config.Password", "password");
		set(f, "Config.tsinfochannel", 0);
		set(f, "Config.MySQL.Host", "host");
		set(f, "Config.MySQL.User", "User");
		set(f, "Config.MySQL.Password", "Password");
		set(f, "Config.MySQL.Database", "Db");
		set(f, "Config.MySQL.Port", 3306);
		
		Utils.setPrefix(ChatColor.translateAlternateColorCodes('&', cfg.getString("Config.Prefix")));
		Utils.setTeamspeakbot(cfg.getBoolean("Config.tsbot"));
		Utils.setTsIp(cfg.getString("Config.tsip"));
		Utils.setTsPort(cfg.getInt("Config.tsport"));
		Utils.setTsUser(cfg.getString("Config.tsuser"));
		Utils.setTsPassword(cfg.getString("Config.Password"));
		Utils.setTsInfoChannel(cfg.getInt("Config.tsinfochannel"));
		Utils.setMysqlHost(cfg.getString("Config.MySQL.Host"));
		Utils.setMysqlUser(cfg.getString("Config.MySQL.User"));
		Utils.setMysqlPassword(cfg.getString("Config.MySQL.Password"));
		Utils.setMysqlDatabase(cfg.getString("Config.MySQL.Database"));
		Utils.setMysqlPort(cfg.getInt("Config.MySQL.Port"));
		Utils.setNoPerm(Utils.getPrefix() + "§cAchtung, fehlen die richtigen Rechte!");
		if(Utils.getMysqlHost().equalsIgnoreCase("host")) {
			System.out.println("Achtung, MySQL Daten sind noch nicht vorhanden!");
			return;
		}
		
		new LoadMySQL();
	}
	
	private void set(File f, String path, Object obj) {
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		if(!cfg.isSet(path)) {
			cfg.set(path, obj);
			try {cfg.save(f);}catch(Exception e) {e.printStackTrace();}
		}
	}
	
}
