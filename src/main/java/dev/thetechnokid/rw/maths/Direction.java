package dev.thetechnokid.rw.maths;

public class Direction {
	private int degrees;
	private boolean isFinal;

	public static final Direction NORTH = new Direction(90, true);

	public static final Direction SOUTH = new Direction(270, true);

	public static final Direction EAST = new Direction(0, true);

	public static final Direction WEST = new Direction(180, true);

	public Direction(int degrees) {
		this(degrees, false);
	}

	public Direction(int degrees, boolean isFinal) {
		this.degrees = degrees;
		this.isFinal = isFinal;
	}

	public boolean equals(Direction other) {
		return this.degrees == other.degrees;
	}

	@Override
	public int hashCode() {
		return degrees;
	}

	public int getDegrees() {
		return degrees;
	}

	public void increaseDegrees() {
		if (isFinal)
			return;
		if (degrees < 360)
			degrees++;
		else
			degrees = 1;
	}

	public void decreaseDegrees() {
		if (isFinal)
			return;
		if (degrees > 0)
			degrees--;
		else
			degrees = 359;
	}

	public double getAltitudeModifier() {
		double percent = 0;
		double degreesAbs = 0;
		if (degrees == 0 || degrees == 180)
			return 0;
		else if (degrees == 90)
			return 1;
		else if (degrees == 270)
			return -1;
		if (degrees < 90) {
			degreesAbs = degrees;
		} else if (degrees > 90 && degrees < 270) {
			degreesAbs = 180 - degrees;
		} else if (degrees > 270) {
			degreesAbs = degrees - 360;
		}
		percent = degreesAbs / 90;
		return percent;
	}

	public double getXModifier() {
		double percent = 0;
		double degreesAbs = 0;

		if (degrees == 0)
			return 1;
		else if (degrees == 180)
			return -1;
		else if (degrees == 90 || degrees == 270)
			return 0;

		if (degrees < 90 || degrees > 270) {
			if (degrees < 90)
				degreesAbs = 90 - degrees;
			else if (degrees > 270)
				degreesAbs = degrees - 270;
		} else if (degrees > 90 || degrees < 270) {
			if (degrees > 90 && degrees < 180)
				degreesAbs = 90 - degrees;
			else if (degrees < 270)
				degreesAbs = degrees - 270;
		}

		percent = degreesAbs / 90;
		return percent;
	}
}
