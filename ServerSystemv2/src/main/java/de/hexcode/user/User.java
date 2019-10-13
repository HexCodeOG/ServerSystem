package de.hexcode.user;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import de.hexcode.mysql.User_Base;
import de.hexcode.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class User extends User_Base{
	
	Timer t;
	
	String Name;
	String UUID;
	Long FirstJoin;
	Long PlayTime;
	boolean Protect;
	Integer id;
	ProxiedPlayer p;
	
	public User(ProxiedPlayer p, String UUID) {
		this.p = p;
		this.UUID = UUID;
		this.Name = this.p.getName();
		checkPlayer();
	}
	
	private void checkPlayer() {
		if(isExists(this.UUID)) {
			loadPlayer();
		}else {
			createPlayer(this.UUID, this.Name);
			loadPlayer();
		}
	}
	
	private void loadPlayer() {
		HashMap<String, Object> loadings = loadPlayer(this.UUID);
		this.id = (Integer) loadings.get("ID");
		this.FirstJoin = (Long) loadings.get("FirstJoin");
		this.PlayTime = (Long) loadings.get("PlayTime");
		int i = (int) loadings.get("Protect");
		if( i == 1) {this.Protect = true;}else {this.Protect = false;}
		System.out.println("Spieler " + this.Name + " wurde geladen!");
		Utils.getUser().put(this.p, this);
		setPlayTime();
	}
	
	public void close() {
		this.t.cancel();
		Utils.getUser().remove(this.p);
	}
	
	public void savePlayer() {
		updatePlayer(this.UUID, this.Name, this.FirstJoin, this.PlayTime, this.Protect);
	}
	
	private void setPlayTime() {
		t = new Timer();
		t.schedule(new TimerTask() {
			
			@Override
			public void run() {
				PlayTime = PlayTime+1;
			}
		}, 0, 1000);
	}

	public Timer getT() {
		return t;
	}

	public void setT(Timer t) {
		this.t = t;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public Long getFirstJoin() {
		return FirstJoin;
	}

	public void setFirstJoin(Long firstJoin) {
		FirstJoin = firstJoin;
	}

	public Long getPlayTime() {
		return PlayTime;
	}

	public void setPlayTime(Long playTime) {
		PlayTime = playTime;
	}

	public boolean isProtect() {
		return Protect;
	}

	public void setProtect(boolean protect) {
		Protect = protect;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProxiedPlayer getP() {
		return p;
	}

	public void setP(ProxiedPlayer p) {
		this.p = p;
	}
}
