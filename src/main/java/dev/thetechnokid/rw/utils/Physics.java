package dev.thetechnokid.rw.utils;

public class Physics {

	/**
	 * This is the Gravity constant.
	 */
	public static final double G = 5;
	
	private Physics() {
	}

	public static double position(double a, double v, double m) {
		return a + v - G;
	}
	
}
