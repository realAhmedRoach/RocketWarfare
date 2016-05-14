package dev.thetechnokid.rw.utils;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dev.thetechnokid.rw.RocketWarfare;

public class Logger {
	private File file;
	private BufferedWriter w;
	private boolean inFile = true;

	private static DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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
		if (name == null) {
			inFile = false;
			return;
		}
		try {
			file = new File(RocketWarfare.settingsFolder() + "/RW_" + name + ".txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			w = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void log(String text, Level level) {
		String toWrite = LocalDateTime.now().format(f) + " [" + level.name() + "] : " + text;
		try {
			if (inFile) {
				w.append(toWrite);
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
}
