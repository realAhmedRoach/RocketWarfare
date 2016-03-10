package dev.thetechnokid.rw.entities;

import java.util.ArrayList;

import dev.thetechnokid.rw.maths.*;
import javafx.scene.canvas.GraphicsContext;

public class Rocket extends Entity {

	protected int mass;
	protected Dimension size;
	protected Position pos = new Position();
	protected VectorQuantity acceleration = new VectorQuantity(0, Direction.NORTH);
	protected ArrayList<RocketPart> parts = new ArrayList<>();
	
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
		pos.x += acceleration.getMagnitude() * acceleration.getDirection().getXModifier();
	}

	public double getX() {
		return pos.x;
	}

	public void setX(double x) {
		pos.x = x;
	}

	public Position getPosition() {
		return pos;
	}

	public double getAltitude() {
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
