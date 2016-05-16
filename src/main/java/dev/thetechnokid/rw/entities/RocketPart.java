package dev.thetechnokid.rw.entities;

import java.io.*;
import java.util.ArrayList;

import dev.thetechnokid.rw.RocketWarfare;
import dev.thetechnokid.rw.utils.Assets;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class RocketPart extends Entity implements Serializable {
	private static final long serialVersionUID = 3857824053568051475L;

	private int mass;
	private Point2D posInRocket;
	private RocketPart north, south, east, west;

	protected String type, tier;

	private static ArrayList<String> rocketParts = new ArrayList<String>();

	public static final String[] FLIPPABLE_PARTS = { "FIN", "MISSILE" };

	static {

		String line = null;
		try (BufferedReader r = new BufferedReader(
				new InputStreamReader(RocketWarfare.class.getResourceAsStream("/images/rparts.txt")));) {
			while ((line = r.readLine()) != null) {
				String name = line.split(" ")[0];

				rocketParts.add(name);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(rocketParts);
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

	/**
	 * @param mass
	 *            the mass of the rocket part
	 * @param type
	 *            the type of the rocket part (e.g. 'fin')
	 * @param tier
	 *            the tier of the rocket part (e.g. 'advanced')
	 * @param image
	 *            the image representing this rocket part
	 * @param flipped
	 *            whether this is flipped or not
	 */
	public RocketPart(String type, String tier, int mass, Image image, boolean flipped) {
		this.mass = mass;
		this.type = type;
		this.tier = tier;
		this.image = (flipped) ? Assets.flip(image) : image;
	}

	public boolean equals(RocketPart other) {
		return (this == other) ? true : false;
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

	public static ArrayList<String> allParts() {
		return rocketParts;
	}

	@Override
	public String toString() {
		return "RocketPart: { " + getType() + " " + getTier() + ", " + getMass() + " }";
	}

}
