package dev.thetechnokid.rw.maths;

public class Vector {
	private double x, y;

	public Vector(double newX, double newY) {
		x = newX;
		y = newY;
	}

	public Vector(double magnitude, Direction direction) {
		x = magnitude * direction.getXModifier();
		y = magnitude * direction.getAltitudeModifier();
	}

	public void add(Vector other) {
		x += other.x;
		y += other.y;
	}

	public void sub(Vector other) {
		x -= other.x;
		y -= other.y;
	}

	public double getMagnitude() {
		return Math.hypot(x, y);
	}

	public Direction getDirection() {
		return new Direction((int) Math.toDegrees(Math.atan2(y, x)));
	}
}
