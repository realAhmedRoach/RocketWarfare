package dev.thetechnokid.rw.entities;

import java.io.*;
import java.util.ArrayList;

import dev.thetechnokid.rw.RocketWarfare;
import dev.thetechnokid.rw.utils.*;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class RocketPart extends Entity {

	private int mass;
	private Point2D posInRocket;
	private RocketPart north, south, east, west;

	protected String type, tier;

	private static ArrayList<RocketPart> rocketParts = new PartList();

	public static final String[] FLIPPABLE_PARTS = { "FIN", "MISSILE" };

	static {
		BufferedReader r = new BufferedReader(
				new InputStreamReader(RocketWarfare.class.getResourceAsStream("/images/rparts.txt")));

		String line = null;
		try {
			while ((line = r.readLine()) != null) {
				if ((line.trim()).isEmpty())
					continue;
				RocketPart p = new RocketPart();
				String[] parts = line.split(" ");
				String[] name = parts[0].split("_");
				String[] locString = (parts[1].split(","));
				int[] loc = { Integer.parseInt(locString[0]), Integer.parseInt(locString[1]) };
				Image image = Assets.crop(Assets.ROCKET_PARTS, loc[0], loc[1]);
				p.image = image;
				p.mass = Integer.parseInt(parts[2]);
				p.type = name[0];
				p.tier = name[1];

				rocketParts.add(p);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static RocketPart get(String type, String tier) {
		for (RocketPart part : rocketParts) {
			if (part.type.equals(type.toUpperCase()) && part.tier.equals(tier.toUpperCase()))
				return part;
		}
		return null;
	}

	// no-arg constructor
	public RocketPart() {
	}

	public RocketPart(RocketPart other, boolean flip) {
		this.mass = other.mass;
		this.type = other.type;
		this.tier = other.tier;
		this.north = other.south;
		this.south = other.south;
		this.east = other.west;
		this.west = other.west;
		this.posInRocket = other.posInRocket;
		this.image = other.image;
		if (flip)
			this.image = Assets.flip(image);
	}

	public RocketPart(RocketPart other) {
		this(other, false);
	}

	public boolean equals(RocketPart other) {
		return this.mass == other.mass && this.type == other.type && this.tier == other.tier
				&& this.north == other.south && this.south == other.south && this.east == other.west
				&& this.west == other.west;
	}

	public int getMass() {
		return mass;
	}

	public Point2D getPosInRocket() {
		return posInRocket;
	}

	public void setPosInRocket(Point2D posInRocket) {
		this.posInRocket = posInRocket;
	}

	public RocketPart getNorth() {
		return north;
	}

	public void setNorth(RocketPart north) {
		this.north = north;
	}

	public RocketPart getSouth() {
		return south;
	}

	public void setSouth(RocketPart south) {
		this.south = south;
	}

	public RocketPart getEast() {
		return east;
	}

	public void setEast(RocketPart east) {
		this.east = east;
	}

	public RocketPart getWest() {
		return west;
	}

	public void setWest(RocketPart west) {
		this.west = west;
	}

	public String getType() {
		return type;
	}

	public String getTier() {
		return tier;
	}

	public static ArrayList<RocketPart> allParts() {
		return rocketParts;
	}

}
