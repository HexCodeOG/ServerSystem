package de.hexcode.mysql;

import java.sql.ResultSet;
import java.util.HashMap;

import de.hexcode.ban.Ban;
import de.hexcode.utils.Utils;

public class Ban_Base {
	
	public void unBanbyID(String ID) {
		Utils.getMysql().update("UPDATE Bans SET Active='0' WHERE BanID='"+ID+"'");
	}
	
	public void unBanByUUID(String UUID) {
		Utils.getMysql().update("UPDATE Bans SET Active='0' WHERE UUID='"+UUID+"'");
	}
	
	public Integer getLastBan() {
		Integer i = 0;
		ResultSet rs = Utils.getMysql().query("SELECT * FROM Bans ORDER BY id DESC LIMIT 1");
		try {
			if(rs.next()) {
				i = rs.getInt("id");
			}
		}catch(Exception e) {e.printStackTrace();}
		return i;
	}
	
	public boolean isUserProtect(String UUID) {
		ResultSet rs = Utils.getMysql().query("SELECT * FROM Player WHERE UUID='"+UUID+"'");
		try {
			if(rs.next()) {
				if(rs.getInt("Protect") == 1) {
					return true;
				}
			}
		}catch(Exception e) {e.printStackTrace();}
		return false;
	}
	
	public void loadBans() {
		int load = 0;
		
		
		ResultSet rs = Utils.getMysql().query("SELECT * FROM Bans");
		try {
			while(rs.next()) {
				new Ban(rs.getInt("id"));
				load++;
			}
		}catch(Exception e) {e.printStackTrace();}
		System.out.println(load + " Bans wurden geladen!");
	}
	
	public boolean isIDExists(String ID) {
		ResultSet rs = Utils.getMysql().query("SELECT * FROM Bans WHERE BanID='"+ID+"'");
		try {
			if(rs.next()) {
				return true;
			}
		}catch(Exception e) {e.printStackTrace();}
		return false;
	}
	
	public void createBan(String UUID, String Banner, String Reason, String BanID, boolean b) {
		Utils.getMysql().update("INSERT INTO Bans(UUID,Banner,Reason,BanID,Active) VALUES('"+UUID+"','"+Banner+"','"+Reason+"','"+BanID+"','"+Boolean.compare(b, false)+"')");
	}
	
	public HashMap<String, Object> loadBan(Integer ID) {
		HashMap<String, Object> ban = new HashMap<>();
		ResultSet rs = Utils.getMysql().query("SELECT * FROM Bans WHERE id='"+ID+"'");
		try {
			if(rs.next()) {
				ban.put("UUID", rs.getString("UUID"));
				ban.put("Banner", rs.getString("Banner"));
				ban.put("Reason", rs.getString("Reason"));
				ban.put("Active", rs.getBoolean("Active"));
				ban.put("BanID", rs.getString("BanID"));
			}
		}catch(Exception e) {e.printStackTrace();}
		return ban;
	}
}
