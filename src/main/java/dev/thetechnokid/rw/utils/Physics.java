package dev.thetechnokid.rw.utils;

import dev.thetechnokid.rw.maths.*;

public class Physics {

	/**
	 * This is the Gravity constant.
	 */
	public static final double G = 5;

	private Physics() {
	}

	/**
	 * Equation for rocket altitude
	 * 
	 * @param t
	 *            Time
	 * @return The position delta
	 */
	public static double positionY(int t, Force... forces) {
		double add = 0.0;
		for (Force force : forces) {
			add += force.isAccelerated() ? force.getForceY(t) : force.getForceY();
		}
		return add;
	}

	/**
	 * Equation for rocket x
	 * 
	 * @param t
	 *            Time
	 * @return The position delta
	 */
	public static double positionX(int t, Force... forces) {
		double add = 0.0;
		for (Force force : forces) {
			add += force.getForceX();
		}
		return add;
	}

	public static void position(int time, Position pos, VectorQuantity velocity, VectorQuantity acceleration,
			Force... forces) {

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
