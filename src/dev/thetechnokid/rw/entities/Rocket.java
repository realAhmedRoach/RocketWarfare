package dev.thetechnokid.rw.entities;

import java.util.ArrayList;

import dev.thetechnokid.rw.maths.*;
import javafx.scene.canvas.GraphicsContext;

public class Rocket extends Entity {

	protected int mass, x, altitude, width, height;
	protected VectorQuantity acceleration = new VectorQuantity(0, Direction.NORTH);
	protected ArrayList<RocketPart> parts;
	
	public Rocket(GraphicsContext g) {
		super(g);
		for (RocketPart rocketPart : parts) {
			mass += rocketPart.getMass();
		}
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

	public int getMass() {
		return mass;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public VectorQuantity getAcceleration() {
		return acceleration;
	}
	
	public int getForce() {
		return mass * acceleration.magnitude;
	}
	
	public ArrayList<RocketPart> getParts() {
		return parts;
	}

}
