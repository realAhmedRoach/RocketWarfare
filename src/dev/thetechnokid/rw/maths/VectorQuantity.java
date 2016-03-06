package dev.thetechnokid.rw.maths;

public class VectorQuantity {

	public int magnitude;
	public Direction direction;

	public boolean equals(VectorQuantity other) {
		return (magnitude == other.magnitude) && (direction.equals(other.direction));
	}
}
