package dev.thetechnokid.rw.entities;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class RocketPart extends Entity {

	private int type, mass;
	private Point2D posInRocket;
	private RocketPart north, south, east, west;

	public RocketPart(GraphicsContext g) {
		super(g);
	}

	@Override
	public void render() {
	}

	@Override
	public void tick() {
	}

	public int getType() {
		return type;
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

}
