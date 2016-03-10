package dev.thetechnokid.rw.maths;

public class VectorQuantity {

	private int magnitude;
	private Direction direction;

	public VectorQuantity() {
		magnitude = 0;
		direction = Direction.north();
	}
	
	public VectorQuantity(int magnitude, Direction direction) {
		this.magnitude = magnitude;
		this.direction = direction;
	}
	
	public boolean equals(VectorQuantity other) {
		return (magnitude == other.magnitude) && (direction.equals(other.direction));
	}
	
	public int getMagnitude() {
		return magnitude;
	}
	
	public void setMagnitude(int magnitude) {
		this.magnitude = magnitude;
	}
	
	public Direction getDirection() {
		return direction;
	}

	public void increaseMagnitude(int i) {
		magnitude += i;
	}

	public void decreaseMagnitude(int i) {
		magnitude -= i;
	}
}
