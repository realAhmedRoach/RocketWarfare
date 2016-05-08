package dev.thetechnokid.rw.net;

import java.io.Serializable;
import java.util.ArrayList;

import dev.thetechnokid.rw.entities.Rocket;
import dev.thetechnokid.rw.utils.StringEncryptor;

public class User implements Serializable {
	private static final long serialVersionUID = 12395656776345L;

	private String name;
	private Rank rank = Rank.NOOB;
	private int money;
	private ArrayList<Rocket> blueprints;
	private Preferences prefs;
	
	private byte[] encryptedPassword;
	private byte[] salt;

	public User(String name, String password) {
		this.name = name;
		this.salt = StringEncryptor.generateSalt();
		this.encryptedPassword = StringEncryptor.encryptPassword(password, salt);
		
		prefs = new Preferences(this);
	}
	
	public boolean authenticate(String attemptedPassword) {
		return StringEncryptor.authenticate(attemptedPassword, encryptedPassword, salt);
	}
	
	public String getName() {
		return name;
	}
	
	public Rank getRank() {
		return rank;
	}
	
	public int getMoney() {
		return money;
	}

	public ArrayList<Rocket> getBlueprints() {
		return blueprints;
	}
	
	public void addBlueprint(Rocket print) {
		blueprints.add(print);
	}

	public Preferences getPrefs() {
		return prefs;
	}

}
