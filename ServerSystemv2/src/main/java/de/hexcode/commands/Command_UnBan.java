package de.hexcode.commands;

import de.hexcode.ban.Ban;
import de.hexcode.utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_UnBan extends Command {

	public Command_UnBan(String name) {
		super(name);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(args.length == 0) {
				p.sendMessage("§b/Unban ID [ID]");
				p.sendMessage("§b/Unban Name [Name]");
			}else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("ID")) {
					boolean isUnBannded = false;
					String id = args[1];
					for(String s : Utils.getBans().keySet()) {
						Ban ban = Utils.getBans().get(s);
						if(ban.getBanID().equals(id)) {
							isUnBannded = true;
							ban.unban(1, p);
							break;
						}
					}
					if(!isUnBannded) {p.sendMessage(Utils.getPrefix() + "§cAchtung, die ID wurde nicht gefunden!");}
				}
			}
		}
	}

}
