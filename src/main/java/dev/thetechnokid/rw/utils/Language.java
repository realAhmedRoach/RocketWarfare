package dev.thetechnokid.rw.utils;

import java.io.*;
import java.util.*;

import dev.thetechnokid.rw.RocketWarfare;
import dev.thetechnokid.rw.controllers.MainGameController;

public class Language {
	private static Properties props = new Properties();

	public static HashMap<String, String> LOCALES;

	private static String LOCALE = null;

	static {
		LOCALES = new HashMap<>();
		LOCALES.put("English", "en_US");
		LOCALES.put("Spanish", "es_ES");
	}

	private Language() {
	}

	public static String get(String thing, boolean capitalize) {
		StringBuilder b = new StringBuilder();
		for (String element : thing.split(" ")) {
			String loc = props.getProperty(element, element);
			if (capitalize)
				loc = loc.substring(0, 1).toUpperCase() + loc.substring(1);
			b.append(loc + " ");
		}

		return b.substring(0, b.length() - 1);
	}

	public static String get(String thing) {
		return get(thing, true);
	}

	public static void setLocale(String locale) {
		LOCALE = locale;
		init();
	}

	public static void init() {
		try {
			InputStream inputStream = RocketWarfare.class.getResourceAsStream(
					"/languages/" + (LOCALE == null ? MainGameController.get().USER.getPrefs().getLanguage() : LOCALE)
							+ ".properties");
			if (inputStream != null)
				props.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
