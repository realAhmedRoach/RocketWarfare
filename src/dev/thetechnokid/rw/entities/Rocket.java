package dev.thetechnokid.rw.entities;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

public class Rocket extends Entity {

	protected int weight, x, altitude, width, height, thrust;
	protected ArrayList<RocketPart> parts;
	
	public Rocket(GraphicsContext g) {
		super(g);

	}

	@Override
	public void render() {
	}

	@Override
	public void tick() {
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getAltitude() {
		return altitude;
	}

	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}

	public int getWeight() {
		return weight;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getThrust() {
		return thrust;
	}
	
	public ArrayList<RocketPart> getParts() {
		return parts;
	}

}
