package dev.thetechnokid.rw.physics;

import dev.thetechnokid.rw.RocketWarfare;
import dev.thetechnokid.rw.entities.Rocket;
import dev.thetechnokid.rw.maths.Position;
import dev.thetechnokid.rw.maths.Vector;

public class Physics {

	/**
	 * This is the Gravity constant.
	 */
	public static final double G = 16; // 32 f/s^2; 1px = 6in;

	private Physics() {
	}

	public static void position(int time, Rocket rocket, Position pos) {
		Vector velocity = rocket.getVelocity();
		Vector acceleration = rocket.getAcceleration();

		velocity.add(acceleration);

		velocity.y -= (G / RocketWarfare.FPS);

		pos.y += velocity.y;
		pos.x += velocity.x;

		// if (pos.y < 0) {
		// rocket.crash();
		// }
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
