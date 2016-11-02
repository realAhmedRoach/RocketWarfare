package dev.thetechnokid.rw.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dev.thetechnokid.rw.RocketWarfare;

public class Settings {
	private String rememberedUser;
	
	private static boolean loaded = false;
	private static Settings current;
	
	public static Settings getSettings() {
		if(!loaded) {
			File file = new File(RocketWarfare.settingsFolder() + "/settings.ser");
			file.setReadable(true);
			try(ObjectInputStream i = new ObjectInputStream(new FileInputStream(file));	) {
				current = (Settings) i.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return current;
	}
	
	public String getRememberedUser() {
		return rememberedUser;
	}

	public void setRememberedUser(String rememberedUser) {
		this.rememberedUser = rememberedUser;
	}

	public static Settings getCurrent() {
		return current;
	}

	public static void saveSettings() throws IOException {
		File file = new File(RocketWarfare.settingsFolder() + "/settings.ser");
		file.setWritable(true);
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(file));
		o.writeObject((current!=null)? current : (current = new Settings()));
		o.flush();
		o.close();
	}
}
