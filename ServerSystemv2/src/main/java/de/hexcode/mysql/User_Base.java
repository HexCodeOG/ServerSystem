package de.hexcode.mysql;

import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;

import de.hexcode.utils.Utils;

public class User_Base {
	public boolean isExists(String UUID) {
		ResultSet rs = Utils.getMysql().query("SELECT * FROM Player WHERE UUID='"+UUID+"'");
		try {
			if(rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void createPlayer(String UUID, String Name) {
		Utils.getMysql().update("INSERT INTO Player(UUID,Name,FirstJoin,PlayTime,Protect) VALUES('"+UUID+"','"+Name+"','"+new Date().getTime()+"','0','0')");
	}
	
	public HashMap<String, Object> loadPlayer(String UUID){
		HashMap<String, Object> loading = new HashMap<>();
		ResultSet rs = Utils.getMysql().query("SELECT * FROM Player WHERE UUID='"+UUID+"'");
		try {
			if(rs.next()) {
				loading.put("Name", rs.getString("Name"));
				loading.put("FirstJoin", rs.getLong("FirstJoin"));
				loading.put("PlayTime", rs.getLong("PlayTime"));
				loading.put("Protect", rs.getInt("Protect"));
				loading.put("ID", rs.getInt("id"));
			}
		}catch(Exception e) {e.printStackTrace();}
		return loading;
	}
	
	public void updatePlayer(String UUID, String Name, Long FirstJoin, Long PlayTime, boolean Protect) {
		Utils.getMysql().update("UPDATE Player SET Name='"+Name+"',FirstJoin='"+FirstJoin+"', PlayTime='"+PlayTime+"', Protect='"+Boolean.compare(Protect, false)+"' WHERE UUID='"+UUID+"'");
	}
}
