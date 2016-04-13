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
	protected VectorQuantity acceleration = new VectorQuantity(0, Direction.NORTH.clone());
	protected ArrayList<RocketPart> parts = new ArrayList<>();
	
	private boolean launched = false;
	private boolean falling;
	private long velocity = 0;

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
			velocity--;
		} else {
			if (velocity <= 0)
				velocity++;
		}
		
		pos.altitude += (altitudeChange - (VectorQuantity.GRAVITY.getMagnitude() - (velocity / 15)));
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

	public void addPart(RocketPart part) {
		parts.add(part);
		mass += part.getMass();
	}

	public boolean isLaunched() {
		return launched;
	}
}
