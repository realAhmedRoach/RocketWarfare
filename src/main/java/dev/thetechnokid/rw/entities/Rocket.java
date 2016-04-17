package dev.thetechnokid.rw.entities;

import java.util.ArrayList;

import dev.thetechnokid.rw.maths.*;
import dev.thetechnokid.rw.utils.*;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class Rocket extends Entity implements FlyingObject {
	private static final double VEL_DELTA = (1.0 / 10.0);

	protected int mass;
	protected Dimension size;
	protected Position pos = new Position();
	private double velocity = 0;
	protected VectorQuantity acceleration = new VectorQuantity(0, Direction.NORTH.clone());
	protected ArrayList<RocketPart> parts = new ArrayList<>();

	private boolean launched = false;
	private boolean falling;

	private GraphicsContext g;

	public Rocket(GraphicsContext g) {
		this.g = g;
	}

	@Override
	public void render(int x, int y) {
		for (RocketPart rocketPart : parts) {
			Point2D partPos = rocketPart.getPosInRocket();
			int xx = (int) (x + (partPos.getX() * Grid.SIZE));
			int yy = (int) (y + (partPos.getY() * Grid.SIZE));
			g.drawImage(rocketPart.getImage(), xx, yy);
		}
	}

	@Override
	public void tick() {
		if (launched) {
			calculatePos();
		} else {
			if (getAcceleration().getMagnitude() > Physics.G)
				launched = true;
		}
	}

	private void calculatePos() {
		double aa = (acceleration.getMagnitude() * acceleration.getDirection().getAltitudeModifier());
		double xa = (acceleration.getMagnitude() * acceleration.getDirection().getXModifier());
		double v = velocity;

		falling = (aa - Physics.G < 0);

		if (falling) {
			velocity -= VEL_DELTA;
		} else {
			if (velocity <= 0)
				velocity += VEL_DELTA;
		}

		pos.altitude += (aa + v - Physics.G);
		pos.x += xa;

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

	public int getVelocity() {
		return (int) velocity;
	}

	public double getAltitude() {
		return pos.altitude;
	}

	public int getMass() {
		return mass;
	}

	public int getWidth() {
		return (int) size.getWidth();
	}

	public int getHeight() {
		return (int) size.getHeight();
	}

	public VectorQuantity getAcceleration() {
		return acceleration;
	}

	public int getForce() {
		return mass * acceleration.getMagnitude();
	}

	public void addPart(RocketPart part) {
		parts.add(part);
		mass += part.getMass();
	}

	public boolean isLaunched() {
		return launched;
	}
}
