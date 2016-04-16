package dev.thetechnokid.rw.maths;

public class VectorQuantity {

	private int magnitude;
	private Direction direction;
	private boolean isFinal;

	public VectorQuantity() {
		magnitude = 0;
		direction = Direction.NORTH;
	}

	public VectorQuantity(int magnitude, Direction direction) {
		this(magnitude, direction, false);
	}

	public VectorQuantity(int magnitude, Direction direction, boolean isFinal) {
		this.magnitude = magnitude;
		this.direction = direction;
		this.isFinal = isFinal;
	}

	public boolean equals(VectorQuantity other) {
		return (magnitude == other.magnitude) && (direction.equals(other.direction));
	}

	public int getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(int magnitude) {
		if (isFinal)
			return;
		this.magnitude = magnitude;
	}

	public Direction getDirection() {
		return direction;
	}

	public void increaseMagnitude(int i) {
		if (isFinal)
			return;
		magnitude += i;
	}

	public void decreaseMagnitude(int i) {
		if (isFinal)
			return;
		if (magnitude == 0)
			return;
		magnitude -= i;
	}
}
