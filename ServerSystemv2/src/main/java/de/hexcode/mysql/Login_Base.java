package de.hexcode.mysql;

import de.hexcode.login.Login;
import de.hexcode.utils.Utils;

import java.sql.ResultSet;
import java.util.HashMap;

public class Login_Base {
	
	public void loadLogins() {
		
		int i = 0;
		
		ResultSet rs = Utils.getMysql().query("SELECT * FROM LoginKey");
		try {
			while(rs.next()) {
				new Login(rs.getString("UUID"));
				i++;
			}
		}catch(Exception e) {e.printStackTrace();}
		System.out.println(i+ " Login/s wurde/n geladen!");
	}
	
	public boolean isUserExists(String UUID) {
		ResultSet rs = Utils.getMysql().query("SELECT * FROM LoginKey WHERE UUID='"+UUID+"'");
		try {
			if(rs.next()) {
				return true;
			}
		}catch(Exception e) {e.printStackTrace();}
		return false;
	}
	
	public boolean isKeyExists(String Key) {
		ResultSet rs = Utils.getMysql().query("SELECT * FROM LoginKey WHERE LogKey='"+Key+"'");
		try {
			if(rs.next()) {
				return true;
			}
		}catch(Exception e) {e.printStackTrace();}
		return false;
	}
	
	public HashMap<String, Object> getValues(String UUID){
		HashMap<String, Object> values = new HashMap<>();
		ResultSet rs = Utils.getMysql().query("SELECT * FROM LoginKey WHERE UUID='"+UUID+"'");
		try {
			if(rs.next()) {
				values.put("ID", rs.getInt("id"));
				values.put("LogKey", rs.getString("Logkey"));
				values.put("Password", rs.getString("Passwort"));
				values.put("Active", rs.getBoolean("Active"));
			}
		}catch(Exception e) {e.printStackTrace();}
		return values;
	}
	
	public void create(String UUID, String LogKey) {
		Utils.getMysql().update("INSERT INTO LoginKey(UUID,LogKey,Passwort,Active) VALUES('"+UUID+"','"+LogKey+"','null','1')");
	}
	
	public void setPassword(String UUID, String Password) {
		Utils.getMysql().update("UPDATE LoginKey Set Passwort='"+Password+"' WHERE UUID='"+UUID+"'");
	}
	
	public void setActive(String UUID, Boolean b) {
		Utils.getMysql().update("UPDATE LoginKey Set Active='"+b+"' WHERE UUID='"+UUID+"'");
	}
	
	public void delete(String UUID) {
		Utils.getMysql().update("DELETE FROM LoginKey WHERE UUID='"+UUID+"'");
	}
}
