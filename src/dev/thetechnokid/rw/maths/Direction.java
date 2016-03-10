package dev.thetechnokid.rw.maths;

public class Direction {
	private int degrees;

	public static Direction north() {
		return new Direction(90);
	}

	public static Direction south() {
		return new Direction(270);
	}

	public static Direction east() {
		return new Direction(0);
	}

	public static Direction west() {
		return new Direction(180);
	}

	public Direction(int degrees) {
		this.degrees = degrees;
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
		if (degrees < 360) degrees++;
		else degrees = 1;
	}
	
	public void decreaseDegrees() {
		if (degrees > 0) degrees--;
		else degrees = 359;
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
		} else if (degrees > 90) {
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
			if (degrees > 90)
				degreesAbs = 90 - degrees;
			else if (degrees < 270)
				degreesAbs = degrees - 180;
		}

		percent = degreesAbs / 90;
		return percent;
	}
}
