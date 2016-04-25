package dev.thetechnokid.rw.entities;

import java.io.Serializable;
import java.util.*;

import dev.thetechnokid.rw.maths.*;
import dev.thetechnokid.rw.utils.*;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.*;

public class Rocket extends Entity implements FlyingObject, Serializable {
	private static final double VEL_DELTA = (1.0 / 10.0);

	protected int mass;
	protected Dimension size;
	protected transient Position pos;
	private double velocity;
	protected transient VectorQuantity acceleration;
	protected List<RocketPart> parts;

	private boolean launched = false;

	private GraphicsContext g;

	public Rocket(GraphicsContext g) {
		this.g = g;
		size = new Dimension();
		pos = new Position();
		acceleration = new VectorQuantity();
		parts = new ArrayList<RocketPart>();
	}

	@Override
	public void render(int x, int y) {
		g.save();
		g.setTransform((new Affine(new Rotate(-(acceleration.getDirection().getDegrees() - 90),
				x + ((getWidth() * Grid.SIZE) / 2), y + (getHeight() * Grid.SIZE)))));
		for (RocketPart rocketPart : parts) {
			Point2D partPos = rocketPart.getPosInRocket();
			int xx = (int) (x + (partPos.getX() * Grid.SIZE));
			int yy = (int) (y + (partPos.getY() * Grid.SIZE));
			g.drawImage(rocketPart.getImage(), xx, yy);
		}
		g.restore();
	}

	@Override
	public void tick() {
		if (launched) {
			calculatePos();
		} else {
			if (acceleration.getMagnitude() > 0) {
				acceleration.setMagnitude((int) (Physics.G + 1));
				launched = true;
			}
		}
	}

	private void calculatePos() {
		double aa = (acceleration.getMagnitude() * acceleration.getDirection().getAltitudeModifier());
		double xa = (acceleration.getMagnitude() * acceleration.getDirection().getXModifier());

		boolean falling = (aa - Physics.G < 0);

		if (falling) {
			velocity -= VEL_DELTA;
		} else {
			if (velocity <= Physics.initialVelocity(mass))
				velocity += VEL_DELTA;
		}

		pos.altitude += Physics.position(aa, velocity, mass);
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
		if (part == null)
			return;
		parts.add(part);
		mass += part.getMass();
		size.setWidth(parts.stream().mapToInt(p -> (int) p.getPosInRocket().getX()).max().getAsInt());
		size.setHeight(parts.stream().mapToInt(p -> (int) p.getPosInRocket().getY()).max().getAsInt());
		velocity = Physics.initialVelocity(mass);
	}

	public boolean isLaunched() {
		return launched;
	}
}
