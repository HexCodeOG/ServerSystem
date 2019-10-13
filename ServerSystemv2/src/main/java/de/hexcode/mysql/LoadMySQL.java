package de.hexcode.mysql;

import de.hexcode.ban.LoadBans;
import de.hexcode.login.LoadLogin;
import de.hexcode.utils.Utils;

public class LoadMySQL {
	public LoadMySQL() {
		Utils.setMysql(new API_MySQL(Utils.getMysqlHost(), Utils.getMysqlDatabase(), Utils.getMysqlUser(), Utils.getMysqlPassword(), Utils.getMysqlPort()));
		Utils.getMysql().update("CREATE TABLE IF NOT EXISTS Player(id MEDIUMINT NOT NULL AUTO_INCREMENT,PRIMARY KEY (id),UUID varchar(128), Name varchar(20), FirstJoin long, PlayTime long, Protect boolean)");
		Utils.getMysql().update("CREATE TABLE IF NOT EXISTS Bans(id MEDIUMINT NOT NULL AUTO_INCREMENT,PRIMARY KEY (id), UUID varchar(128), Banner varchar(128), Reason varchar(2000), BanID varchar(128), Active boolean)");
		Utils.getMysql().update("CREATE TABLE IF NOT EXISTS LoginKey(id MEDIUMINT NOT NULL AUTO_INCREMENT,PRIMARY KEY (id),UUID varchar(128), LogKey varchar(128), Passwort varchar(128), Active boolean)");
		
		new LoadBans();
		new LoadLogin();
	}
}
