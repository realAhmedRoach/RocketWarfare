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

	public void increaseMagnitude(double i, Direction direction) {
		if (isFinal)
			return;
		VectorQuantity add = new VectorQuantity(i, direction);
		double x = this.magnitudeActualX() + add.magnitudeActualX();
		double y = this.magnitudeActualY() + add.magnitudeActualY();
		VectorQuantity sum = new VectorQuantity(this.magnitudeActualX() - add.magnitudeActualX(),
				this.magnitudeActualY() - add.magnitudeActualY());
		magnitude -= sum.getMagnitude();
		direction = sum.getDirection();
	}

	public void decreaseMagnitude(double i) {
		if (isFinal)
			return;
		magnitude -= i;
	}

	public void decreaseMagnitude(double i, Direction direction) {
		if (isFinal)
			return;
		VectorQuantity sub = new VectorQuantity(i, direction);
		double x = this.magnitudeActualX() + sub.magnitudeActualX();
		double y = this.magnitudeActualY() + sub.magnitudeActualY();
		VectorQuantity difference = new VectorQuantity(this.magnitudeActualX() - sub.magnitudeActualX(),
				this.magnitudeActualY() - sub.magnitudeActualY());
		magnitude -= difference.getMagnitude();
		direction = difference.getDirection();
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
