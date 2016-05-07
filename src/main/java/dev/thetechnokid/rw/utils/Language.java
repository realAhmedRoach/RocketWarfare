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

	private Language() {
	}

	public static void setLocale(String locale) {
		LOCALE = locale;
		init();
	}

	public static String get(String thing, boolean capitalize) {
		StringBuilder b = new StringBuilder();
		for (String element : thing.split(" ")) {
			String loc = props.getProperty(element, element);
			if (capitalize)
				loc = loc.substring(0, 1).toUpperCase() + loc.substring(1);
			b.append(loc + " ");
		}
		
		return b.substring(0, b.length());
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
