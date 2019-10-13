package de.hexcode.commands;

import de.hexcode.ban.Ban;
import de.hexcode.utils.Utils;
import de.hexcode.uuid.uuidfetcher;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_Ban extends Command {

	public Command_Ban(String name) {
		super(name);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			String UUID = uuidfetcher.getUUID(p.getName()).toString();
			
			if(args.length == 0) {
				p.sendMessage("§b/Ban [Name] [ID]");
				p.sendMessage("§b1:Beleidigung");
				p.sendMessage("§b2:Trolling");
				p.sendMessage("§b3:Arbeits Behinderung");
				p.sendMessage("§b4:Bug Nutzung");
				p.sendMessage("§b5:Hacking");
				p.sendMessage("§b6:Development Ban");
			}else if(args.length == 1) {
				p.sendMessage("§b/Ban [Name] [ID]");
				p.sendMessage("§b1:Beleidigung");
				p.sendMessage("§b2:Trolling");
				p.sendMessage("§b3:Arbeits Behinderung");
				p.sendMessage("§b4:Bug Nutzung");
				p.sendMessage("§b5:Hacking");
				p.sendMessage("§b6:Development Ban");
			}else if(args.length == 2) {
				Ban ban;
				switch(args[1]) {
					case "1":
						 ban = new Ban(args[0], UUID, "Beleidigung", p);
						 if(!ban.isUUIDExists()) {p.sendMessage(Utils.getPrefix() + "§cAchtung den Spieler 36" + args[0] + "§c gibt es nicht!");}
						ban.create();
						break;
					case "2":
						 ban = new Ban(args[0], UUID, "Trolling", p);
						 if(!ban.isUUIDExists()) {p.sendMessage(Utils.getPrefix() + "§cAchtung den Spieler 36" + args[0] + "§c gibt es nicht!");}
						ban.create();
						break;
					case "3":
						ban = new Ban(args[0], UUID, "Arbeits Behinderung", p);
						if(!ban.isUUIDExists()) {p.sendMessage(Utils.getPrefix() + "§cAchtung den Spieler 36" + args[0] + "§c gibt es nicht!");}
						ban.create();
						break;
					case "4":
						ban = new Ban(args[0], UUID, "Bug Nutzung", p);
						if(!ban.isUUIDExists()) {p.sendMessage(Utils.getPrefix() + "§cAchtung den Spieler 36" + args[0] + "§c gibt es nicht!");}
						ban.create();
						break;
					case "5":
						ban = new Ban(args[0], UUID, "Hacking", p);
						if(!ban.isUUIDExists()) {p.sendMessage(Utils.getPrefix() + "§cAchtung den Spieler 36" + args[0] + "§c gibt es nicht!");}
						ban.create();
						break;
					case "6":
						ban = new Ban(args[0], UUID, "Development Ban", p);
						if(!ban.isUUIDExists()) {p.sendMessage(Utils.getPrefix() + "§cAchtung den Spieler 36" + args[0] + "§c gibt es nicht!");}
						ban.create();
						break;
					default:
						p.sendMessage(Utils.getPrefix() + "§cDer Ausdruck §e" + args[1] + "§c ist nicht richtig!");
						break;
				}
			}
		}
	}

}
