package dev.thetechnokid.rw;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class Version {
	public static final String NAME = "Rocket Warfare";
	public static final String VERSION = "0.0.1-SNAPSHOT";
	public static final String FRIENDLY = "Dev Edition";

	private static final String versionSite = "http://thetechnokid.github.com/RocketWarfare/version.txt";
	private static ArrayList<String> data;

	static {
		try {
			URL url = new URL(versionSite);
			InputStream html = url.openStream();
			BufferedReader r = new BufferedReader(new InputStreamReader(html));

			data = new ArrayList<String>();
			String line = null;
			while ((line = r.readLine()) != null)
				data.add(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getVersion() {
		return data.get(1);
	}

	public static String getFriendly() {
		return data.get(2);
	}

	public static String getDescription() {
		return data.get(3);
	}
}
