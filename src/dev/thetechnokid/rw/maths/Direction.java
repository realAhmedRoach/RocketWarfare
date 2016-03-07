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
}
