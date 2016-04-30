package dev.thetechnokid.rw.maths;

import dev.thetechnokid.rw.utils.Physics;

public class Force {
	public static final Force GRAVITY = new Force(Physics.G, Direction.SOUTH.clone(), false);

	private VectorQuantity acceleration;
	private boolean accelerated;

	public Force(double magnitude, Direction direction, boolean accelerated) {
		acceleration = new VectorQuantity(magnitude, direction);
		this.accelerated = accelerated;
	}
	
	public Force(double magnitude, Direction direction) {
		this(magnitude, direction, true);
	}

	public VectorQuantity getAcceleration() {
		return acceleration;
	}

	public boolean isAccelerated() {
		return accelerated;
	}
	
	public void setAccelerated(boolean a) {
		accelerated = a;
	}
	
	public double getForceY() {
		return (acceleration.getMagnitude() * acceleration.getDirection().getAltitudeModifier());
	}

	public double getForceX() {
		return (acceleration.getMagnitude() * acceleration.getDirection().getXModifier());
	}
	
	public double getForceY(int time) {
		 return getForceY() * time;
	}

	public double getForceX(int time) {
		return getForceX() * time;
	}
}
