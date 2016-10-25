package dev.thetechnokid.rw.physics;

import dev.thetechnokid.rw.RocketWarfare;
import dev.thetechnokid.rw.maths.*;

public class Physics {

	/**
	 * This is the Gravity constant.
	 */
	public static final double G = 16;

	private Physics() {
	}

	public static void position(int time, Position pos, VectorQuantity velocity, VectorQuantity acceleration) {
		velocity.increaseMagnitude(acceleration.getMagnitude());
		if (acceleration.getDirection().getDegrees() > 180) {
			velocity.increaseMagnitude(G / RocketWarfare.FPS);
		} else {
			velocity.decreaseMagnitude(G / RocketWarfare.FPS);
		}
		velocity.getDirection().set(acceleration.getDirection());
		pos.y += velocity.magnitudeActualY();
		pos.x += velocity.magnitudeActualX();
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
