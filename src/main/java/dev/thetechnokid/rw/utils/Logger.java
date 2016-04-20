package dev.thetechnokid.rw.utils;

import java.io.*;
import java.text.*;
import java.time.Instant;
import java.util.Date;

public class Logger {
	private File file;
	private BufferedWriter w;
	private boolean inFile = true;

	private static DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static enum Level {
		INFO(0), DEBUG(1), WARNING(2), ERROR(3);

		int level;

		public int level() {
			return level;
		}

		Level(int level) {
			this.level = level;
		}
	}

	public Logger() {
		this("log");
	}

	public Logger(String name) {
		if (name == null)
			inFile = false;
		try {
			file = new File(getDefaultDir() + "/RW_" + name + ".txt");
			if (!file.exists()) {
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
			if (inFile) {
				w.write(toWrite);
				w.newLine();
				w.flush();
			}
			if (level.level() < 2)
				System.out.println(toWrite);
			else
				System.err.println(toWrite);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getFileName() {
		return file.getAbsolutePath();
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
