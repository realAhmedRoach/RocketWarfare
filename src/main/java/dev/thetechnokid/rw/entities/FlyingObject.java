package dev.thetechnokid.rw.entities;

import dev.thetechnokid.rw.maths.*;
import javafx.scene.canvas.GraphicsContext;

public abstract class FlyingObject {

	protected int mass;
	protected Dimension size;
	protected transient Position pos;
	protected transient Vector acceleration;
	protected transient Vector velocity;
	protected transient GraphicsContext g;
	protected int time;

	public FlyingObject(GraphicsContext g) {
		this.g = g;
		acceleration = new Vector();
		velocity = new Vector();
	}

	public Vector getAcceleration() {
		return acceleration;
	}

	public Vector getVelocity() {
		return velocity;
	}

	public double getX() {
		return pos.x;
	}

	public void setX(double x) {
		pos.x = x;
	}

	public Position getPosition() {
		return pos;
	}

	public double getAltitude() {
		return pos.y;
	}

	public int getMass() {
		return mass;
	}

	public int getWidth() {
		return size.getWidth();
	}

	public int getHeight() {
		return size.getHeight();
	}

	public int getTime() {
		return time;
	}
	
	public abstract void tick();

	public abstract void render(double x, double y);
}
