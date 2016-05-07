package dev.thetechnokid.rw.utils;

import java.io.*;
import java.util.Properties;

import dev.thetechnokid.rw.RocketWarfare;

public class Language {
	private static String LOCALE = "en_US";
	private static Properties props = new Properties();

	static {
		init();
	}

	public static void setLocale(String locale) {
		LOCALE = locale;
		init();
	}

	public String get(String thing) {
		StringBuilder b = new StringBuilder();
		for (String element : thing.split(" ")) {
			b.append(props.getProperty(element) + " ");
		}
		return b.toString();
	}

	private static void init() {
		try {
			InputStream inputStream = RocketWarfare.class.getResourceAsStream("/languages/" + LOCALE + ".properties");
			props.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
