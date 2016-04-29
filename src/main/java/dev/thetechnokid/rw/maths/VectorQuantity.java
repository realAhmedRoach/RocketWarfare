package dev.thetechnokid.rw.maths;

public class VectorQuantity {

	private double magnitude;
	private Direction direction;
	private boolean isFinal;

	public VectorQuantity() {
		magnitude = 0;
		direction = Direction.NORTH.clone();
	}

	public VectorQuantity(int x, int y) {
		this.magnitude = (int) Math.hypot(x, y);
		this.direction = new Direction((int) Math.toDegrees(Math.atan2(y, x)));
	}

	public VectorQuantity(double magnitude, Direction direction) {
		this(magnitude, direction, false);
	}

	public VectorQuantity(double magnitude, Direction direction, boolean isFinal) {
		this.magnitude = magnitude;
		this.direction = direction;
		this.isFinal = isFinal;
	}

	public boolean equals(VectorQuantity other) {
		return (magnitude == other.magnitude) && (direction.equals(other.direction));
	}

	public double getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(double magnitude) {
		if (isFinal)
			return;
		this.magnitude = magnitude;
	}

	public Direction getDirection() {
		return direction;
	}

	public void increaseMagnitude(double i) {
		if (isFinal)
			return;
		magnitude += i;
	}

	public void decreaseMagnitude(double i) {
		if (isFinal || magnitude == 0)
			return;
		magnitude -= i;
	}

	/**
	 * Returns the speed of the rocket, in altitudes per frame.
	 * 
	 * @return The altitude per frame of the rocket.
	 */
	public double apf() {
		return ((Math.sin(Math.toRadians(direction.getDegrees())))
				* (magnitude)) * direction.getAltitudeModifier();
	}
}
