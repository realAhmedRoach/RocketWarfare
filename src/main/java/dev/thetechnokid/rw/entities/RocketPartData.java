package dev.thetechnokid.rw.entities;

import java.io.*;
import java.util.stream.Stream;

import dev.thetechnokid.rw.RocketWarfare;
import dev.thetechnokid.rw.utils.Assets;
import javafx.scene.image.Image;

public class RocketPartData {

	private static BufferedReader r = new BufferedReader(
			new InputStreamReader(RocketWarfare.class.getResourceAsStream("/images/rparts.txt")));
	private static Stream<String> lines = r.lines();

	public static RocketPart get(String type, String tier, boolean flipped) {
		try {
			String line = lines.filter(stuff -> stuff.startsWith(type + "_" + tier)).findAny().get();
			System.out.println(line);
			String[] parts = line.split(" ");
			String[] name = parts[0].split("_");
			String[] locString = (parts[1].split(","));
			int[] loc = { Integer.parseInt(locString[0]), Integer.parseInt(locString[1]) };
			Image image = Assets.crop(Assets.ROCKET_PARTS, loc[0], loc[1]);
			int mass = Integer.parseInt(parts[2]);
			return new RocketPart(type, tier, mass, image, flipped);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Image image(String type, String tier, boolean flipped) {
		try {
			String line = lines.filter(stuff -> stuff.startsWith(type + "_" + tier)).findAny().get();
			System.out.println(line);
			String[] parts = line.split(" ");
			String[] name = parts[0].split("_");
			if (!name[0].equalsIgnoreCase(type) || !name[1].equalsIgnoreCase(tier))
				return null;
			String[] locString = (parts[1].split(","));
			int[] loc = { Integer.parseInt(locString[0]), Integer.parseInt(locString[1]) };
			Image image = Assets.crop(Assets.ROCKET_PARTS, loc[0], loc[1]);
			return image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
