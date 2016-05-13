package dev.thetechnokid.rw.net;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import dev.thetechnokid.rw.RocketWarfare;
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

	private boolean authenticate(String attemptedPassword) {
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

	public void save() throws Exception {
		File file = new File(RocketWarfare.settingsFolder() + "/users/" + name);
		file.setWritable(true);
		file.mkdirs();
		file.createNewFile();
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(file));
		o.writeObject(this);
		o.flush();
		o.close();
	}

	public static User load(String name, String attemptedPassword) throws Exception {
		User user = null;
		File file = new File(RocketWarfare.settingsFolder() + "/users/" + name);
		file.setReadable(true);
		try (ObjectInputStream i = new ObjectInputStream(new FileInputStream(file));){
			user = (User) i.readObject();
			if (!user.authenticate(attemptedPassword))
				return null;
		} catch (FileNotFoundException e) {
			return null;
		}
		return user;
	}
}
