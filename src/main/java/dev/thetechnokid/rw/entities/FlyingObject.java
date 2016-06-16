package dev.thetechnokid.rw.entities;

import dev.thetechnokid.rw.maths.*;

public abstract class FlyingObject {

	protected int mass;
	protected Dimension size;
	protected transient Position pos;
	protected transient VectorQuantity acceleration;
	protected transient VectorQuantity velocity;

	public FlyingObject() {
		acceleration = new VectorQuantity();
		velocity = new VectorQuantity();
	}

	public VectorQuantity getAcceleration() {
		return acceleration;
	}

	public VectorQuantity getVelocity() {
		return velocity;
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
		return pos.y;
	}

	public int getMass() {
		return mass;
	}

	public int getWidth() {
		return size.getWidth();
	}

	public int getHeight() {
		return size.getHeight();
	}

	public abstract void tick();

	public abstract void render(int x, int y, double scale);
}
