package dev.thetechnokid.rw.entities;

import java.util.ArrayList;

import dev.thetechnokid.rw.maths.*;
import javafx.scene.canvas.GraphicsContext;

public class Rocket extends Entity {

	protected int mass;
	protected Dimension size;
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
		pos.altitude += acceleration.getMagnitude() * acceleration.getDirection().getAltitudeModifier();
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
		return (int) size.getWidth();
	}

	public int getHeight() {
		return (int) size.getHeight();
	}

	public VectorQuantity getAcceleration() {
		return acceleration;
	}
	
	public int getForce() {
		return mass * acceleration.getMagnitude();
	}
	
	public ArrayList<RocketPart> getParts() {
		return parts;
	}

}
