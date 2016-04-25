package dev.thetechnokid.rw.net;

import java.io.Serializable;
import java.util.ArrayList;

import dev.thetechnokid.rw.entities.Rocket;

public class User implements Serializable {
	private static final long serialVersionUID = 12395656776345L;

	private String name;
	private int level;
	private int money;
	private ArrayList<Rocket> blueprints;

	public User(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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
