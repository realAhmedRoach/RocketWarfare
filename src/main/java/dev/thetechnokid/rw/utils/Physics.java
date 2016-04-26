package dev.thetechnokid.rw.utils;

public class Physics {

	/**
	 * This is the Gravity constant.
	 */
	public static final double G = 5;
	private static final double SLOW = 10;

	private Physics() {
	}

	/**
	 * Equation for rocket position
	 * 
	 * @param a
	 *            Acceleration
	 * @param v
	 *            Velocity
	 * @param t
	 *            Time
	 * @return The position delta
	 */
	public static double position(double a, double v, double t) {
		return a + v - ((G * t) / SLOW);
	}

	/**
	 * Gets the initial velocity required for an object to be launched
	 * 
	 * @param m
	 *            The mass of the object
	 * @return Minimum velocity for launch
	 */
	public static double initialVelocity(double m) {
		return G + 1;
	}
}
