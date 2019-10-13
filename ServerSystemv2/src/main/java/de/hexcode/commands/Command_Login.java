package de.hexcode.commands;

import de.hexcode.login.Login;
import de.hexcode.utils.Utils;
import de.hexcode.uuid.uuidfetcher;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_Login extends Command {

	public Command_Login(String name) {
		super(name);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(args.length == 0) {
				p.sendMessage("§b/Login Password");
				p.sendMessage("§b/Login Logout");
				p.sendMessage("§b/login Create [Name] [DevKey]");
				p.sendMessage("§b/Login Set [Password] [PasswordKey]");
			}else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("logout")) {
					String UUID = uuidfetcher.getUUID(p.getName()).toString();
					if(Utils.getLogins().containsKey(UUID)) {
						Login login = Utils.getLogins().get(UUID);
						if(login.isLoggedIn()) {
							login.logOut();
							p.sendMessage(Utils.getPrefix() + "§bDu wurdest abgemeldet!");
						}else {p.sendMessage(Utils.getPrefix() + "§cDu bist nicht angemeldet!");}
					}else {p.sendMessage(Utils.getPrefix() + "§cAchtung, du kannst dich nicht abmelden!");}
				}else {
					String Password = args[0];
					String UUID = uuidfetcher.getUUID(p.getName()).toString();
					if(Utils.getLogins().containsKey(UUID)) {
						Login login = Utils.getLogins().get(UUID);
						if(!login.isLoggedIn()) {
							login.logIn(p, Password);
						}else {p.sendMessage(Utils.getPrefix() + "§cAchtung, du bist bereits angemeldet!");}
					}else {p.sendMessage(Utils.getPrefix() + "§cAchtung, du kannst dich nicht einloggen!");}
				}
			}else if(args.length == 2) {
				
			}else if(args.length == 3) {
				if(args[0].equalsIgnoreCase("create")){
					String Name = args[1];
					String DevKey = args[2];
					if(Utils.getDevKey().equals(DevKey)) {
						Login login = new Login(Name, p);
						if(!login.isExists()) {
							login.create();
						}else {p.sendMessage(Utils.getPrefix() + "§cDer Spieler §e" + Name + " hat bereits ein Passwort!");}
					}else {p.sendMessage(Utils.getPrefix() + "§cAchtung, der Developer Schlüssel wurde nicht angenommen!");}
				}else if(args[0].equalsIgnoreCase("set")) {
					String Password = args[1];
					String PasswordKey = args[2];
					String UUID = uuidfetcher.getUUID(p.getName()).toString();
					if(Utils.getLogins().containsKey(UUID)) {
						Login login = Utils.getLogins().get(UUID);
						login.setPassword(Password, PasswordKey, p);
					}else {p.sendMessage(Utils.getPrefix() + "§cAchtung, von dir wird kein Passwort erwartet!");}
				}
			}
		}
	}

}
