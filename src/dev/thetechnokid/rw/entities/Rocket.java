package dev.thetechnokid.rw.entities;

import java.util.ArrayList;

import dev.thetechnokid.rw.maths.*;
import javafx.scene.canvas.GraphicsContext;

public class Rocket extends Entity {

	protected int mass, width, height;
	protected Position pos;
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
		pos.altitude += acceleration.magnitude;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public Position getPosition() {
		return pos;
	}

	public int getAltitude() {
		return pos.altitude;
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
