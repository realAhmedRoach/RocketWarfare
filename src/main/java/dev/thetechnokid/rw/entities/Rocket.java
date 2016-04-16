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
	private long velocity = 0;
	protected VectorQuantity acceleration = new VectorQuantity(0, Direction.NORTH.clone());
	protected ArrayList<RocketPart> parts = new ArrayList<>();

	private boolean launched = false;
	private boolean falling;

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
		double aa = (acceleration.getMagnitude() * acceleration.getDirection().getAltitudeModifier());
		double xa = (acceleration.getMagnitude() * acceleration.getDirection().getAltitudeModifier());
		double v = velocity / 15;
		
		falling = (acceleration.getMagnitude() - VectorQuantity.GRAVITY.getMagnitude() < 0);

		if (falling) {
			velocity--;
		} else {
			if (velocity <= 0)
				velocity++;
		}

		pos.altitude += (aa - (VectorQuantity.GRAVITY.getMagnitude() - v));
		pos.x += xa;

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

	public long getVelocity() {
		return velocity;
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
