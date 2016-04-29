package dev.thetechnokid.rw.maths;

public class Force {
	public static final Force GRAVITY = new Force(5, Direction.SOUTH.clone());
	
	private VectorQuantity acceleration;
	
	public Force(int magnitude, Direction direction) {
		acceleration = new VectorQuantity(magnitude, direction);
	}
	
	public VectorQuantity getAcceleration() {
		return acceleration;
	}
}
