package dev.thetechnokid.rw.net;

import java.io.Serializable;
import java.util.ArrayList;

import dev.thetechnokid.rw.entities.Rocket;

public class User implements Serializable {
	private static final long serialVersionUID = 12395656776345L;

	private String name;
	private Rank rank = Rank.NOOB;
	private int money;
	private ArrayList<Rocket> blueprints;
	private String encryptedPassword;
	private byte[] salt;

	public User(String name) {
		this.name = name;
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

}
