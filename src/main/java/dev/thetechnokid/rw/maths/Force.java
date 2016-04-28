package dev.thetechnokid.rw.maths;

public class Force {
	private VectorQuantity acceleration;
	
	public Force(int magnitude, Direction direction) {
		acceleration = new VectorQuantity(magnitude, direction);
	}
	
	public VectorQuantity getAcceleration() {
		return acceleration;
	}
}
