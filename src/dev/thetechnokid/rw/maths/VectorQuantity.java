package dev.thetechnokid.rw.maths;

public class VectorQuantity {

	public int magnitude;
	public Direction direction;

	public VectorQuantity() {
		magnitude = 0;
		direction = Direction.NORTH;
	}
	
	public VectorQuantity(int magnitude, Direction direction) {
		this.magnitude = magnitude;
		this.direction = direction;
	}
	
	public boolean equals(VectorQuantity other) {
		return (magnitude == other.magnitude) && (direction.equals(other.direction));
	}
}
