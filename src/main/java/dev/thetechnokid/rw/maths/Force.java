package dev.thetechnokid.rw.maths;

import dev.thetechnokid.rw.utils.Physics;

public class Force {
	public static final Force GRAVITY = new Force(Physics.G, Direction.SOUTH.clone());

	private VectorQuantity acceleration;

	public Force(double magnitude, Direction direction) {
		acceleration = new VectorQuantity(magnitude, direction);
	}

	public VectorQuantity getAcceleration() {
		return acceleration;
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
