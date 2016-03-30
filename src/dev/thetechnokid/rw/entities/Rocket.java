package dev.thetechnokid.rw.entities;

import java.util.ArrayList;

import dev.thetechnokid.rw.maths.Dimension;
import dev.thetechnokid.rw.maths.Direction;
import dev.thetechnokid.rw.maths.Position;
import dev.thetechnokid.rw.maths.VectorQuantity;

public class Rocket extends Entity implements FlyingObject {

	protected int mass;
	protected Dimension size;
	protected Position pos = new Position();
	protected VectorQuantity acceleration = new VectorQuantity(0, Direction.north());
	protected ArrayList<RocketPart> parts = new ArrayList<>();
	
	private boolean launched = false;
	private boolean falling;
	private long fallingTime = 0;
	

	public Rocket() {
		for (RocketPart rocketPart : parts) {
			mass += rocketPart.getMass();
		}
	}

	@Override
	public void render() {
	}

	@Override
	public void tick() {
		if (launched) {
			calculatePos();
		} else {
			if (getAcceleration().getMagnitude() > VectorQuantity.GRAVITY.getMagnitude())
				launched = true;
		}
	}

	private void calculatePos() {
		double altitudeChange = (acceleration.getMagnitude() * acceleration.getDirection().getAltitudeModifier());
		if (altitudeChange - VectorQuantity.GRAVITY.getMagnitude() < 0)
			falling = true;
		else
			falling = false;

		if (falling) {
			fallingTime++;
		} else {
			if (fallingTime > 0)
				fallingTime--;
		}

		pos.altitude += (altitudeChange - (VectorQuantity.GRAVITY.getMagnitude() + (fallingTime / 20)));
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

	public boolean isLaunched() {
		return launched;
	}
}
