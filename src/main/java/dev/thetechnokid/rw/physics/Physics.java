package dev.thetechnokid.rw.physics;

import dev.thetechnokid.rw.RocketWarfare;
import dev.thetechnokid.rw.entities.Rocket;
import dev.thetechnokid.rw.maths.*;

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

		velocity.getDirection().set(acceleration.getDirection());
		velocity.add(acceleration);

		Vector airResistance = airResistance(1.225, velocity.getMagnitude(), 0.25, rocket.getWidth(),
				velocity.getDirection());

		velocity.y -= (G / RocketWarfare.FPS);

		airResistance.div(velocity);
		velocity.sub(airResistance);

		pos.y += velocity.y;
		pos.x += velocity.x;

		if (pos.y < 0) {
			rocket.crash();
		}
	}

	/**
	 * Finds the air resistance.
	 * 
	 * @param p
	 *            Density of fluid
	 * @param u
	 *            Velocity of object
	 * @param C
	 *            Drag coefficient
	 * @param A
	 *            Surface area
	 * @param direction
	 *            Direction of object
	 * @return The drag fallback as a vector
	 */
	public static Vector airResistance(double p, double u, double C, double A, Direction direction) {
		double drag = (p * Math.pow(u, 2) * C * A) / 2;
		Vector airResistance = new Vector(drag / RocketWarfare.FPS, direction.opposite());
		return airResistance;
	}

	public static double airResistance(double p, double u, double C, double A) {
		double drag = (p * Math.pow(u, 2) * C * A) / 2;
		return drag;
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
