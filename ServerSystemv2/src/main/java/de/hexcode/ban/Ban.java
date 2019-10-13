package de.hexcode.ban;

import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import de.hexcode.mysql.Ban_Base;
import de.hexcode.utils.Utils;
import de.hexcode.uuid.uuidfetcher;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Ban extends Ban_Base{
	String UUid;
	String Name;
	String Banner;
	String Reason;
	String BannerName;
	String BanID;
	Boolean Active;
	Integer ID;
	
	ProxiedPlayer p;
	
	public Ban(Integer DataBaseID) {
		this.ID = DataBaseID;
		load();
	}
	
	public Ban(String Name, String Banner, String Reason, ProxiedPlayer Self) {
		this.Name = Name;
		this.Banner = Banner;
		this.Reason = Reason;
		this.p = Self;
		this.BannerName = p.getName();
	}
	
	private void load() {
		HashMap<String, Object> ban = loadBan(this.ID);
		this.UUid = (String) ban.get("UUID");
		this.Reason = (String) ban.get("Reason");
		this.Banner = (String) ban.get("Banner");
		this.Active = (Boolean) ban.get("Active");
		this.BanID = (String) ban.get("BanID");
		this.BannerName = uuidfetcher.getName(UUID.fromString(this.Banner));
		this.Name = uuidfetcher.getName(UUID.fromString(this.UUid));
		Utils.getBans().put(this.UUid, this);
	}
	
	public String getBanID() {
		return this.BanID;
	}
	
	@SuppressWarnings("deprecation")
	public void unban(Integer id, ProxiedPlayer p) {
		if(id == 1) {
			unBanbyID(this.BanID);
			System.out.println("UnBan ID!");
		}else if(id == 2){
			unBanByUUID(this.UUid);
		}
		p.sendMessage(Utils.getPrefix() + "§cDer Spieler §e" + uuidfetcher.getName(UUID.fromString(this.UUid)) + "§c wurde entbannt!");
	}
	
	public void checkBanned() {
		if(this.Active == true) {
			removePlayerFromServer();
		}
	}
	
	public boolean isUUIDExists() {
		try {
			this.UUid = uuidfetcher.getUUID(this.Name).toString();
			return true;
		}catch(Exception e) {return false;}
	}
	
	public boolean isActiv() {
		return this.Active;
	}
	
	public String getBannerName() {
		return uuidfetcher.getName(java.util.UUID.fromString(this.Banner));
	}
	
	@SuppressWarnings("deprecation")
	public void create() {
		if(!isUserProtect(this.UUid)) {
			String id = createID();
			if(!isIDExists(id)) {
				createBan(this.UUid, this.Banner, this.Reason, id,true);
				this.BanID = id;
				this.ID = getLastBan();
				
				removePlayerFromServer();
				load();
				
				p.sendMessage(Utils.getPrefix() + "§cDu hast soeben den Spieler §e" + this.Name + "§c vom Sever gebannt!");
			}else {create();}
		}else {p.sendMessage(Utils.getPrefix() + "§cAchtung, der Spieler ist geschützt!");}
	}
	
	@SuppressWarnings("deprecation")
	private void removePlayerFromServer() {
		try {
			Timer t = new Timer();
			t.schedule(new TimerTask() {
				
				@Override
				public void run() {
					if(BungeeCord.getInstance().getPlayer(Name).isConnected()) {
						ProxiedPlayer p = BungeeCord.getInstance().getPlayer(Name);
						p.disconnect("§eAchtung §4"+p.getName() + "§e du wurdest vom Ardnarun.com Netzwerk ausgeschlossen!\n\n"
								+ "§eBegründung: §6" +Reason + "\n"
								+ "§eGebannt von §6" +Name + "\n"
								+ "§eDeine BanID: §6" +BanID + "\n"
								+ "§eGebannt bis §6 Immer" + "\n\n"
								+ "§eDu Denkst das dieser Bann nicht gerecht ist, dann melde dich bei uns im Forum\n"
								+ "§eUnter §4Ardnarun.com §e wir wünschen dir weiterhin viel Spaß!");
						
						
					}
				}
			}, 800);
		}catch(Exception e) {e.printStackTrace();}
	}
	
	private String createID() {
		String value = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		int le = 32;
		char[] carray = value.toCharArray();
		
		String string = "AB-";
		
		Random rnd = new Random();
		
		for(int i = 0; i < le; i++) {
			string = string+carray[rnd.nextInt(carray.length)];
		}
		System.out.println(string);
		return string;
	}
}
