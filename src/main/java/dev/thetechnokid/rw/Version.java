package dev.thetechnokid.rw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

	/**
	 * Checks whether there is a new version, ignore SNAPSHOT versions and the
	 * like.
	 * 
	 * @return Whether there is a new version available.
	 */
	public static boolean newVersion() {
		String thisVersion = VERSION.split("-")[0];
		String otherVersion = getVersion().split("-")[0];

		String[] splitThis = thisVersion.split(".");
		String[] splitOther = otherVersion.split(".");

		int majorThis = Integer.parseInt(splitThis[0]);
		int minorThis = Integer.parseInt(splitThis[1]);
		int patchThis = Integer.parseInt(splitThis[2]);

		int majorOther = Integer.parseInt(splitOther[0]);
		int minorOther = Integer.parseInt(splitOther[1]);
		int patchOther = Integer.parseInt(splitOther[2]);

		if (majorOther > majorThis || minorOther > minorThis)
			return true;
		return (patchOther > patchThis && (majorOther >= majorThis || minorOther >= minorThis));

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
