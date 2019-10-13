package de.hexcode.utils;

import java.util.HashMap;

import de.hexcode.ban.Ban;
import de.hexcode.login.Login;
import de.hexcode.mysql.API_MySQL;
import de.hexcode.serversystem.ServerSystem;
import de.hexcode.user.User;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Utils {
	@Getter @Setter
	public static ServerSystem instance;
	@Getter @Setter
	public static API_MySQL mysql;
	@Getter @Setter
	public static String prefix;
	@Getter @Setter
	public static String mysqlHost;
	@Getter @Setter
	public static String mysqlUser;
	@Getter @Setter
	public static String mysqlPassword;
	@Getter @Setter
	public static String mysqlDatabase;
	@Getter @Setter
	public static Integer mysqlPort;
	@Getter @Setter
	public static boolean teamspeakbot;
	@Getter @Setter
	public static String tsIp;
	@Getter @Setter
	public static String tsName;
	@Getter @Setter
	public static String tsUser;
	@Getter @Setter
	public static String tsPassword;
	@Getter @Setter
	public static Integer tsInfoChannel;
	@Getter @Setter
	public static Integer tsPort;
	@Getter @Setter
	public static HashMap<ProxiedPlayer, User> User;
	@Getter @Setter
	public static HashMap<String, Ban> Bans;
	@Getter @Setter
	public static String DevKey;
	@Getter @Setter
	public static HashMap<String, Login> Logins;
	@Getter @Setter
	public static String NoPerm;
}
