package dev.thetechnokid.rw.utils;

public class Physics {

	/**
	 * This is the Gravity constant.
	 */
	public static final double G = 5;
	
	private Physics() {
	}

	/**
	 * Equation for rocket position
	 * @param a Acceleration
	 * @param v Velocity
	 * @param m Mass
	 * @return The position delta
	 */
	public static double position(double a, double v, double m) {
		return a + v + (m * G);
	}
	
	public static double initialVelocity(double m) {
		return m * G;
	}
}
