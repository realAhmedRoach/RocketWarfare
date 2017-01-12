package dev.thetechnokid.rw.maths;

public class Vector {
	public double x, y;

	public Vector() {
		x = 0;
		y = 0;
	}

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector(double magnitude, Direction direction) {
		x = magnitude * direction.getXModifier();
		y = magnitude * direction.getAltitudeModifier();
	}

	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void set(double n) {
		this.x = n;
		this.y = n;
	}

	public void add(Vector other) {
		x += other.x;
		y += other.y;
	}

	public void sub(Vector other) {
		x -= other.x;
		y -= other.y;
	}

	public void mul(Vector other) {
		x *= other.x;
		y *= other.y;
	}

	public void div(Vector other) {
		x /= other.x;
		y /= other.y;
	}

	public void rotate(int degrees) {
		double rx = (this.x * Math.cos(degrees)) - (this.y * Math.sin(degrees));
		double ry = (this.x * Math.sin(degrees)) + (this.y * Math.cos(degrees));
		x = rx;
		y = ry;
	}

	public double getMagnitude() {
		return Math.hypot(x, y);
	}
}
