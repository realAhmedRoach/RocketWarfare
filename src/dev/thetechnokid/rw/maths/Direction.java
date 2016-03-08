package dev.thetechnokid.rw.maths;

public class Direction {
	public int degrees;
	
	public static final Direction NORTH = new Direction(90);
	public static final Direction SOUTH = new Direction(270);
	public static final Direction EAST = new Direction(0);
	public static final Direction WEST = new Direction(180);
	
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
	
	public double getAltitudeModifier() {
		double percent = 0;
		int degreesAbs = 0;
		if(degrees == 0 || degrees == 180) 
			return 0;
		else if (degrees == 90) 
			return 1;
		else if (degrees == 270)
			return -1;
		if (degrees <= 180) {
			if(degrees > 90) {
				degreesAbs = degrees - 90;
			}
			percent = degreesAbs / 90;
		} else if (degrees < 180) {
			if (degrees < 270) {
				degreesAbs = degrees - 180;
			} else {
				degreesAbs = degrees - 270;
			}
			percent = -degreesAbs / 90;
		}
		System.out.println(percent);
		return percent;
	}
}
