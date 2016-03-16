package dev.thetechnokid.rw.utils;

import java.io.*;
import java.sql.Date;
import java.text.*;
import java.time.Instant;

public class Logger {
	private File file;
	BufferedWriter w;

	private static DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static enum Level {
		INFO(0), DEBUG(1), WARNING(2), ERROR(3);

		int level;

		Level(int level) {
			this.level = level;
		}
	}

	public Logger() {
		this("log");
	}

	public Logger(String name) {
		try {
			file = new File(getDefaultDir() + "/RocketWarfare/" + name + ".txt");
			File parent = file.getParentFile();
			if (!parent.exists()) {
				parent.mkdirs();
				file.createNewFile();
			}
			w = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void log(String text, Level level) {
		String toWrite = f.format(Date.from(Instant.now())) + " [" + level.name() + "] : " + text;
		try {
			w.write(toWrite);
			System.out.println(toWrite);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getDefaultDir() {
		String OS = System.getProperty("os.name").toUpperCase();
		if (OS.contains("WIN"))
			return System.getenv("APPDATA");
		else if (OS.contains("MAC"))
			return System.getProperty("user.home") + "/Library/Application Support";
		else if (OS.contains("NUX"))
			return System.getProperty("user.home");
		return System.getProperty("user.dir");
	}
}
