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
}
