package dev.thetechnokid.rw.maths;

public class VectorQuantity {

	private double magnitude;
	private Direction direction;
	private boolean isFinal;

	public VectorQuantity() {
		magnitude = 0;
		direction = Direction.NORTH.clone();
	}

	public VectorQuantity(double x, double y) {
		this.magnitude = Math.hypot(x, y);
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
		if (isFinal)
			return;
		magnitude -= i;
	}

	public void add(VectorQuantity other) {
		double x = this.magnitudeActualX() + other.magnitudeActualX();
		double y = this.magnitudeActualY() + other.magnitudeActualY();
		VectorQuantity newV = new VectorQuantity(x, y);
		this.magnitude = newV.magnitude;
		this.direction = newV.direction;
	}

	public void subtract(VectorQuantity other) {
		double x = this.magnitudeActualX() - other.magnitudeActualX();
		double y = this.magnitudeActualY() - other.magnitudeActualY();
		VectorQuantity newV = new VectorQuantity(x, y);
		this.magnitude = newV.magnitude;
		this.direction = newV.direction;
	}

	public double magnitudeActualY() {
		return magnitude * direction.getAltitudeModifier();
	}

	public double magnitudeActualX() {
		return magnitude * direction.getXModifier();
	}
}
