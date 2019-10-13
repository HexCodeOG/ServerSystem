package de.hexcode.login;

import java.io.File;
import java.util.HashMap;
import java.util.Random;

import de.hexcode.configuration.file.YamlConfiguration;
import de.hexcode.mysql.Login_Base;
import de.hexcode.utils.Utils;
import de.hexcode.uuid.uuidfetcher;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Login extends Login_Base{
	boolean isLoggedIn;
	boolean waitForPassword;
	boolean isActiv;
	String password;
	String passwordKey;
	Integer Id;
	String UUID;
	String Name;
	ProxiedPlayer p;
	
	public Login(String Name, ProxiedPlayer p) {
		this.UUID = uuidfetcher.getUUID(Name).toString();
		this.Name = Name;
		this.p = p;
	}
	
	public Login(String UUID) {
		this.Name = uuidfetcher.getName(java.util.UUID.fromString(UUID));
		this.UUID = UUID;
		load();
	}
	
	@SuppressWarnings("deprecation")
	public void logIn(ProxiedPlayer p, String password) {
		if(this.password.equals(password)) {
			this.isLoggedIn = true;
			p.sendMessage(Utils.getPrefix() + "§bDu hast nun volle Command Kraft!");
		}else {
			p.sendMessage(Utils.getPrefix() + "§cAchtung, das Passwort stimmt nicht!");
		}
	}
	
	public void logOut() {
		this.isLoggedIn = false;
	}
	
	public boolean isExists() {
		return isUserExists(this.UUID);
	}
	
	@SuppressWarnings("deprecation")
	public void create() {
		String key = createID();
		if(!isKeyExists(key)) {
			create(this.UUID, key);
			this.p.sendMessage(Utils.getPrefix() + "§eFür den Spieler §c" + this.Name + "§e wurde ein Login Key angelegt! Key: §b" + key);
			File f = new File("plugins/ServerSystem/loginkeys", this.Name+".yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
			cfg.set("Key", key);
			try {cfg.save(f);}catch(Exception e) {e.printStackTrace();}
			load();
		}else {
			create();
		}
	}
	
	private void load() {
		HashMap<String, Object> values = getValues(this.UUID);
		this.passwordKey = (String) values.get("LogKey");
		this.password = (String) values.get("Password");
		this.isActiv = (boolean) values.get("Active");
		this.Id = (Integer) values.get("ID");
		if(this.password.equals("null")) {this.waitForPassword = true;}else {this.waitForPassword = false;}
		this.isLoggedIn = false;
		Utils.getLogins().put(this.UUID, this);
	}
	
	public boolean isLoggedIn() {
		return this.isLoggedIn;
	}
	
	@SuppressWarnings("deprecation")
	public void setPassword(String password, String key, ProxiedPlayer p) {
		if(this.waitForPassword) {
			if(key.equals(this.passwordKey)) {
				setPassword(this.UUID, password);
				p.sendMessage(Utils.getPrefix() + "§bDu hast dein Passwort gesetzt! Passwort: §c" + password);
				this.password = password;
				this.waitForPassword = false;
			}else {
				p.sendMessage(Utils.getPrefix() + "§cAchtung, der Key wurde nicht angenommen!");
			}
		}else {
			p.sendMessages(Utils.getPrefix() + "§cAchtung, Du hast dein Passwort bereits gesetzt!");
		}
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
